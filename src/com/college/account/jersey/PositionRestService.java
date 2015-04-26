package com.college.account.jersey;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.college.account.bean.Position;
import com.college.util.JacksonUtils;
import com.college.util.Json2Obj;
import com.college.util.Logger4j;
import com.college.util.Obj2Map;
import com.college.util.ServiceFactoryBean;


@Path("/position")
public class PositionRestService {
	// error code  from 3000

    private static final Logger log = Logger4j.getLogger(PositionRestService.class);
    
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)  
    @Path("/save")
    public String createGet(@QueryParam("jsonString") String jsonString) 
    {
        Integer id = null;
        Map resultMap = new HashMap();
        Map dataMap = new HashMap();
        
        try
        {	
        			
        	Position posiObj = (Position)Json2Obj.getObj(jsonString, Position.class);
        	
        	String name = posiObj.getName();
        	Date time = posiObj.getCreateTime();
        	Integer operid = posiObj.getOperId();
        	
        	if(null == name || !StringUtils.isNotBlank(name)){
        		resultMap.put("ack", "failure");
    			dataMap.put("code", 3001);
    			dataMap.put("field", "name");
    			dataMap.put("description", "name not exist or null");
    			resultMap.put("errors", dataMap);
        	}else if(null == time){
        		resultMap.put("ack", "failure");
    			dataMap.put("code", 3005);
    			dataMap.put("field", "createTime");
    			dataMap.put("description", "canot null");
    			resultMap.put("errors", dataMap);
        	}else if(null == operid){
        		resultMap.put("ack", "failure");
    			dataMap.put("code", 3006);
    			dataMap.put("field", "operId");
    			dataMap.put("description", "oper_id canot null");
    			resultMap.put("errors", dataMap);
        	}else{
        		id = ServiceFactoryBean.getPositionService().create(posiObj);
        		resultMap.put("ack", "success");
    			dataMap.put("id", id);
    			resultMap.put("data", dataMap);
        	}
        	
        }
        catch (Exception e) {
            log.error("Save Position failed.");
            log.error(e);
        }
        
        return JacksonUtils.getJsonString(resultMap);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Path("/save")
    public String createPost(@QueryParam("jsonString") String jsonString) {
    		
    	return createGet(jsonString);
    }
    
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/update")
    public String updInfoGet(@QueryParam("jsonString") String jsonString)
    {
    	Position posiObj = null;
    	Position posiObjFind = null;
    	Map resultMap = new HashMap();
        Map dataMap = new HashMap();
    	
        posiObj = (Position)Json2Obj.getObj(jsonString, Position.class);
    	
    	if(null != posiObj){
    		posiObjFind = ServiceFactoryBean.getPositionService().searchById(posiObj.getId());
    	}
 
    	if(null == posiObj || null == posiObjFind){
    		
    		resultMap.put("ack", "failure");
			dataMap.put("code", 3002);
			dataMap.put("field", "id");
			dataMap.put("description", "id not find or no id");
			resultMap.put("errors", dataMap);
    	}else if(null != posiObj.getName() && !StringUtils.isNotBlank(posiObj.getName())){
    		resultMap.put("ack", "failure");
			dataMap.put("code", 3004);
			dataMap.put("field", "name");
			dataMap.put("description", "can not change to blank");
			resultMap.put("errors", dataMap);
    	}else{
    		
    		Json2Obj.repalceDiffObjMem(posiObj, posiObjFind, Position.class);
    				
    		ServiceFactoryBean.getPositionService().update(posiObjFind);
    		
    		resultMap.put("ack", "success");
    	}
    	
    	return JacksonUtils.getJsonString(resultMap);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/update")
    public String updInfoPost(@QueryParam("jsonString") String jsonString)
    {
    	return updInfoGet(jsonString);
    }
    
    
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/delete")
    public String deleteInfoGet(@QueryParam("id") String id)
    {
    	Position posiObj = null;
    	Map resultMap = new HashMap();
        Map dataMap = new HashMap();
        Integer idtemp = Integer.valueOf(id);
    	
    
        posiObj = ServiceFactoryBean.getPositionService().searchById(idtemp);

    	//ServiceFactoryBean.getOrganizationService().
    	if(null == posiObj){
    		resultMap.put("ack", "failure");
			dataMap.put("code", 4002);
			dataMap.put("field", "id");
			dataMap.put("description", "id =" + id + " not find");
			resultMap.put("errors", dataMap);
    	}else{
    		
    		ServiceFactoryBean.getPositionService().delete(idtemp);
    		resultMap.put("ack", "success");
    	}
    	
    	return JacksonUtils.getJsonString(resultMap);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/delete")
    public String deleteInfoPost(@QueryParam("id") String id)
    {
        
        return deleteInfoGet(id);
    }
    
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/search")
    public String getInfoGet(@QueryParam("id") String id){
    	Integer idtemp = Integer.valueOf(id);
    	
    	Map resultMap = new HashMap();
        Map dataMap = new HashMap();
        
        Position posiObj = null;
        
        posiObj= ServiceFactoryBean.getPositionService().searchById(idtemp);
        
        if(null == posiObj){
        	resultMap.put("ack", "failure");
			dataMap.put("code", 4002);
			dataMap.put("field", "id");
			dataMap.put("description", "id =" + id + " not find");
			resultMap.put("errors", dataMap);
        }else{
        	
        	Map posiMap = Obj2Map.toMap(posiObj, Position.class);
			List data = new ArrayList();
			data.add(posiMap);
			resultMap.put("ack", "success");
			resultMap.put("data", data);
			resultMap.put("datanum", 1);
        }
        
        return JacksonUtils.getJsonString(resultMap);
    }
}
