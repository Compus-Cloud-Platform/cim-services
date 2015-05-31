package com.college.account.jersey.filter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
	
	@Context  
    private HttpServletResponse servletResponse; 
	
	@Override
	public ContainerRequest filter(ContainerRequest arg0) {

		StringBuffer s = servletRequest.getRequestURL();
		
		if(!s.toString().contains("login")){
		    
		    Map<String, javax.ws.rs.core.Cookie> cmap = arg0.getCookies();
	        
	        Set<String> key = cmap.keySet();
	        
	        String userid = null;
	        if(key.contains("id")){
	            
	            javax.ws.rs.core.Cookie obj = (javax.ws.rs.core.Cookie)cmap.get("id");
	            userid = obj.getValue();
	        }else{
	            Response response = Response.ok("{\"error\":{\"field\":\"cookies\",\"description\":\"lost para\",\"code\":1},\"ack\":\"failure\"}").status(401).type(MediaType.APPLICATION_JSON).build();  
	            throw new WebApplicationException(response);
	        }
	        
	        HttpSession session = servletRequest.getSession();
	            
	        Object obj = session.getAttribute(userid);
	        if(null == obj){
	            Response response = Response.ok("{\"error\":{\"field\":\"cookies\",\"description\":\"no auth\",\"code\":1},\"ack\":\"failure\"}").status(401).type(MediaType.APPLICATION_JSON).build();  
	            throw new WebApplicationException(response);
	        }
	        
	        servletResponse.addHeader("id", userid);
		}
		
		return arg0;
	}

}
