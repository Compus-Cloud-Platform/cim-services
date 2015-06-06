package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.college.account.bean.UserAdminOrg;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;

public class DaoUserAdminOrgService extends  DaoService<UserAdminOrg>{
	
	public static String tablename = "UserAdminOrg";
	
	public static int USERADMINORGRELATIONEXIST = 14001;
	
	private UserAdminOrg getObj(Integer userId, Integer relationId){
		
		UserAdminOrg  useradminorg = searchByMutiFeild(tablename, "loginIdorgId", new Integer[]{userId, relationId});
		
		return useradminorg;
	}

	public String save(Integer userId, Integer relationId, Integer operId){
		
		if(null != getObj(userId, relationId)){
			
			return Cause.getFailcode(USERADMINORGRELATIONEXIST, "id", "relation is exist");
		}
		
		UserAdminOrg useradminorg = new UserAdminOrg(userId, relationId, new Date(), operId);
		useradminorg.setId(Session.getOperId());
		
		Integer id = create(useradminorg);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer userId, Integer relationId, Integer operId){
		
		UserAdminOrg  useradminorg = getObj(userId, relationId);
		
		if(null == useradminorg){
			
			return Cause.getFailcode(USERADMINORGRELATIONEXIST, "id", "relation is exist");
		}
		
		delete(UserAdminOrg.class, useradminorg.getId());
		
		return  Cause.getSuccess(useradminorg.getId());
	}
	
	public List<Integer> sel(Integer relationId, Integer operId){
		
		UserAdminOrg  useradminorg = null;
		
		List<Object> list = searchByFeildList(tablename, "orgId", relationId);
		List<Integer> useridlist = new ArrayList<Integer>();
		
		for(Object obj:list){
			useradminorg = (UserAdminOrg)obj;
			useridlist.add(useradminorg.getLoginId());
		}
		
		return useridlist;
	}
}
