package com.college.account.service;


import java.util.Date;

import com.college.account.bean.TeacherCourse;
import com.college.util.Cause;
import com.college.util.Json2Obj;


public class DaoTeacherCourseService extends  DaoService<TeacherCourse>{

	public static String tablename = "TeacherCourse";
	public int TeacherCourseUSERNOTFIND = 15001;

	
	public String save(Integer id, String jsonString, Integer operId){
		
		TeacherCourse teachercourse = null;
		teachercourse = (TeacherCourse)Json2Obj.getObj(jsonString, TeacherCourse.class);
		
		teachercourse.setLoginId(id);
		teachercourse.setCreateTime(new Date());
		teachercourse.setOperId(operId);
		
		Integer idTemp = create(teachercourse);
		
		return Cause.getSuccess(idTemp);
	}
	
}
