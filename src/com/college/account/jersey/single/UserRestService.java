package com.college.account.jersey.single;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import com.college.account.service.DaoUsersService;
import com.college.account.service.DaoUsersServiceExt;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/users")
public class UserRestService {
	


	private static final Logger log = Logger4j.getLogger(UserRestService.class);
	
	private static DaoUsersService p = ServiceFactoryBean.getUserService();
	private static DaoUsersServiceExt pext = ServiceFactoryBean.getUserServiceExt();
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(String jsonString){
		
		try {
			
			String result=  p.save(jsonString);
			
			if(!Cause.isSuccess(result)){
				return result;
			}
			
			pext.save(Cause.getResultId(result), jsonString);
			
			return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(1000, "", "system error");
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String updateInfo(@PathParam("id") String id,
	                                          String jsonString){
		try {
			
			String result[]=  p.upd(Integer.parseInt(id), jsonString);
			
			if(!Cause.isSuccess(result[0])){
				return result[0];
			}
			
			pext.upd(result[1], jsonString);
			
			return result[0];
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(1000, "", "system error");
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("{id}")
	public String deleteInfo(@PathParam("id") String id){
		 
		try {
			
			String result[]=  p.del(Integer.parseInt(id));
			
			if(!Cause.isSuccess(result[0])){
				return result[0];
			}
			
			pext.del(result[1]);
			
			return result[0];
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(1000, "", "system error");
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String searchInfo(@PathParam("id") String id)
	{
		try {
			
			return p.sel(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(1000, "", "system error");
		
	}
	
	@GET
	public String getAllObject()
	{
		try {
			
			return p.getAllObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(1000, "", "system error");
		
	}
	
	@GET
	@Path("/{from}/{size}")
	public String getAllObjectFromSize(@PathParam("from") String from,
			                           @PathParam("size") String size)
	{
		try {
			
			return p.getAllObjectFromSize(Integer.parseInt(from), Integer.parseInt(size));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(1000, "", "system error");
		
	}
	
	/* from 开始页码 size 一页的个数 */
	@GET
	@Path("/position-id/{id}/{from}/{size}")
	public String getAllByPosition(@PathParam("id") String id,
			                       @PathParam("from") String from,
			                       @PathParam("size") String size)
	{
		try {
			
			return p.getAllByPosition(Integer.parseInt(id), Integer.parseInt(from), Integer.parseInt(size));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(1000, "", "system error");
		
	}
}
