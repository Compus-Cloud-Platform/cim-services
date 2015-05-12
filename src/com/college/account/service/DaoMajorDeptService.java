package com.college.account.service;

import java.util.Date;
import com.college.account.bean.MajorDept;
import com.college.util.Cause;

public class DaoMajorDeptService extends  DaoService<MajorDept>{
	
	private String tablename = "MajorDept";
	
	private int MAJORDEPTNOTFIND = 12001;
	
	public String save(Integer majorId, Integer deptOrgId, Integer operId){
		
		MajorDept majordept = new MajorDept(majorId, deptOrgId, new Date(), operId);
		
		Integer id = create(majordept);
		
		return Cause.getSuccess(id);
	}
	
	
	public String del(String majorId, String deptOrgId){
		
		MajorDept majordept = null;
		
		majordept = searchByMutiFeild(tablename, "majorIddeptOrgId", new Object[]{Integer.parseInt(majorId), Integer.parseInt(deptOrgId)});
		
		if(null == majordept){
			
			return Cause.getFailcode(MAJORDEPTNOTFIND, "id", "not find");
		}
		
		delete(MajorDept.class, majordept.getId());
		
		return Cause.getSuccess(majordept.getId());
	}

}
