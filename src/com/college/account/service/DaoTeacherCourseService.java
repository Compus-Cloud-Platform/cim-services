package com.college.account.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Course;
import com.college.account.bean.TeacherCourse;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;


public class DaoTeacherCourseService extends  DaoService<TeacherCourse>{

	public static String tablename = "TeacherCourse";
	public static int TEACHERCOURSEIDNOTFIND = 15001;
	
	private DaoUsersService usersService;
	private DaoCourseService courseService;
	
	
	public void setUsersService(DaoUsersService usersService) {
		this.usersService = usersService;
	}

	public void setCourseService(DaoCourseService courseService) {
		this.courseService = courseService;
	}
	
	public boolean isExist(Integer id){
		
		if(null == id){ return false;}
		
		TeacherCourse teachercourseFind = null;
		teachercourseFind = searchByid(id, tablename);
		return (null == teachercourseFind)?false:true;
	}
	
	public String save(String jsonString, Integer operId){
		
		TeacherCourse teachercourse = null;
		teachercourse = (TeacherCourse)Json2Obj.getObj(jsonString, TeacherCourse.class);
		
		Integer userId = teachercourse.getLoginId();
		Integer courseId = teachercourse.getCourseId();
		
		if(null == userId || !usersService.isExist(userId)){
			return Cause.getFailcode(DaoUsersService.USELOGINIDNOTEXIST, "id", "user id not find");
		}
		
		if(null == courseId || !courseService.selIsExist(courseId)){
			return Cause.getFailcode(DaoCourseService.COURSEIDNOTFIND, "id", "teacher id not find");
		}

		teachercourse.setCreateTime(new Date());
		teachercourse.setId(Session.getOperId());
		
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
	
	public String selTeacherAllCourse(Integer id){
		
		TeacherCourse teachercourse = null;
		
		List<Object> list = searchByFeildList(tablename, "loginId", id);
		
		List<Object> teachercourseidlist = new ArrayList<Object>();
		
		for(Object obj:list){
			
			teachercourse = (TeacherCourse)obj;

			Map<String, Object> map = Obj2Map.toMap(teachercourse, TeacherCourse.class);
			
			map.put("course", Obj2Map.toMap(courseService.searchByid(teachercourse.getCourseId(), DaoCourseService.tablename), Course.class));
			
			teachercourseidlist.add(map);
		}
		
		return Cause.getData(teachercourseidlist);
	}
	
	public String selone(Integer id){
		
		TeacherCourse teachercourse = null;
		teachercourse = searchByid(id, tablename);
		
		if(null == teachercourse){
			return Cause.getFailcode(TEACHERCOURSEIDNOTFIND, "id", "TeacherCourse id not find");
		}
		
		List<Object> list = new ArrayList<Object>();
		
		Map<String, Object> map = Obj2Map.toMap(teachercourse, TeacherCourse.class);
		
		map.put("course", Obj2Map.toMap(courseService.searchByid(teachercourse.getCourseId(), DaoCourseService.tablename), Course.class));
		
		list.add(map);
		
		
		return Cause.getData(list);
	}
	
	public String getAllObject(){
		
		List<Object> list = searchAll(tablename);

		return Cause.getData(list);
	}
	
}
