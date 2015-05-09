package com.college.account.jersey;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.college.account.bean.Users;
import com.college.account.bean.UsersExt;
import com.college.util.JacksonUtils;
import com.college.util.Json2Obj;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/v1")
public class UserLoginRestService {

	private static final Logger log = Logger4j.getLogger(UserLoginRestService.class);
	
	@GET
    @Path("/users")
    @Consumes({MediaType.APPLICATION_JSON})
	public String loginNamePwdGet(@QueryParam("userName") String userName,
			                   @QueryParam("password") String password) {
	    
	    Map resultMap = new HashMap();
	    Map dataMap = new HashMap();
		Users userObject = ServiceFactoryBean.getUserService().search(userName);
		if(null != userObject){
		    if(password.equals(userObject.getLoginPassword())){
		        resultMap.put("ack", "success");
	            dataMap.put("id", userObject.getId());
	            dataMap.put("loginId", userObject.getLoginId());
	            dataMap.put("token", null);
	            resultMap.put("data", dataMap);
		    }else{
		        resultMap.put("ack", "failure");
		        dataMap.put("code",1001);
	            dataMap.put("field","password");
	            dataMap.put("description","Password is incorrect");
	            resultMap.put("errors", dataMap);
		    }
		}else{
		    resultMap.put("ack", "failure");
		    dataMap.put("code",1002);
		    dataMap.put("field","loginId");
		    dataMap.put("description","loginId is not exist");
		    resultMap.put("errors", dataMap);
		}
		
		return JacksonUtils.getJsonString(resultMap);
	}
	
	@POST
    @Path("/users")
    @Consumes({MediaType.APPLICATION_JSON})
	public String loginNamePwdPOST(@QueryParam("userName") String userName,
            @QueryParam("password") String password) {
	    return loginNamePwdGet(userName, password);
	}
	
	@GET
    @Path("/save")
    @Consumes({MediaType.APPLICATION_JSON})
	public String saveUserGet(@QueryParam("jsonString") String jsonString){
	    Users users = null;
	    UsersExt usersext = null;
	    Users userObject = null;
	    Integer id = null;
	    
	    Map resultMap = new HashMap();
        Map dataMap = new HashMap();
	    
	    users=(Users)Json2Obj.getObj(jsonString, Users.class);
	    usersext=(UsersExt)Json2Obj.getObj(jsonString, UsersExt.class);
	    
	    userObject = ServiceFactoryBean.getUserService().search(users.getLoginId());
	    if(null != userObject){
	        
	        dataMap.put("code", 1003);
	        dataMap.put("field","loginId");
	        dataMap.put("description","Account ID already exist");
	        
	        resultMap.put("errors", dataMap);
	        resultMap.put("ack", "failure");
	        
	    }else{
	        
	        id = ServiceFactoryBean.getUserService().createUsers(users);
	        ServiceFactoryBean.getUserServiceExt().createUsersServiceExt(usersext);
	        
	        dataMap.put("id", id);
	        resultMap.put("ack", "success");
	        resultMap.put("data", dataMap); 
	    }
	    
	    return JacksonUtils.getJsonString(resultMap);
	}
	
	@POST
    @Path("/save")
    @Consumes({MediaType.APPLICATION_JSON})
    public String saveUserPost(@QueryParam("jsonString") String jsonString){
        return saveUserGet(jsonString);
    }
	
	@GET
    @Path("/update")
    @Consumes({MediaType.APPLICATION_JSON})
	public String updUserGet(@QueryParam("jsonString") String jsonString){
		
		Users users = null;
		UsersExt usersext = null;
		Users usersSearch = null;
		UsersExt usersextSearch = null;
		String loginId = null;
		Integer id = null;
		Map resultMap = new HashMap();
        Map dataMap = new HashMap();
		
		/* 必须携带 loginId字段 否则无法修改 */
		/* 根据 loginID获取 id */
		users=(Users)Json2Obj.getObj(jsonString, Users.class);
		usersext=(UsersExt)Json2Obj.getObj(jsonString, UsersExt.class);
		id = users.getId();
		
		if(users.getLoginId() == null){
			
			resultMap.put("ack", "failure");
			
			dataMap.put("code", 1004);
			dataMap.put("field", "loginId");
			dataMap.put("description", "must have loginId");
			resultMap.put("errors", dataMap);
			
			
		}else{
			
			loginId = users.getLoginId();
			
			usersSearch = ServiceFactoryBean.getUserService().search(loginId);
			usersextSearch = ServiceFactoryBean.getUserServiceExt().search(loginId);
			
			if(null == usersSearch){
				/* logindid 不存在*/
				resultMap.put("ack", "failure");
				
				dataMap.put("code", 1005);
				dataMap.put("field", "loginId");
				dataMap.put("description", "loginId not exist");
				resultMap.put("errors", dataMap);

			}else{
				/* 重组user userext */
				
				Json2Obj.repalceDiffObjMem(users, usersSearch, Users.class);
				Json2Obj.repalceDiffObjMem(usersext, usersextSearch, UsersExt.class);
				
				ServiceFactoryBean.getUserService().updateUsers(usersSearch);
		        ServiceFactoryBean.getUserServiceExt().updateUsersServiceExt(usersextSearch);
		        
		        resultMap.put("ack", "success");
			}
			
		}

		return JacksonUtils.getJsonString(resultMap);
	}
	
	@POST
    @Path("/update")
    @Consumes({MediaType.APPLICATION_JSON})
    public String updUserPost(@QueryParam("jsonString") String jsonString){
        return updUserGet(jsonString);
    }
	
	@GET
    @Path("/delete")
    @Consumes({MediaType.APPLICATION_JSON})
	public String delUserGet(@QueryParam("loginId") String loginId){
		return "cc";
	}
	
	@POST
    @Path("/delete")
    @Consumes({MediaType.APPLICATION_JSON})
	public String delUserPost(@QueryParam("loginId") String loginId){
		return delUserGet(loginId);
	}
}
