package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.college.account.bean.Major;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;


public class DaoMajorService extends  DaoService<Major>{

	private int MAJORIDNOTFIND = 5001;
	private int MAJORNAMENULL = 5002;
	private int MAJORNAMEEXIST = 5003;
	public static String tablename = "Major";

	
	public boolean selIsExist(Integer id){		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	private boolean selNameUniq(String name){
		
		return (null == searchByFeild(tablename, "name", name))?true:false;
	}
	
	public String save(String jsonString){

		Major major = (Major)Json2Obj.getObj(jsonString, Major.class);
		
		if(null == major.getName()){
			return Cause.getFailcode(MAJORNAMENULL, "name", "name can not null");
		}
		
		if(!selNameUniq(major.getName())){
			return Cause.getFailcode(MAJORNAMEEXIST, "name", "name is exist");
		}

		major.setCreateTime(new Date());
		
		Integer id = create(major);
		
	    return Cause.getSuccess(id);
	}

	public String sel(Integer id){
		
		Major major = searchByid(id, tablename);
		
		if(null == major){
			
			return Cause.getFailcode(MAJORIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(major, Major.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Major major = null;
		Major majorfind = null;
		
		major = (Major)Json2Obj.getObj(jsonString, Major.class);
		
		
		
		majorfind = searchByid(id, tablename);
		
		if(null == majorfind){
			return Cause.getFailcode(MAJORIDNOTFIND, "id", "org id not find");
		}
		/*
		if(null != major.getName() && !selNameUniq(major.getName())){
			
			return Cause.getFailcode(MAJORNAMEEXIST, "name", "name is exist");
		}
		*/
		Json2Obj.updateObject(major, majorfind);
		
		update(majorfind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Major major = null;
		
		major = searchByid(id, tablename);
		
		if(null == major){
			return Cause.getFailcode(MAJORIDNOTFIND, "id", "org id not find");
		}
		
		delete(Major.class, id);
		
		return Cause.getSuccess(id);
	}
	
	public String getAllObject(){
		
		List<Object> list = searchAll(tablename);
		
		return Cause.getData(list);
	}
	
	public String getObject(Integer id){
		
		List<Object> list = new ArrayList<Object>();
		
		Object obj = searchByid(id, tablename);
		list.add(obj);
		
		return Cause.getData(list);
	}
	
	public String getBydepId(Integer id){
		
		List<Object> list = new ArrayList<Object>();
		
		list = searchByFeildList(tablename, "deptId", id);
		
		return Cause.getData(list);
	}
	
	
}
