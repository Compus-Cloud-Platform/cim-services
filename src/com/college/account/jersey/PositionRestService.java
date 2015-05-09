package com.college.account.jersey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.college.account.bean.Position;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;
import com.college.util.ServiceFactoryBean;


@Path("/positions")
public class PositionRestService {
	
	private static int POSITIONLOSTNAME = 3001;
	private static int POSITIONIDNOTFIND = 3002;
	
	private static String tablename = "Position";

	@GET
    @Path("/save")
	@Consumes({MediaType.APPLICATION_JSON})
	public String save(@QueryParam("jsonString") String jsonString){
		Integer id = null;
		Position position = null;
		position = (Position)Json2Obj.getObj(jsonString, Position.class);
		
		if(null == position.getName()){
			return Cause.getFailcode(POSITIONLOSTNAME, "name", null);
		}
		
		position.setCreateTime(new Date());
		
		id = ServiceFactoryBean.getPositionService().create(position);
		
		return Cause.getSuccess(id);
	}
	
	 @GET
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Path("/id")
	 public String searchInfo(@QueryParam("id") String id)
	 {
		 Position position = null;
		 List list = new ArrayList(1);
		 
		 position = ServiceFactoryBean.getPositionService().searchByid(Integer.parseInt(id), tablename);
		 
		 if(null == position){ return Cause.getFailcode(POSITIONIDNOTFIND, "id", null);}
		 
		 list.add(Obj2Map.toMap(position, Position.class));
		 
		 return Cause.getData(list);
	 }
	 
	 @GET
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Path("/update")
	 public String updateInfo(@QueryParam("jsonString") String jsonString){
		 
		 Position position = null;
		 Position positionfind = null;
		 position = (Position)Json2Obj.getObj(jsonString, Position.class);
		 
		 positionfind = ServiceFactoryBean.getPositionService().searchByid(position.getId(), tablename);
		 
		 if(null == position){ return Cause.getFailcode(POSITIONIDNOTFIND, "id", null);}
		 
		 Json2Obj.repalceDiffObjMem(position, positionfind, Position.class);
		 
		 ServiceFactoryBean.getPositionService().update(positionfind);
		 
		 return Cause.getSuccess(null);
	 }
	 
//	 @GET
//	 @Consumes({MediaType.APPLICATION_JSON})
//	 @Path("/id")
//	 public String deleteInfo(@QueryParam("id") String id){
//		 
//		 ServiceFactoryBean.getPositionService().delete(Position.class, Integer.parseInt(id));
//		 
//		 return Cause.getSuccess(null);
//	 }
}
