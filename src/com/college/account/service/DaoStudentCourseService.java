package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.college.account.bean.StudentCourse;
import com.college.util.Cause;
import com.college.util.Json2Obj;

public class DaoStudentCourseService extends  DaoService<StudentCourse>{
	
	public static String tablename = "StudentCourse";
	public static int STUDENTCOURSEIDNOTFIND = 16001;
	public static int STUDENTCOURSECANOTUPDATE = 16001;

	public String save(Integer id, String jsonString, Integer operId){
		
		StudentCourse studentcourse = null;
		studentcourse = (StudentCourse)Json2Obj.getObj(jsonString, StudentCourse.class);
		
		studentcourse.setLoginId(id);
		studentcourse.setCreateTime(new Date());
		studentcourse.setOperId(operId);
		
		Integer idTemp = create(studentcourse);
		
		return Cause.getSuccess(idTemp);
	}
	
	public String upd(Integer id, String jsonString, Integer operId){
		
		StudentCourse studentcoursefind = null;
		StudentCourse studentcourse = searchByid(id, tablename);
		if(null == studentcourse){
			
			return Cause.getFailcode(STUDENTCOURSEIDNOTFIND, "Id", "id not find");
		}
		
		studentcoursefind = (StudentCourse)Json2Obj.getObj(jsonString, StudentCourse.class);
		
		if(null!= studentcoursefind.getLoginId() || null != studentcoursefind.getTeacherCourseId()){
			return Cause.getFailcode(STUDENTCOURSECANOTUPDATE, "id", "user id course id cannot update");
		}
		
		Json2Obj.updateObject(studentcourse, studentcoursefind);
		
		update(studentcoursefind);
		
		return Cause.getSuccess(studentcoursefind.getId());
		
	}
	
	public String del(Integer id, Integer operId){
		
		StudentCourse studentcourse = searchByid(id, tablename);
		
		if(null == studentcourse){
			
			return Cause.getFailcode(STUDENTCOURSEIDNOTFIND, "Id", "id not find");
		}
		
		delete(StudentCourse.class, studentcourse.getId());
		
		return  Cause.getSuccess(studentcourse.getId());
	}
	
	public Integer getOneObject(Integer id, Integer idTC){
		
		StudentCourse studentcourse = searchByid(idTC, tablename);
		return studentcourse.getTeacherCourseId();
	}
	
	public List<Integer>  getAllObject(Integer id){
		
		StudentCourse studentcourse = null;
		
		List<Object> list = searchByFeildList(tablename, "loginId", id);
		
		List<Integer> relist = new ArrayList<Integer>();
		
		for(Object tempObj:list){
			studentcourse = (StudentCourse)tempObj;
			relist.add(studentcourse.getTeacherCourseId());
		}
		
		return null;
	}
}
