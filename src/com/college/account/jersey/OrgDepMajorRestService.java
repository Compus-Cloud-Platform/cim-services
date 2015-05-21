package com.college.account.jersey;


import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import com.college.account.service.DaoMajorDeptOrgService;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/organization-department-majors")
public class OrgDepMajorRestService {
	
	private static DaoMajorDeptOrgService p = ServiceFactoryBean.getMajorDeptService();
	
    private static final Logger log = Logger4j.getLogger(OrgDepMajorRestService.class);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(String jsonString){
    	
    	try {
    		@SuppressWarnings("unchecked")
    		Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
    		
    		if(null == map.get("majorId") || null == map.get("deptOrgId")){
    			return Cause.getFailcode(5000, "", "param error");
    		}
    		
    		String id = map.get("majorId").toString();
    		String idD = map.get("deptOrgId").toString();
			
			return p.save(Integer.parseInt(id), Integer.parseInt(idD), null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(5000, "", "system error");
    }
    
    @DELETE
    @Path("/{id}")
	public String deleteInfo(@PathParam("id") String id){
    	
    	try {
			
			return p.del(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
    	
    	return Cause.getFailcode(5000, "", "system error");
    }
    
    
    @GET
    @Path("/detp-org-id/{id}")
	public String searchInfo(@PathParam("id") String id){
    	
    	try {
			/* dep 与  org 构成一级级联 为了不至于层次太深 所以 不采取二级级联  */
			return p.getMajorBylistId(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
    	
    	return Cause.getFailcode(5000, "", "system error");
    }
    
    @GET
    @Path("/{id}")
	public String searchOneInfo(@PathParam("id") String id){
    	
    	try {
			
			return p.getMajorByRelationID(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
    	
    	return Cause.getFailcode(5000, "", "system error");
    }

}
