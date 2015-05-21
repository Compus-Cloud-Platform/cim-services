package com.college.account.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Major;
import com.college.account.bean.MajorDeptOrg;
import com.college.util.Cause;
import com.college.util.Obj2Map;

public class DaoMajorDeptOrgService extends  DaoService<MajorDeptOrg>{
	
	public static String tablename = "MajorDeptOrg";
	
	private int MAJORDEPTNOTFIND = 12001;
	private int MAJORIDWEONG = 12002;
	private int MAJORIDRELATIONEXIST= 12003;
	
	private DaoMajorService majorService;
	

	public void setMajorService(DaoMajorService majorService) {
		this.majorService = majorService;
	}


	public String save(Integer majorId, Integer deptOrgId, Integer operId){
		
		MajorDeptOrg majordeptfind = searchByMutiFeild(tablename, "majorIddeptOrgId", new Integer[]{majorId,deptOrgId});
		
		if(null != majordeptfind){
			return Cause.getFailcode(MAJORIDRELATIONEXIST, "id", "this is exist");
		}
		
		if(null == majorService.searchByid(majorId, DaoMajorService.tablename)){
			return Cause.getFailcode(MAJORDEPTNOTFIND, "id", "major id not exist");
		}
		
		MajorDeptOrg majordept = new MajorDeptOrg(majorId, deptOrgId, new Date(), operId);
		
		Integer id = create(majordept);
		
		return Cause.getSuccess(id);
	}
	
	
	public String del(String id){
		
		MajorDeptOrg majordept = null;
		
		majordept = searchByid(Integer.parseInt(id), tablename);
		
		if(null == majordept){
			
			return Cause.getFailcode(MAJORDEPTNOTFIND, "id", "not find");
		}
		
		delete(MajorDeptOrg.class, majordept.getId());
		
		return Cause.getSuccess(majordept.getId());
	}

	public String getDepOneMajor(Integer orgDepId, Integer majorId){
		
		MajorDeptOrg majordept = null;
		
		majordept = searchByMutiFeild(tablename, "majorIddeptOrgId", new Object[]{majorId, orgDepId});
		
		if(null == majordept){
			return Cause.getFailcode(MAJORIDWEONG, "majorId", "major id wrong");
		}
		
		return Cause.getSuccess(majordept.getId());
	}
	
	public String getMajorBylistId(Integer id){
		
		List<Object> list = searchByFeildList(tablename, "deptOrgId", id);
		
		List<Object> relist = new ArrayList<Object>();
		
		for(Object temp :list){
			Map<String, Object> map = Obj2Map.toMap(temp, MajorDeptOrg.class);
			
			Major major = majorService.searchByid(((MajorDeptOrg)temp).getMajorId(), DaoMajorService.tablename);
			
			map.put("major", Obj2Map.toMap(major, Major.class));
			
			relist.add(map);
		}

		return Cause.getData(relist);
	}
	
	
	public String getMajorByRelationID(Integer id){
		MajorDeptOrg majordept = null;
		
		majordept = searchByid(id, tablename);
		
		List<Object> majorList = new ArrayList<Object>();
		
		Object obj = majorService.searchByid(majordept.getMajorId(), DaoMajorService.tablename);
		
		majorList.add(obj);
		
		return Cause.getData(majorList);
	}
	

}
