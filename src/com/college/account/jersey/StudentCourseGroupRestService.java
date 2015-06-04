package com.college.account.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.college.account.service.DaoStudentCourseGroupService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("/student-course-groups")
public class StudentCourseGroupRestService {

	private static DaoStudentCourseGroupService p = ServiceFactoryBean.getStudentCourseGroupService();
	private static final Logger log = Logger4j.getLogger(StudentCourseGroupRestService.class);
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(String jsonString){
		try {
	
			return p.save(jsonString, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(20000, "", "system error");
	}
	
	 @DELETE
	 @Path("/{id}")
	 public String deleteInfo(@PathParam("id") String id){
		 try {
			
			 String result=  p.del(Integer.parseInt(id));
			 return result;
			
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 log.error(e);
		 }
		
		 return Cause.getFailcode(20000, "", "system error");
	 }
	 
	 @GET
	 @Path("/tearcher-course-group-id/{id}")
	 public String getGroupstudentInfo(@PathParam("id") String id){
		 try {
			
			 String result=  p.getGroupstudentInfo(Integer.parseInt(id));
			 return result;
			
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 log.error(e);
		 }
		
		 return Cause.getFailcode(20000, "", "system error");
	 }
}
