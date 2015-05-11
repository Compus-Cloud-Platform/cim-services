package com.college.account.service;

import com.college.account.bean.UsersExt;
import com.college.util.Cause;
import com.college.util.Json2Obj;

public class DaoUsersServiceExt extends DaoService<UsersExt>{
	
	private int USEEXTIDNOTEXIST = 2001;
	
	private String tablename = "UsersExt";

	public String save(Integer id, String jsonString){
		
		UsersExt usersext = null;
		UsersExt usersextind = null;

		jsonString = jsonString.replace("loginId", "LOGINID");
		
		usersext=(UsersExt)Json2Obj.getObj(jsonString, UsersExt.class);
		
		usersext.setLoginId(id);
	    
		usersextind = searchByFeild(tablename, "loginId", usersext.getLoginId());
	    
	    if(null != usersextind){
	    	/* 删除原先的 再次插入 */
	    	delete(UsersExt.class, usersextind.getId());
	    }

	    id = create(usersext);
	    
	    return Cause.getSuccess(id);
	}
	
	public String upd(String loginId, String jsonString){
		
		UsersExt usersext = null;
		UsersExt usersextfind = null;
		
		jsonString = jsonString.replace("loginId", "LOGINID");
		usersext=(UsersExt)Json2Obj.getObj(jsonString, UsersExt.class);
		
		usersextfind = searchByFeild(tablename, "loginId", Integer.parseInt(loginId));
		
		if(null == usersextfind)
		{
	    	return Cause.getFailcode(USEEXTIDNOTEXIST, "Id", "id not find");
	    }
		
		Json2Obj.updateObject(usersext, usersextfind);
		
		update(usersextfind);
		
		return Cause.getSuccess(usersextfind.getId());
	}
	
	public String del(String loginId){
		
		/* search */
		UsersExt usersextfind = null;
		
		usersextfind = searchByFeild(tablename, "loginId", Integer.parseInt(loginId));
		
		if(null == usersextfind){
			return Cause.getFailcode(USEEXTIDNOTEXIST, "Id", "id not find");
		}
		
		delete(UsersExt.class, usersextfind.getId());
		
		return Cause.getSuccess(usersextfind.getId());
	}
}
