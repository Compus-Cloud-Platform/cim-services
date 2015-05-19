package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.college.account.bean.MajorDeptOrg;
import com.college.util.Cause;

public class DaoMajorDeptOrgService extends  DaoService<MajorDeptOrg>{
	
	public static String tablename = "MajorDeptOrg";
	
	private int MAJORDEPTNOTFIND = 12001;
	private int MAJORIDWEONG = 12002;
	
	public String save(Integer majorId, Integer deptOrgId, Integer operId){
		
		MajorDeptOrg majordept = new MajorDeptOrg(majorId, deptOrgId, new Date(), operId);
		
		Integer id = create(majordept);
		
		return Cause.getSuccess(id);
	}
	
	
	public String del(String majorId, String deptOrgId){
		
		MajorDeptOrg majordept = null;
		
		majordept = searchByMutiFeild(tablename, "majorIddeptOrgId", new Object[]{Integer.parseInt(majorId), Integer.parseInt(deptOrgId)});
		
		if(null == majordept){
			
			return Cause.getFailcode(MAJORDEPTNOTFIND, "id", "not find");
		}
		
		delete(MajorDeptOrg.class, majordept.getId());
		
		return Cause.getSuccess(majordept.getId());
	}
	
	public List<Integer> getDepAllMajor(Integer orgDepId){
		
		List<Object> list = searchByFeildList(tablename, "deptOrgId", orgDepId);
		
		List<Integer> resultlist = new ArrayList<Integer>();
		for(Object temp :list){
			MajorDeptOrg majordept = (MajorDeptOrg)temp;
			resultlist.add(majordept.getMajorId());
		}
		
		return resultlist;
	}
	
	public String getDepOneMajor(Integer orgDepId, Integer majorId){
		
		MajorDeptOrg majordept = null;
		
		majordept = searchByMutiFeild(tablename, "majorIddeptOrgId", new Object[]{majorId, orgDepId});
		
		if(null == majordept){
			return Cause.getFailcode(MAJORIDWEONG, "majorId", "major id wrong");
		}
		
		return Cause.getSuccess(majordept.getId());
	}

}
