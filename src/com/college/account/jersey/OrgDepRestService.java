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
import com.college.account.service.DaoDeptOrgService;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("/organization-departments")
public class OrgDepRestService {

    private static DaoDeptOrgService pDO = ServiceFactoryBean.getDeptOrgService();
    private static final Logger log = Logger4j.getLogger(OrgDepRestService.class);
    

	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
    public String save(String jsonString){
		
		try {
			/* 转化为json */
			Map<?, ?> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
			
			String orgId = (null== map.get("orgId"))?null:map.get("orgId").toString();
            String depId = (null== map.get("deptId"))?null:map.get("deptId").toString();
            
            if(null == orgId || null == depId){
            	return Cause.getFailcode(3000, "", "param error");
            }
			
			if(null != pDO.getIsRight(orgId, depId)){
				
				return Cause.getFailcode(DaoDeptOrgService.DEPORGEXIST, "id", "id has exist");
			}
			
			return pDO.save(Integer.parseInt(orgId), Integer.parseInt(depId), null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(3000, "", "system error");
	}
    @DELETE
    @Path("/{id}")
    public String deleteDep(@PathParam("id") String id){
    	
    	try{
    		return pDO.del(id);
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(3000, "", "system error");
    	
    }
    
    @GET
    @Path("/org-id/{id}")
    public String getDep(@PathParam("id") String id){
    	try{
    		return pDO.get(id);
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(3000, "", "system error");
    }
    
    @GET
    @Path("/{id}")
    public String getOneDep(@PathParam("id") String id){
    	try{
    		
    		return pDO.getOne(id);
    		
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(3000, "", "system error");
    }	
	

}
