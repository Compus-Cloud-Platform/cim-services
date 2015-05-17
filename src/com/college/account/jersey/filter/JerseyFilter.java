package com.college.account.jersey.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

@Provider
public class JerseyFilter implements ContainerRequestFilter{

	 @Context   
	 private HttpServletRequest servletRequest;  
	 @Context  
	 private HttpServletResponse servletResponse;
	    
	@Override
	public ContainerRequest filter(ContainerRequest arg0) {
		// TODO Auto-generated method stub
		 StringBuffer  s = servletRequest.getRequestURL();
		 
		Map smap = arg0.getCookies();
		
		return arg0;
	}

}
