package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.DeptOrg;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.ServiceFactoryBean;

public class DaoDeptOrgService extends  DaoService<DeptOrg>{

	public static String tablename = "DeptOrg";
	public int NOTFIND = 11001;
	private int DEPORGPARAMERROR = 5004;
	private int DEPORGDEPERGIDERROR = 5005;
	
	public void save(Integer orgId, Integer depId, Integer operId){
		
		DeptOrg deptorg = new DeptOrg(depId, orgId, new Date(), operId);
		create(deptorg);
		return;
	}
	
	public String del(String id, String idD){
		
		DeptOrg deptorg = null;
		
		deptorg = searchByMutiFeild(tablename, "deptIdorgId", new Object[]{Integer.parseInt(idD), Integer.parseInt(id)});
		
		if(null == deptorg){
			return Cause.getFailcode(NOTFIND, "id", "not find");
		}
		
		delete(DeptOrg.class, deptorg.getId());
		
		return Cause.getSuccess(deptorg.getId());
	}
	
	public String get(String orgId){
		
		List<Object> list = searchByFeildList(tablename, "orgId", Integer.parseInt(orgId));
		
		List<Object> listDep = new ArrayList<Object>();
		
		DaoDepartmentService pD = ServiceFactoryBean.getDepartmentService();
		
		for(Object obj:list){
			DeptOrg deptorg = (DeptOrg)obj;
			listDep.add(pD.selObj(deptorg.getDeptId()));
		}
		
		return Cause.getData(listDep);
	}
	
	public String getOne(String orgId, String depId){
		
		List<Object> list = searchByFeildList(tablename, "orgId", Integer.parseInt(orgId));
		
		List<Object> listDep = new ArrayList<Object>();
		
		DaoDepartmentService pD = ServiceFactoryBean.getDepartmentService();
		
		for(Object obj:list){
			DeptOrg deptorg = (DeptOrg)obj;
			
			if(deptorg.getDeptId() == Integer.parseInt(depId)){
				listDep.add(pD.selObj(deptorg.getDeptId()));
			}
			
		}
		
		if(listDep.size() == 0){
			return Cause.getFailcode(DEPORGPARAMERROR, "id", "can not find this data");
		}
		
		return Cause.getData(listDep);
	}
	
	public Integer getIsRight(String orgId, String depId){
		
		DeptOrg deptorg = null;
		
		deptorg = searchByMutiFeild(tablename, "deptIdorgId", new Object[]{Integer.parseInt(depId), Integer.parseInt(orgId)});
		
		return deptorg.getId();
	}
	
	public String isJudge(String id, String idD, String jsonString) throws Exception{
		
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
		
		if(null == map.get("deptOrgMapId") || null == map.get("majorId")){
			return  Cause.getFailcode(DEPORGPARAMERROR, "json", "json param error");
		}
		
		Integer idsearch = Integer.parseInt(map.get("deptOrgMapId").toString());
		
		DeptOrg deptorg = searchByid(idsearch, DaoDeptOrgService.tablename);
		if(Integer.parseInt(id) != deptorg.getOrgId() ||
				Integer.parseInt(idD) != deptorg.getDeptId()){
			return Cause.getFailcode(DEPORGDEPERGIDERROR, "deptOrgMapId", "deptOrgMapId error");
		}
		
		return Cause.getSuccess(idsearch);
	}
}
