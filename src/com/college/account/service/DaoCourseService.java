package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Course;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoCourseService extends  DaoService<Course>{

	public static int COURSEIDNOTFIND = 6001;
	public static int COURSENAMENULL = 6002;
	public static int COURSENAMEEXIST = 6003;
	
	private String tablename = "Course";
	
	public boolean selIsExist(Integer id){		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	private boolean selNameUniq(String name){
		
		return (null == searchByFeild(tablename, "name", name))?true:false;
	}
	
	public String save(String jsonString){

		Course course = (Course)Json2Obj.getObj(jsonString, Course.class);
		
		if(null == course.getName()){
			return Cause.getFailcode(COURSENAMENULL, "name", "name can not null");
		}
		
		if(!selNameUniq(course.getName())){
			return Cause.getFailcode(COURSENAMEEXIST, "name", "name is exist");
		}

		course.setCreateTime(new Date());
		
		Integer id = create(course);
		
	    return Cause.getSuccess(id);
	}
	
	
	public String sel(Integer id){
		
		Course course = searchByid(id, tablename);
		
		if(null == course){
			
			return Cause.getFailcode(COURSEIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(course, Course.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String getAllObject(){
		
		List<Object> list = searchAll(tablename);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Course course = null;
		Course coursefind = null;
		
		course = (Course)Json2Obj.getObj(jsonString, Course.class);
		
		
		
		coursefind = searchByid(id, tablename);
		
		if(null == coursefind){
			return Cause.getFailcode(COURSEIDNOTFIND, "id", "org id not find");
		}
		
		if(null != course.getName() && !selNameUniq(course.getName())){
			
			return Cause.getFailcode(COURSENAMEEXIST, "name", "name is exist");
		}
		
		Json2Obj.updateObject(course, coursefind);
		
		update(coursefind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Course course = null;
		
		course = searchByid(id, tablename);
		
		if(null == course){
			return Cause.getFailcode(COURSEIDNOTFIND, "id", "org id not find");
		}
		
		delete(Course.class, id);
		
		return Cause.getSuccess(id);
	}
	
	public String getList(List<Integer> list){
		
		List<Object> courseList = new ArrayList<Object>();
		
		for(Integer id:list){
			courseList.add(searchByid(id, tablename));
		}
		
		return Cause.getData(courseList);
		
	}
}
