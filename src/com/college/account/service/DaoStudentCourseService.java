package com.college.account.service;

import java.util.Date;

import com.college.account.bean.StudentCourse;
import com.college.util.Cause;
import com.college.util.Json2Obj;

public class DaoStudentCourseService extends  DaoService<StudentCourse>{

	public String save(Integer id, String jsonString, Integer operId){
		
		StudentCourse studentcourse = null;
		studentcourse = (StudentCourse)Json2Obj.getObj(jsonString, StudentCourse.class);
		
		studentcourse.setLoginId(id);
		studentcourse.setCreateTime(new Date());
		studentcourse.setOperId(operId);
		
		Integer idTemp = create(studentcourse);
		
		return Cause.getSuccess(idTemp);
	}
}
