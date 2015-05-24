package com.college.account.service;

import com.college.account.bean.TeacherCourseGroup;

public class DaoTeacherCourseGroupService extends  DaoService<TeacherCourseGroup>{
	
	public static String tablename = "TeacherCourseGroup";
	
	public static int COURSEGROUPIDNOTFIND = 18001;

	public boolean isExist(Integer id){
		
		TeacherCourseGroup teachercoursegroup = null;
		
		if(null == id)return false;
		
		teachercoursegroup = searchByid(id, tablename);
		
		return (null == teachercoursegroup)?false:true;
	}
}
