package com.college.account.jersey;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import com.college.account.bean.Organization;
import com.college.util.Json2Obj;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("/organization")
public class OrganizationRestService
{
    private static final Logger log = Logger4j.getLogger(OrganizationRestService.class);
    @GET
    @Path("/getOrganizations")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getInfo() 
    {
        String result = null;
    
        return result;
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)  
    @Path("/save")
    public String createInfo(@QueryParam("jsonString") String jsonString) 
    {
        Integer id = null;
        
        try
        {
        	Organization orgObj = (Organization)Json2Obj.getObj(jsonString, Organization.class);
        	id = ServiceFactoryBean.getOrganizationService().create(orgObj);
        }
        catch (Exception e) {
            log.error("Save organization failed.");
            log.error(e);
        }
        System.out.println(id);
        return "success";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Path("/save")
    public String createInfoPost(@QueryParam("jsonString") String jsonString) {
    	return createInfo(jsonString);
    }
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteOrganization")
    public String deleteInfo(@QueryParam("id") String idString)
    {
        return "success";
    }
}
