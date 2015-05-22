package com.college.account.jersey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String login(String jsonString){
		
		try {		
			
			return p.login(jsonString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(1000, "", "system error");
	}
	
}
