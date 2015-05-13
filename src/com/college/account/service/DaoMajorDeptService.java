package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.college.account.bean.MajorDept;
import com.college.util.Cause;

public class DaoMajorDeptService extends  DaoService<MajorDept>{
	
	public static String tablename = "MajorDept";
	
	private int MAJORDEPTNOTFIND = 12001;
	private int MAJORIDWEONG = 12002;
	
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
	
	public List<Integer> getDepAllMajor(Integer orgDepId){
		
		List<Object> list = searchByFeildList(tablename, "deptOrgId", orgDepId);
		
		List<Integer> resultlist = new ArrayList<Integer>();
		for(Object temp :list){
			MajorDept majordept = (MajorDept)temp;
			resultlist.add(majordept.getMajorId());
		}
		
		return resultlist;
	}
	
	public String getDepOneMajor(Integer orgDepId, Integer majorId){
		
		MajorDept majordept = null;
		
		majordept = searchByMutiFeild(tablename, "majorIddeptOrgId", new Object[]{majorId, orgDepId});
		
		if(null == majordept){
			return Cause.getFailcode(MAJORIDWEONG, "majorId", "major id wrong");
		}
		
		return Cause.getSuccess(majordept.getId());
	}

}
