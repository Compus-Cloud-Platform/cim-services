package com.college.account.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;


import com.college.account.bean.Department;
import com.college.account.bean.DeptOrg;
import com.college.account.bean.Organization;
import com.college.account.bean.Users;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Obj2Map;

public class DaoDeptOrgService extends  DaoService<DeptOrg>{

	public static String tablename = "DeptOrg";
	public static int NOTFIND = 11001;
	public static int DEPORGPARAMERROR = 5004;
	public static int DEPORGDEPERGIDERROR = 5005;
	public static int DEPORGEXIST = 5006;
	

	private DaoDepartmentService departmentService;

	private DaoOrganizationService organizationservice;
	
	public void setOrganizationservice(DaoOrganizationService organizationservice) {
		this.organizationservice = organizationservice;
	}

	public void setDepartmentService(DaoDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public String  save(Integer orgId, Integer depId, Integer operId){
		
		Department department = (Department)departmentService.searchByid(depId, DaoDepartmentService.tablename);
		
		Organization organization = (Organization)organizationservice.searchByid(orgId, DaoOrganizationService.tablename);
		
		if(null == department || null == organization){
			return Cause.getFailcode(NOTFIND, "id", "org id or dep id not find");
		}
		
		DeptOrg deptorg = new DeptOrg( department, organization, new Date(), operId);
		
		create(deptorg);
		
		return Cause.getSuccess(deptorg.getId());
	}
	
	public String del(String id){
		
		DeptOrg deptorg = null;
		
		deptorg = searchByid(Integer.parseInt(id), tablename);
		
		if(null == deptorg){
			
			return Cause.getFailcode(NOTFIND, "id", "not find");
		}
		
		delete(DeptOrg.class, deptorg.getId());
		
		return Cause.getSuccess(deptorg.getId());
	}
	
	public String get(String orgId){
		
		List<Object> list = searchByFeildList(tablename, "orgId", Integer.parseInt(orgId));
		
		return Cause.getStringData(list, DeptOrg.class);
		
	}
	
	public String getOne(String id){
		
		DeptOrg deptorg = null;
		
		deptorg = searchByid(Integer.parseInt(id), tablename);
		
		List<Object> list = new ArrayList<Object>();
		
		list.add(deptorg);
		
		return Cause.getStringData(list, DeptOrg.class);
	}
	
	public Integer getIsRight(String orgId, String depId){
		
		DeptOrg deptorg = null;
		
		deptorg = searchByMutiFeild(tablename, "deptIdorgId", new Object[]{Integer.parseInt(depId), Integer.parseInt(orgId)});
		
		if(null == deptorg) return null;
		
		return deptorg.getId();
	}
	

}
