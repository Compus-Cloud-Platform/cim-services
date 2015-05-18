package com.college.account.jersey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.college.account.service.DaoCourseService;
import com.college.account.service.DaoTeacherCourseService;
import com.college.account.service.DaoUsersService;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/teachers/{id}")
public class TeacherRestService {

	private static DaoTeacherCourseService p = ServiceFactoryBean.getTeacherCourseService();
	private static DaoUsersService pU = ServiceFactoryBean.getUserService();
	private static DaoCourseService pD = ServiceFactoryBean.getCourseService();
    private static final Logger log = Logger4j.getLogger(TeacherRestService.class);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Path("/courses")
	public String save(@PathParam("id") String id,
			           String jsonString){
    	
    	try {

    		if(!pU.isExist(Integer.parseInt(id))){
    			
    			return Cause.getFailcode(DaoUsersService.USEIDNOTEXIST, "Id", "id not find");
    		}
    		
    		@SuppressWarnings("unchecked")
			Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
    		
    		if(null == map.get("courseId")){
    			return Cause.getFailcode(DaoCourseService.COURSEIDNOTFIND, "Id", "course id not exist");
    		}
    		
    		if(!pD.selIsExist(Integer.parseInt(map.get("courseId").toString()))){
    			return Cause.getFailcode(DaoCourseService.COURSEIDNOTFIND, "Id", "course id not find");
    		}
			
			return p.save(Integer.parseInt(id),jsonString, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(15000, "", "system error");
    }
    
    @GET
    @Path("/courses")
	public String searchInfo(@PathParam("id") String id)
	{
		try {
			
			List<Integer> courselist =  p.sel(Integer.parseInt(id));
			
			Map<Integer, Integer> idMap = new HashMap<Integer, Integer>();
			/* 剔除重复的id */
			for(int i =0; i < courselist.size(); ++i){
				idMap.put(courselist.get(i), i);
			}
			
			List<Integer> relist = new ArrayList<Integer>();
			
			for (Integer key : idMap.keySet()) {
				relist.add(key);
			}
			
			return pD.getList(relist);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(15000, "", "system error");
		
	}
}
