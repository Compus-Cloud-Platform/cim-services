package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;

import com.college.account.bean.Department;
import com.college.account.bean.DeptOrg;
import com.college.util.Cause;
import com.college.util.ServiceFactoryBean;

public class DaoDeptOrgService extends  DaoService<DeptOrg>{

	public String tablename = "DeptOrg";
	public int NOTFIND = 11001;
	
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
		
		List<Object> listDep = new ArrayList<Object>(1);
		
		DaoDepartmentService pD = ServiceFactoryBean.getDepartmentService();
		
		for(Object obj:list){
			DeptOrg deptorg = (DeptOrg)obj;
			
			if(deptorg.getDeptId() == Integer.parseInt(depId)){
				listDep.add(pD.selObj(deptorg.getDeptId()));
			}
			
		}
		
		return Cause.getData(listDep);
	}
}
