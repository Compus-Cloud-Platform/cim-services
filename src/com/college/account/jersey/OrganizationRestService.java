package com.college.account.jersey;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.college.account.bean.Organization;
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
        try
        {
            @SuppressWarnings("unchecked")
            List<Organization> lists = ServiceFactoryBean.getOrganizationService().findAllOrganizations();
            
            ObjectMapper mapper = new ObjectMapper();
            Map<String, List<Organization>> p =new HashMap<String, List<Organization>>();
            p.put("Organization", lists);
            result =  mapper.writeValueAsString(p).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Path("/createOrganization")
    public String createInfo(@QueryParam("jsonString") String jsonString) 
    {
        Integer id = null;
        try
        {
            //Organization organization = new Organization("上海交通大学", "10248", "上海", "上海交通大学是我国历史最悠久的高等学府之一，是教育部直属、教育部与上海市共建的全国重点大学。", new Date(), new Date(), 22);
            //id = ServiceFactoryBean.getOrganizationService().createOrganization(organization);
        }
        catch (Exception e) {
            log.error("Save organization failed.");
            log.error(e);
        }
        System.out.println(id);
        return "success";
    }
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteOrganization")
    public String deleteInfo(@QueryParam("id") String idString)
    {
        Integer id = Integer.valueOf(idString);
        ServiceFactoryBean.getOrganizationService().deleteOrganization(Organization.class, id);
        return "success";
    }
}
