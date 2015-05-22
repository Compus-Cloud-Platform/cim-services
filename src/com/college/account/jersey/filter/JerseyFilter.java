package com.college.account.jersey.filter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
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

		StringBuffer s = servletRequest.getRequestURL();
		
		if(s.toString().contains("login")){
			
			Map para = arg0.getFormParameters();
			/* login  取出 cookie */
			@SuppressWarnings("rawtypes")
			Map cmap = arg0.getCookies();
			
			Set<String> key = cmap.keySet();
			
			if(key.contains("id"));
		}
		
		Map smap = arg0.getCookies();
		
		
		Cookie[] array = servletRequest.getCookies();
		
		Set<String> key = smap.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String ss = (String) it.next();
            System.out.println(ss);
            System.out.println(smap.get(ss));
        }
        
       // if(null != arg0){
       // 	Response response = Response.ok("{99:\"error\"}").status(401).type(MediaType.APPLICATION_JSON).build();  
       //     throw new WebApplicationException(response); // Throw new UnAuthorized  
       // }
        
		//System.out.println(smap);
		
		return arg0;
	}

}
