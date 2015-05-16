package com.college.account.jersey;

import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;



import com.college.account.service.DaoDeptOrgService;
import com.college.account.service.DaoMajorDeptService;
import com.college.account.service.DaoOrganizationService;
import com.college.account.service.DaoUserAdminOrgService;
import com.college.account.service.DaoUserStudentMajorService;
import com.college.account.service.DaoUserTeacherDeptService;
import com.college.account.service.DaoUsersService;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.ServiceFactoryBean;


@Path("/organizations/users")
public class UserRelationRestService {
	
	private static DaoUsersService p = ServiceFactoryBean.getUserService();
	private static DaoDeptOrgService pDO = ServiceFactoryBean.getDeptOrgService();
	private static DaoMajorDeptService pMD = ServiceFactoryBean.getMajorDeptService();
	private static DaoOrganizationService pO = ServiceFactoryBean.getOrganizationService();
	
	private static DaoUserAdminOrgService pUA = ServiceFactoryBean.getUserAdminOrgService();
	private static DaoUserStudentMajorService pUS = ServiceFactoryBean.getUserStudentMajorService();
	private static DaoUserTeacherDeptService pUT = ServiceFactoryBean.getUserTeacherDeptService();

	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public String addRelationship(@PathParam("id") String id,
			                                String jsonString){
		
		try {
			Integer deptOrgId = null;
			Integer majorDeptId = null;
			Integer orgId = null;
			@SuppressWarnings("unchecked")
			Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
			
			if(null != map.get("deptOrgId"))deptOrgId = Integer.parseInt(map.get("deptOrgId").toString());
			if(null != map.get("majorDeptId"))majorDeptId = Integer.parseInt(map.get("majorDeptId").toString());
			if(null != map.get("orgId"))orgId = Integer.parseInt(map.get("orgId").toString());
			
			if(!checkId(Integer.parseInt(id), orgId, deptOrgId, majorDeptId)){
				return Cause.getFailcode(999, "id", "id can not find");
			}
			
			if(null != deptOrgId){
				return pUT.save(Integer.parseInt(id), deptOrgId, null);
			}
			
			if(null != majorDeptId){
				return pUS.save(Integer.parseInt(id), majorDeptId, null);
			}
			
			if(null != orgId){
				return pUA.save(Integer.parseInt(id), orgId, null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Cause.getFailcode(1000, "", "system error");
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public String delRelationship(@PathParam("id") String id,
			                                String jsonString){
		
		try {
			Integer deptOrgId = null;
			Integer majorDeptId = null;
			Integer orgId = null;
			@SuppressWarnings("unchecked")
			Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
			
			if(null != map.get("deptOrgId"))deptOrgId = Integer.parseInt(map.get("deptOrgId").toString());
			if(null != map.get("majorDeptId"))majorDeptId = Integer.parseInt(map.get("majorDeptId").toString());
			if(null != map.get("orgId"))orgId = Integer.parseInt(map.get("orgId").toString());
			
			if(!checkId(Integer.parseInt(id), orgId, deptOrgId, majorDeptId)){
				return Cause.getFailcode(999, "id", "id can not find");
			}
			
			if(null != deptOrgId){
				return pUT.del(Integer.parseInt(id), deptOrgId, null);
			}
			
			if(null != majorDeptId){
				return pUS.del(Integer.parseInt(id), majorDeptId, null);
			}
			
			if(null != orgId){
				return pUA.del(Integer.parseInt(id), orgId, null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Cause.getFailcode(1000, "", "system error");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	public String getRelationship(String jsonString){
		
		try {
			Integer deptOrgId = null;
			Integer majorDeptId = null;
			Integer orgId = null;
			@SuppressWarnings("unchecked")
			Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
			
			if(null != map.get("deptOrgId"))deptOrgId = Integer.parseInt(map.get("deptOrgId").toString());
			if(null != map.get("majorDeptId"))majorDeptId = Integer.parseInt(map.get("majorDeptId").toString());
			if(null != map.get("orgId"))orgId = Integer.parseInt(map.get("orgId").toString());
			
			if(null != deptOrgId){
				List<Integer> list = pUT.sel(deptOrgId, null);
				return p.getUserByListid(list);
			}
			
			if(null != majorDeptId){
				List<Integer> list = pUS.sel(majorDeptId, null);
				return p.getUserByListid(list);
			}
			
			if(null != orgId){
				List<Integer> list = pUA.sel(orgId, null);
				return p.getUserByListid(list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Cause.getFailcode(1000, "", "system error");
	}
	
	boolean checkId(Integer userId, Integer orgId, Integer deptOrgId, Integer majorDeptId){
		if(null != userId){
			if(null == p.searchByid(userId, DaoUsersService.tablename)){
				return false;
			}
		}
		
		if(null != orgId){
			if(null == pO.searchByid(orgId, DaoOrganizationService.tablename)){
				return false;
			}
		}
		
		if(null != deptOrgId){
			if(null == pDO.searchByid(deptOrgId, DaoDeptOrgService.tablename)){
				return false;
			}
		}
		
		if(null != majorDeptId){
			if(null == pMD.searchByid(majorDeptId, DaoMajorDeptService.tablename)){
				return false;
			}
		}
		return true;
	}
}
