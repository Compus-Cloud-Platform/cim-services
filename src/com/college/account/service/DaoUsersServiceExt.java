package com.college.account.service;

import com.college.account.bean.UsersExt;
import com.college.util.Cause;
import com.college.util.Json2Obj;

public class DaoUsersServiceExt extends DaoService<UsersExt>{
	
	private int USEEXTIDNOTEXIST = 2001;
	
	private String tablename = "UsersExt";

	public String save(String jsonString){
		
		UsersExt usersext = null;
		UsersExt usersextind = null;
		Integer id = null;
		
		usersext=(UsersExt)Json2Obj.getObj(jsonString, UsersExt.class);
	    
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
		
		usersext=(UsersExt)Json2Obj.getObj(jsonString, UsersExt.class);
		
		usersextfind = searchByFeild(tablename, "loginId", loginId);
		
		if(null == usersextfind)
		{
	    	return Cause.getFailcode(USEEXTIDNOTEXIST, "Id", "id not find");
	    }
		
		Json2Obj.repalceDiffObjMem(usersext, usersextfind, UsersExt.class);
		
		update(usersextfind);
		
		return Cause.getSuccess(usersextfind.getId());
	}
}
