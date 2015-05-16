package com.college.account.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;


import com.college.account.service.DaoTeacherCourseService;
import com.college.account.service.DaoUsersService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("/teachers/{id}/courses")
public class TeacherCourseRestService {

	private static DaoTeacherCourseService p = ServiceFactoryBean.getTeacherCourseService();
	private static DaoUsersService pU = ServiceFactoryBean.getUserService();
    private static final Logger log = Logger4j.getLogger(TeacherCourseRestService.class);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(@PathParam("id") String id,
			           String jsonString){
    	
    	try {

    		if(!pU.isExist(Integer.parseInt(id))){
    			
    			return Cause.getFailcode(DaoUsersService.USEIDNOTEXIST, "Id", "id not find");
    		}
			
			return p.save(Integer.parseInt(id),jsonString, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(15000, "", "system error");
    }
}
