package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.college.account.bean.Department;
import com.college.account.bean.DeptOrg;
import com.college.util.Cause;

public class DaoDeptOrgService extends  DaoService<DeptOrg>{

	public static String tablename = "DeptOrg";
	public static int NOTFIND = 11001;
	public static int DEPORGPARAMERROR = 5004;
	public static int DEPORGDEPERGIDERROR = 5005;
	public static int DEPORGEXIST = 5006;
	
	private DaoDepartmentService departmentService;
	
	public void setDepartmentService(DaoDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public String  save(Integer orgId, Integer depId, Integer operId){
		
		DeptOrg deptorg = new DeptOrg(depId, orgId, new Date(), operId);
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
		
		List<Object> listDep = new ArrayList<Object>();
		

		for(Object obj:list){
			DeptOrg deptorg = (DeptOrg)obj;
			listDep.add(departmentService.selObj(deptorg.getDeptId()));
		}
		
		return Cause.getSpeicalData(listDep, list);
	}
	
	public String getOne(String id){
		
		DeptOrg deptorg = null;
		
		deptorg = searchByid(Integer.parseInt(id), tablename);
		
		if(null == deptorg){
			
			return Cause.getFailcode(DEPORGPARAMERROR, "id", "can not find this data");
		}
		
		Department department= departmentService.searchByid(deptorg.getDeptId(), DaoDepartmentService.tablename);
		
		List<Object> list = new ArrayList<Object>();
		list.add(department);
		
		return Cause.getData(list);
	}
	
	public Integer getIsRight(String orgId, String depId){
		
		DeptOrg deptorg = null;
		
		deptorg = searchByMutiFeild(tablename, "deptIdorgId", new Object[]{Integer.parseInt(depId), Integer.parseInt(orgId)});
		
		if(null == deptorg) return null;
		
		return deptorg.getId();
	}
	

}
