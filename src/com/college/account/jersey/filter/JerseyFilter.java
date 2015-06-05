package com.college.account.jersey.filter;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

@Provider
public class JerseyFilter implements ContainerRequestFilter{

	@Context   
    private HttpServletRequest servletRequest;  
	
	@Override
	public ContainerRequest filter(ContainerRequest arg0) {

		StringBuffer s = servletRequest.getRequestURL();
		
		if(!s.toString().contains("login")){

			Object temp  = servletRequest.getHeader("id");
			
			if(null == temp){
				Response response = Response.ok("{\"error\":{\"field\":\"header id\",\"description\":\"lost para\",\"code\":1},\"ack\":\"failure\"}").status(401).type(MediaType.APPLICATION_JSON).build();  
	            throw new WebApplicationException(response);
			}
			
	        Object obj = Session.getAttribute(temp.toString());
	        
	        if(null == obj){
	            Response response = Response.ok("{\"error\":{\"field\":\"header id\",\"description\":\"no auth\",\"code\":1},\"ack\":\"failure\"}").status(401).type(MediaType.APPLICATION_JSON).build();  
	            throw new WebApplicationException(response);
	        }
	        
		}
		
		return arg0;
	}

}
