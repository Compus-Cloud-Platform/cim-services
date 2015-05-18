package com.college.account.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.college.account.service.DaoStudentCourseService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/studentscourses/{id}")
public class StudentCourseRestService {

	private static DaoStudentCourseService p = ServiceFactoryBean.getStudentCourseService();
	private static final Logger log = Logger4j.getLogger(StudentCourseRestService.class);
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON) 
	public String updateInfo(@PathParam("id") String id,
			                 String jsonString){
		try {
			
			return p.upd(Integer.parseInt(id), jsonString, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(16000, "", "system error");
	}
	
	@DELETE
	public String deleteInfo(@PathParam("id") String id){
		try {
			
			return p.del(Integer.parseInt(id), null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(16000, "", "system error");
	}
}
