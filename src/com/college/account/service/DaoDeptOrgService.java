package com.college.account.service;

import java.util.Date;

import javax.ws.rs.PathParam;

import com.college.account.bean.DeptOrg;
import com.college.util.Cause;

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
}
