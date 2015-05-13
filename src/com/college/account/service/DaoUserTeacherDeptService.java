package com.college.account.service;

import java.util.Date;

import com.college.account.bean.UserTeacherDept;
import com.college.util.Cause;

public class DaoUserTeacherDeptService extends  DaoService<UserTeacherDept>{
	
	public static String tablename = "UserTeacherDept";
	public static int USERTEACHERDEPTRELATIONEXIST = 13001;
	
	private UserTeacherDept getObj(Integer userId, Integer relationId){
		
		UserTeacherDept  userteacherdept = searchByMutiFeild(tablename, "loginIddeptOrgId", new Integer[]{userId, relationId});
		
		return userteacherdept;
	}

	public String save(Integer userId, Integer relationId, Integer operId){
		
		if(null != getObj(userId, relationId)){
			
			return Cause.getFailcode(USERTEACHERDEPTRELATIONEXIST, "id", "relation is exist");
		}
		
		UserTeacherDept userteacherdept = new UserTeacherDept(userId, relationId, new Date(), operId);
		
		Integer id = create(userteacherdept);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer userId, Integer relationId, Integer operId){
		
		UserTeacherDept userteacherdept = new UserTeacherDept(userId, relationId, new Date(), operId);
		
		Integer id = create(userteacherdept);
		
		return Cause.getSuccess(id);
	}
}
