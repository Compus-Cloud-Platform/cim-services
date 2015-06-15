package com.college.account.jersey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.college.account.service.DaoUsersService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/login")
public class UserLoginRestService {

	private static final Logger log = Logger4j.getLogger(UserLoginRestService.class);
	
	private static DaoUsersService p = ServiceFactoryBean.getUserService();
	
	@Context 
	HttpServletRequest request; 
	
	@Context  
    HttpServletResponse servletResponse; 
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String login(String jsonString){
		
		try {
		    
		    String s = p.login(jsonString, request, servletResponse);
			
			return s;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(1000, "", "system error");
	}
	
	@POST
	@Path("/forget/{loginId}")
	public String forgetPasswd(@PathParam("loginId")  String loginId){

		try {
		    
		    String s = p.forgetPasswd(loginId);
			
			return s;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(1000, "", "system error");
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	@Path("/identify/{loginId}")
	public String identifyingCode(@PathParam("loginId")  String loginId,
			                      String jsonString ){
		
		try {
		    
		    String s = p.updatePasswd(loginId, jsonString);
			
			return s;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(1000, "", "system error");
	}
	
}
