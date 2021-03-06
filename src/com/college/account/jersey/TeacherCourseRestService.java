package com.college.account.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;


import com.college.account.service.DaoTeacherCourseService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/teacher-courses")
public class TeacherCourseRestService {

	private static DaoTeacherCourseService pT = ServiceFactoryBean.getTeacherCourseService();
	private static final Logger log = Logger4j.getLogger(TeacherCourseRestService.class);
	    
 
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(String jsonString){
    	
    	try {
    		
			return pT.save(jsonString, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(15000, "", "system error");
    }
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String updateteachercourse(@PathParam("id")  String id,
	                                   String jsonString){
		try {
			
			String result =  pT.upd(Integer.parseInt(id), jsonString);
			
			return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(15000, "", "system error");
	}
	
    
	@DELETE
	@Path("/{id}")
	public String deleteeachercourse(@PathParam("id")  String id){
		
		try {
    		
			return pT.del(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(15000, "", "system error");
	}
	
	 @GET
	 @Path("/login-id/{id}")
	 public String getTeacherAllCourse(@PathParam("id")  String id){
		 
		 try {
			 
			 return pT.selTeacherAllCourse(Integer.parseInt(id));
				
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 log.error(e);
		 }
		 return Cause.getFailcode(15000, "", "system error");
	 }
	 
	 @GET
	 @Path("/{id}")
	 public String searchInfo(@PathParam("id") String id)
	 {
		 try {
			
			 return pT.selone(Integer.parseInt(id));
			
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 log.error(e);
		 }
		
		 return Cause.getFailcode(15000, "", "system error");
	 }
}
