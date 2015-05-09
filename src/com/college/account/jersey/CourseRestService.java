package com.college.account.jersey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.college.account.bean.Course;
import com.college.account.bean.Major;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;
import com.college.util.ServiceFactoryBean;


@Path("/courses")
public class CourseRestService {

	private static int COURSELOSTNAME = 6001;
	private static int COURSEIDNOTFIND = 6002;
	
	private static String tablename = "Course";

	@GET
    @Path("/save")
	@Consumes({MediaType.APPLICATION_JSON})
	public String save(@QueryParam("jsonString") String jsonString){
		Integer id = null;
		Course course = null;
		course = (Course)Json2Obj.getObj(jsonString, Course.class);
		
		if(null == course.getName()){
			return Cause.getFailcode(COURSELOSTNAME, "name", null);
		}
		
		course.setCreateTime(new Date());
		
		id = ServiceFactoryBean.getCourseService().create(course);
		
		return Cause.getSuccess(id);
	}
	
	 @GET
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Path("/id")
	 public String searchInfo(@QueryParam("id") String id)
	 {
		 Major major = null;
		 List list = new ArrayList(1);
		 
		 major = ServiceFactoryBean.getMajorService().searchByid(Integer.parseInt(id), tablename);
		 
		 if(null == major){ return Cause.getFailcode(COURSEIDNOTFIND, "id", null);}
		 
		 list.add(Obj2Map.toMap(major, Major.class));
		 
		 return Cause.getData(list);
	 }
	 
	 @GET
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Path("/update")
	 public String updateInfo(@QueryParam("jsonString") String jsonString){
		 
		 Major major = null;
		 Major majorfind = null;
		 major = (Major)Json2Obj.getObj(jsonString, Major.class);
		 
		 majorfind = ServiceFactoryBean.getMajorService().searchByid(major.getId(), tablename);
		 
		 if(null == major){ return Cause.getFailcode(COURSEIDNOTFIND, "id", null);}
		 
		 Json2Obj.repalceDiffObjMem(major, majorfind, Major.class);
		 
		 ServiceFactoryBean.getMajorService().update(majorfind);
		 
		 return Cause.getSuccess(null);
	 }
	 
//	 @GET
//	 @Consumes({MediaType.APPLICATION_JSON})
//	 @Path("/id")
//	 public String deleteInfo(@QueryParam("id") String id){
//		 
//		 ServiceFactoryBean.getMajorService().delete(Major.class, Integer.parseInt(id));
//		 
//		 return Cause.getSuccess(null);
//	 }
}
