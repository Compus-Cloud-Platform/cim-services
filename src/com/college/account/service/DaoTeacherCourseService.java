package com.college.account.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.college.account.bean.TeacherCourse;
import com.college.util.Cause;
import com.college.util.Json2Obj;


public class DaoTeacherCourseService extends  DaoService<TeacherCourse>{

	public static String tablename = "TeacherCourse";
	public static int TEACHERCOURSEIDNOTFIND = 15001;

	public boolean isExist(Integer id){
		
		if(null == id){ return false;}
		
		TeacherCourse teachercourseFind = null;
		teachercourseFind = searchByid(id, tablename);
		return (null == teachercourseFind)?false:true;
	}
	
	public String save(Integer id, String jsonString, Integer operId){
		
		TeacherCourse teachercourse = null;
		teachercourse = (TeacherCourse)Json2Obj.getObj(jsonString, TeacherCourse.class);
		
		teachercourse.setLoginId(id);
		teachercourse.setCreateTime(new Date());
		teachercourse.setOperId(operId);
		
		Integer idTemp = create(teachercourse);
		
		return Cause.getSuccess(idTemp);
	}
	
	public String upd(Integer id, String jsonString){
		
		TeacherCourse teachercourse = null;
		TeacherCourse teachercourseFind = null;
		teachercourse = (TeacherCourse)Json2Obj.getObj(jsonString, TeacherCourse.class);
		
		teachercourseFind = searchByid(id, tablename);
		
		if(null == teachercourseFind){
			
			return Cause.getFailcode(TEACHERCOURSEIDNOTFIND, "id", "TeacherCourse id not find");
		}
		
		Json2Obj.updateObject(teachercourse, teachercourseFind);
		
		update(teachercourseFind);
		
		return Cause.getSuccess(teachercourseFind.getId());
	}
	
	public String del(Integer id){
		
		TeacherCourse teachercourseFind = null;
		
		teachercourseFind = searchByid(id, tablename);
		
		if(null == teachercourseFind){
			
			return Cause.getFailcode(TEACHERCOURSEIDNOTFIND, "id", "TeacherCourse id not find");
		}
		
		delete(TeacherCourse.class, teachercourseFind.getId());
		
		return Cause.getSuccess(teachercourseFind.getId());
	}
	
	public List<Integer> sel(Integer id){
		
		TeacherCourse teachercourse = null;
		
		List<Object> list = searchByFeildList(tablename, "loginId", id);
		
		List<Integer> courseidlist = new ArrayList<Integer>();
		
		for(Object obj:list){
			
			teachercourse = (TeacherCourse)obj;
			courseidlist.add(teachercourse.getCourseId());
		}
		
		return courseidlist;
	}
	
}
