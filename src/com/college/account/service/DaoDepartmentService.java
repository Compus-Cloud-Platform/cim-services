package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Department;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoDepartmentService extends  DaoService<Department>{

	public static int DEPARTMENTIDNOTFIND = 10001;
	public static int DEPARTMENTNAMENULL = 10002;
	public static int DEPARTMENTNAMEEXIST = 10003;
	public static int DEPARTMENTLOSTNAME = 10004;
	
	public static String tablename = "Department";
	
	public boolean selIsExist(Integer id){		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	public boolean selNameUniq(String name){
		
		return (null == searchByFeild(tablename, "name", name))?true:false;
	}
	
	public String save(String jsonString){

		Department department = (Department)Json2Obj.getObj(jsonString, Department.class);
		
		if(null == department.getName()){
			return Cause.getFailcode(DEPARTMENTNAMENULL, "name", "name can not null");
		}
		
		if(!selNameUniq(department.getName())){
			return Cause.getFailcode(DEPARTMENTNAMEEXIST, "name", "name is exist");
		}

		department.setCreateTime(new Date());
		
		Integer id = create(department);
		
	    return Cause.getSuccess(id);
	}
	
	
	public String sel(Integer id){
		
		Department department = searchByid(id, tablename);
		
		if(null == department){
			
			return Cause.getFailcode(DEPARTMENTIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(department, Department.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Department department = null;
		Department departmentfind = null;
		
		department = (Department)Json2Obj.getObj(jsonString, Department.class);
		
		
		
		departmentfind = searchByid(id, tablename);
		
		if(null == departmentfind){
			return Cause.getFailcode(DEPARTMENTIDNOTFIND, "id", "org id not find");
		}
		
		if(null != department.getName() && !selNameUniq(department.getName())){
			
			return Cause.getFailcode(DEPARTMENTNAMEEXIST, "name", "name is exist");
		}
		
		Json2Obj.repalceDiffObjMem(department, departmentfind, Department.class);
		
		update(departmentfind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Department department = null;
		
		department = searchByid(id, tablename);
		
		if(null == department){
			return Cause.getFailcode(DEPARTMENTIDNOTFIND, "id", "org id not find");
		}
		
		delete(Department.class, id);
		
		return Cause.getSuccess(id);
	}
}
