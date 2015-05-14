package com.college.account.service;

import java.util.Date;

import com.college.account.bean.UserStudentMajor;
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
		
		Integer id = create(userstudentmajor);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer userId, Integer relationId, Integer operId){
		return null;
	}
}
