package com.college.account.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.college.util.Logger4j;

@Path("/v1")
public class UserLoginRestService {

	private static final Logger log = Logger4j.getLogger(UserLoginRestService.class);
	
	@GET
    @Path("/users")
    @Consumes({MediaType.APPLICATION_JSON})
	public String loginNamePwd(@QueryParam("userName") String userName,
			                   @QueryParam("password") String password) {
		return userName+password;
	}
}
