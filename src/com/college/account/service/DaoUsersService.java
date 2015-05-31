package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.college.account.bean.Users;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Json2Obj;
import com.college.util.Md5Util;
import com.college.util.ServiceFactoryBean;


public class DaoUsersService extends DaoService<Users>{
	
	public static int USELOGINLOSTNAMEORID = 1001;
	public static int USELOGINIDNOTEXIST = 1002;
	public static int USEPWDWRONG = 1003;
	public static int USELOGINEXISTORWRONG = 1004;
	public static int USEORGIDWRONG = 1005;
	public static int USEPOSIDWRONG = 1006;
	public static int USEIDNOTEXIST = 1007;
	public static int USELOGIDNOTUPT = 1007;
	
	public static String tablename = "Users";
	
	public String login(String jsonString, HttpServletRequest request, HttpServletResponse servletResponse) throws Exception{
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
		
		if(null == map.get("userName") || null == map.get("password")){
			return Cause.getFailcode(USELOGINLOSTNAMEORID, "login", "can not null");
		}
		
		Users users = searchByFeild(tablename, "loginId", map.get("userName").toString());
		
		if(null == users){
			return Cause.getFailcode(USELOGINIDNOTEXIST, "name", "name not exist");
		}
		
		if(users.getLoginPassword().equals(Md5Util.md5calc(map.get("password").toString()))){
			List<Object> list = new ArrayList<Object>();
			list.add(users);
			
			/* 成功后设置session属性 */
			request.getSession().setAttribute(map.get("userName"), users.getId());
			
			servletResponse.addHeader("id", map.get("userName"));
			
			return Cause.getStringData(list, Users.class);
			
		}else{
			return Cause.getFailcode(USEPWDWRONG, "pwd", "pwd wrong");
		}
	}
	
	
	public String save(String jsonString){
		
		Users users = null;
		Users usersfind = null;
		Integer id = null;
		
	    users=(Users)Json2Obj.getObj(jsonString, Users.class);
	    
	    if(null == users.getLoginId()){
	    	return Cause.getFailcode(USELOGINLOSTNAMEORID, "login", "can not null");
	    }
	    
	    /* if org id exist must find it */
	    
	    if(null != users.getOrgId()){
	    	
	    	if(!ServiceFactoryBean.getOrganizationService().selIsExist(users.getOrgId())){
	    		return Cause.getFailcode(USEORGIDWRONG, "orgId", "orgId must exist in Organization table");
	    	}
	    }
	    
	    if(null != users.getPositionId()){
	    	if(!ServiceFactoryBean.getPositionService().selIsExist(users.getPositionId())){
	    		return Cause.getFailcode(USEPOSIDWRONG, "positionId", "positionId must exist in Position table");
	    	}
	    }
	    
	    usersfind = searchByFeild(tablename, "loginId", users.getLoginId());
	    
	    if(null != usersfind){
	    	return Cause.getFailcode(USELOGINEXISTORWRONG, "loginId", "exist or lost loginId filed");
	    }
	    
	    users.setLoginPassword(Md5Util.md5calc(users.getLoginPassword()));
	    
	    users.setCreateTime(new Date());
	    id = create(users);
	    
	    return Cause.getSuccess(id);
	}
	
	public String[] upd(Integer id, String jsonString){
		
		Users users = null;
		Users usersfind = null;
		
		users=(Users)Json2Obj.getObj(jsonString, Users.class);
		
		usersfind = searchByid(id, tablename);
		
		if(null == usersfind)
		{
	    	return new String[]{Cause.getFailcode(USEIDNOTEXIST, "Id", "id not find"),null};
	    }
		
		
		if(users.getLoginId() != null){
			if(!users.getLoginId().equals(usersfind.getLoginId())){
				return new String[]{Cause.getFailcode(USELOGIDNOTUPT, "loginId", "loginId can not update"),null};
			}
		}
		

		if(null != users.getOrgId()){

	    	if(!ServiceFactoryBean.getOrganizationService().selIsExist(users.getOrgId())){
	    		return new String[]{Cause.getFailcode(USEORGIDWRONG, "orgId", "orgId must exist in Organization table"),null};
	    	}
	    }
	    
	    if(null != users.getPositionId()){
	    	if(!ServiceFactoryBean.getPositionService().selIsExist(users.getPositionId())){
	    		return new String[]{Cause.getFailcode(USEPOSIDWRONG, "PositionId", "PositionId must exist in Position table"),null};
	    	}
	    }
	    
	    if(null != users.getLoginPassword()){
	    	users.setLoginPassword(Md5Util.md5calc(users.getLoginPassword()));
	    }
	    
		Json2Obj.updateObject(users, usersfind);
		
		update(usersfind);
		
		return new String[]{Cause.getSuccess(id), usersfind.getId().toString()};
	}
	
	public String[] del(Integer id){
		
		/* search */
		Users usersfind = null;
		
		usersfind = searchByid(id, tablename);
		
		if(null == usersfind){
			return new String[]{Cause.getFailcode(USEIDNOTEXIST, "Id", "id not find"),null};
		}
		
		delete(Users.class, usersfind.getId());
		
		return new String[]{Cause.getSuccess(id), usersfind.getId().toString()};
	}
	
	public String sel(Integer id){
		
		Users usersfind = null;
		
		usersfind = searchByid(id, tablename);
		
		if(null == usersfind){
			
			return Cause.getFailcode(USEIDNOTEXIST, "Id", "id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
        
        list.add(usersfind);
        
        return Cause.getStringData(list, Users.class);
		
	}
	
	public boolean isExist(Object id){
		
		Users usersfind = null;
		
		if(null == id)return false;
		
		usersfind = searchByid(Integer.parseInt(id.toString()), tablename);
		
		return (null == usersfind)?false:true;
	}
	
	public String getAllObject(){
		
		List<Object> list = searchAll(tablename);
		
        return Cause.getStringData(list, Users.class);
	}
	
	public String getUserByListid(List<Integer> listid){
		List<Object> list = new ArrayList<Object>();
		Users usersfind = null;
		
		for(Integer loginid:listid){
			usersfind = searchByid(loginid, tablename);
	        list.add(usersfind);
		}
		return Cause.getStringData(list, Users.class);
	}
}
