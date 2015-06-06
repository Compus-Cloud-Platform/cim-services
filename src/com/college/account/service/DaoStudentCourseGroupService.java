package com.college.account.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.college.account.bean.StudentCourseGroup;
import com.college.account.bean.TeacherCourseGroup;
import com.college.account.bean.Users;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Obj2Map;

public class DaoStudentCourseGroupService extends  DaoService<StudentCourseGroup>{
	
	private DaoUsersService usersService;
	private DaoTeacherCourseGroupService teacherCourseGroupService;
	
	public static int STUDENTGROUPPARAWRONG = 20001;
	public static int STUDENTGROUPIDNOTFIND = 20002;
	public static String tablename = "StudentCourseGroup";
	
	
	
	public void setUsersService(DaoUsersService usersService) {
		this.usersService = usersService;
	}

	public void setTeacherCourseGroupService(
			DaoTeacherCourseGroupService teacherCourseGroupService) {
		this.teacherCourseGroupService = teacherCourseGroupService;
	}

	
	
	public String save(String jsonString, Integer operId) throws JsonParseException, JsonMappingException, IOException{
		
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
		
		if(null == map.get("studentId") || null == map.get("teacherCourseGroupId")){
			return Cause.getFailcode(STUDENTGROUPPARAWRONG, "para", "para wrong");
		}
		
		
		Users users = usersService.searchByid(Integer.parseInt(map.get("studentId").toString()), DaoUsersService.tablename);
		
		TeacherCourseGroup  teachercoursegroup = teacherCourseGroupService.searchByid(Integer.parseInt(map.get("teacherCourseGroupId").toString()), DaoTeacherCourseGroupService.tablename);
		
		if(null == users || null == teachercoursegroup){
			return Cause.getFailcode(STUDENTGROUPPARAWRONG, "para", "para  id can not find");
		}
		
		StudentCourseGroup studentcoursegroup = new StudentCourseGroup(users, teachercoursegroup, new Date(), operId);
		studentcoursegroup.setId(Session.getOperId());
		
		Integer tempid = create(studentcoursegroup);
	
		return Cause.getSuccess(tempid);
	}
	
	public String upd(Integer id, String jsonString) throws Exception{
		
		StudentCourseGroup studentcoursegroup = null;
		
		studentcoursegroup = searchByid(id, tablename);
		
		if(null == studentcoursegroup){
			
			return Cause.getFailcode(STUDENTGROUPIDNOTFIND, "id", "student group id not find");
		}
		
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
		
		if(null == map.get("studentId") && null == map.get("teacherCourseGroupId")){
			return Cause.getFailcode(STUDENTGROUPPARAWRONG, "para", "para wrong");
		}
		
		Users users = null;
		TeacherCourseGroup  teachercoursegroup = null;
		
		if(null != map.get("studentId")){
			users = usersService.searchByid(Integer.parseInt(map.get("studentId").toString()), DaoUsersService.tablename);
			if(null == users){
				return Cause.getFailcode(STUDENTGROUPPARAWRONG, "para", "para  id can not find");
			}
		}
		
		if(null != map.get("teacherCourseGroupId")){
			teachercoursegroup = teacherCourseGroupService.searchByid(Integer.parseInt(map.get("teacherCourseGroupId").toString()), DaoTeacherCourseGroupService.tablename);
			if(null == teachercoursegroup){
				return Cause.getFailcode(STUDENTGROUPPARAWRONG, "para", "para  id can not find");
			}
		}
		
		
		if(null == users && null == teachercoursegroup){
			return Cause.getFailcode(STUDENTGROUPPARAWRONG, "para", "para  id can not find");
		}
		
		if(null != teachercoursegroup){
			studentcoursegroup.setTeacherCourseGroup(teachercoursegroup);
		}
		
		if(null != users){
			studentcoursegroup.setUsers(users);
		}
		
		
		update(studentcoursegroup);
	
		return Cause.getSuccess(studentcoursegroup.getId());
	}
	
	
	public String del(Integer id){
		
		StudentCourseGroup studentcoursegroup = null;
		
		studentcoursegroup = searchByid(id, tablename);
		
		if(null == studentcoursegroup){
			
			return Cause.getFailcode(STUDENTGROUPIDNOTFIND, "id", "student group id not find");
		}
		
		delete(StudentCourseGroup.class, studentcoursegroup.getId());
		
		return Cause.getSuccess(studentcoursegroup.getId());
	}
	
	public String getGroupstudentInfo(Integer id){
		
		List<Object> list = searchByFeildList(tablename, "teacherCourseGroup", id);
		
		List<Object> resultlist = new ArrayList<Object>();
		for(Object obj:list){
			
			Map<String, Object> map = Obj2Map.toMapRecursive(obj, StudentCourseGroup.class);
			map.remove("teacherCourseGroup");
			map.put("teacherCourseGroupId", id);
			resultlist.add(map);
		}
		
		return Cause.getData(resultlist);
	}

}
