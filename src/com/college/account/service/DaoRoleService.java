package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.college.account.bean.Role;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoRoleService extends  DaoService<Role>{

	private int ROLEIDNOTFIND = 8001;
	private int ROLENAMENULL = 8002;
	private int ROLENAMEEXIST = 8003;
	
	private String tablename = "Role";
	
	public boolean selIsExist(Integer id){		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	private boolean selNameUniq(String name){
		
		return (null == searchByFeild(tablename, "name", name))?true:false;
	}
	
	public String save(String jsonString){

		Role role = (Role)Json2Obj.getObj(jsonString, Role.class);
		
		if(null == role.getName()){
			return Cause.getFailcode(ROLENAMENULL, "name", "name can not null");
		}
		
		if(!selNameUniq(role.getName())){
			return Cause.getFailcode(ROLENAMEEXIST, "name", "name is exist");
		}

		role.setCreateTime(new Date());
		
		Integer id = create(role);
		
	    return Cause.getSuccess(id);
	}
	
	
	public String sel(Integer id){
		
		Role role = searchByid(id, tablename);
		
		if(null == role){
			
			return Cause.getFailcode(ROLEIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(role, Role.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String getAllObject(){
		
		List<Object> list = searchAll(tablename);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Role role = null;
		Role rolefind = null;
		
		role = (Role)Json2Obj.getObj(jsonString, Role.class);
		
		
		
		rolefind = searchByid(id, tablename);
		
		if(null == rolefind){
			return Cause.getFailcode(ROLEIDNOTFIND, "id", "org id not find");
		}
		/*
		if(null != role.getName() && !selNameUniq(role.getName())){
			
			return Cause.getFailcode(ROLENAMEEXIST, "name", "name is exist");
		}
		*/
		Json2Obj.updateObject(role, rolefind);
		
		update(rolefind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Role role = null;
		
		role = searchByid(id, tablename);
		
		if(null == role){
			return Cause.getFailcode(ROLEIDNOTFIND, "id", "org id not find");
		}
		
		delete(Role.class, id);
		
		return Cause.getSuccess(id);
	}
}
