package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Permission;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoPermissionService extends  DaoService<Permission>{

	private int PERMISSIONIDNOTFIND = 9001;
	private int PERMISSIONNAMENULL = 9002;
	private int PERMISSIONNAMEEXIST = 9003;
	
	private String tablename = "Permission";
	
	public boolean selIsExist(Integer id){		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	private boolean selNameUniq(String name){
		
		return (null == searchByFeild(tablename, "name", name))?true:false;
	}
	
	public String save(String jsonString){

		Permission permission = (Permission)Json2Obj.getObj(jsonString, Permission.class);
		
		if(null == permission.getName()){
			return Cause.getFailcode(PERMISSIONNAMENULL, "name", "name can not null");
		}
		
		if(!selNameUniq(permission.getName())){
			return Cause.getFailcode(PERMISSIONNAMEEXIST, "name", "name is exist");
		}

		permission.setCreateTime(new Date());
		
		Integer id = create(permission);
		
	    return Cause.getSuccess(id);
	}
	
	
	public String sel(Integer id){
		
		Permission permission = searchByid(id, tablename);
		
		if(null == permission){
			
			return Cause.getFailcode(PERMISSIONIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(permission, Permission.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Permission permission = null;
		Permission permissionfind = null;
		
		permission = (Permission)Json2Obj.getObj(jsonString, Permission.class);
		
		
		
		permissionfind = searchByid(id, tablename);
		
		if(null == permissionfind){
			return Cause.getFailcode(PERMISSIONIDNOTFIND, "id", "org id not find");
		}
		
		if(null != permission.getName() && !selNameUniq(permission.getName())){
			
			return Cause.getFailcode(PERMISSIONNAMEEXIST, "name", "name is exist");
		}
		
		Json2Obj.updateObject(permission, permissionfind);
		
		update(permissionfind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Permission permission = null;
		
		permission = searchByid(id, tablename);
		
		if(null == permission){
			return Cause.getFailcode(PERMISSIONIDNOTFIND, "id", "org id not find");
		}
		
		delete(Permission.class, id);
		
		return Cause.getSuccess(id);
	}
}
