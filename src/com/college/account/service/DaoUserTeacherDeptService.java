package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		
		UserTeacherDept  userteacherdept = getObj(userId, relationId);
		
		if(null == userteacherdept){
			
			return Cause.getFailcode(USERTEACHERDEPTRELATIONEXIST, "id", "relation is exist");
		}
		
		delete(UserTeacherDept.class, userteacherdept.getId());
		
		return Cause.getSuccess(userteacherdept.getId());
	}
	
	public List<Integer> sel(Integer relationId, Integer operId){
		
		UserTeacherDept  userteacherdept = null;
		List<Object> list = searchByFeildList(tablename, "deptOrgId", relationId);
		List<Integer> useridlist = new ArrayList<Integer>();
		
		for(Object obj:list){
			userteacherdept = (UserTeacherDept)obj;
			useridlist.add(userteacherdept.getLoginId());
		}
		
		return useridlist;
	}
}
