package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.college.account.bean.UserStudentMajor;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;

public class DaoUserStudentMajorService extends  DaoService<UserStudentMajor>{
	
	public static String tablename = "UserStudentMajor";
	public static int USERSTUDENTMAJORRELATIONEXIST = 14001;
	
	private UserStudentMajor getObj(Integer userId, Integer relationId){
		
		UserStudentMajor  userstudentmajor = searchByMutiFeild(tablename, "loginIdmajorDeptId", new Integer[]{userId, relationId});
		
		return userstudentmajor;
	}

	public String save(Integer userId, Integer relationId, Integer operId){
		
		if(null != getObj(userId, relationId)){
			
			return Cause.getFailcode(USERSTUDENTMAJORRELATIONEXIST, "id", "relation is exist");
		}
		
		UserStudentMajor userstudentmajor = new UserStudentMajor(userId, relationId, new Date(), operId);
		userstudentmajor.setId(Session.getOperId());
		
		Integer id = create(userstudentmajor);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer userId, Integer relationId, Integer operId){
		
		UserStudentMajor  userstudentmajor = getObj(userId, relationId);
		
		if(null == userstudentmajor){
			
			return Cause.getFailcode(USERSTUDENTMAJORRELATIONEXIST, "id", "relation is exist");
		}
		
		delete(UserStudentMajor.class, userstudentmajor.getId());
		
		return Cause.getSuccess(userstudentmajor.getId());
				
	}
	
	public List<Integer> sel(Integer relationId, Integer operId){
		
		UserStudentMajor  userstudentmajor = null;
		List<Object> list = searchByFeildList(tablename, "majorDeptId", relationId);
		List<Integer> useridlist = new ArrayList<Integer>();
		
		for(Object obj:list){
			userstudentmajor = (UserStudentMajor)obj;
			useridlist.add(userstudentmajor.getLoginId());
		}
		
		return useridlist;
				
	}
}
