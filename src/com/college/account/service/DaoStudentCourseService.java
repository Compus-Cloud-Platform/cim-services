package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Course;
import com.college.account.bean.StudentCourse;
import com.college.account.bean.TeacherCourse;
import com.college.account.bean.TeacherCourseGroup;
import com.college.account.bean.Users;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoStudentCourseService extends  DaoService<StudentCourse>{
	
	public static String tablename = "StudentCourse";
	public static int STUDENTCOURSEIDNOTFIND = 16001;
	public static int STUDENTCOURSECANOTUPDATE = 16001;
	
	private DaoUsersService usersService;
	private DaoTeacherCourseService teacherCourseService;
	private DaoTeacherCourseGroupService teacherCourseGroupService;
	private DaoCourseService courseService;
	
	public void setCourseService(DaoCourseService courseService) {
		this.courseService = courseService;
	}

	public void setUsersService(DaoUsersService usersService) {
		this.usersService = usersService;
	}

	public void setTeacherCourseService(DaoTeacherCourseService teacherCourseService) {
		this.teacherCourseService = teacherCourseService;
	}

	public void setTeacherCourseGroupService(
			DaoTeacherCourseGroupService teacherCourseGroupService) {
		this.teacherCourseGroupService = teacherCourseGroupService;
	}

	

	public String save(String jsonString, Integer operId){
		
		StudentCourse studentcourse = null;
		studentcourse = (StudentCourse)Json2Obj.getObj(jsonString, StudentCourse.class);
		
		Integer userid = studentcourse.getLoginId();
		Integer teCourseid = studentcourse.getTeacherCourseId();
		Integer teCourseGroupid = studentcourse.getTeacherCourseGroupId();
		
		if(null == userid || !usersService.isExist(userid)){
			
			return Cause.getFailcode(DaoUsersService.USEIDNOTEXIST, "Id", "id not exist or find");
		}
		
		if(null == teCourseid || !teacherCourseService.isExist(teCourseid)){
			return Cause.getFailcode(DaoTeacherCourseService.TEACHERCOURSEIDNOTFIND, "Id", "id not exist or find");
		}
		
		if(null != teCourseGroupid && !teacherCourseGroupService.isExist(teCourseGroupid)){
			return Cause.getFailcode(DaoTeacherCourseGroupService.COURSEGROUPIDNOTFIND, "Id", "id not exist or find");
		}
		
		studentcourse.setCreateTime(new Date());
		studentcourse.setId(Session.getOperId());
		
		Integer idTemp = create(studentcourse);
		
		return Cause.getSuccess(idTemp);
	}
	
	public String upd(Integer id, String jsonString, Integer operId){
		
		StudentCourse studentcourse = null;
		StudentCourse studentcoursefind = searchByid(id, tablename);
		
		if(null == studentcoursefind){
			return Cause.getFailcode(STUDENTCOURSEIDNOTFIND, "Id", "id not find");
		}
		
		studentcourse = (StudentCourse)Json2Obj.getObj(jsonString, StudentCourse.class);
		
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
	
	
	public String selStudentAllCourse(Integer id){
		
		List<Object> list = searchByFeildList(tablename, "loginId", id);
		
		List<Object> resultlist = new ArrayList<Object>();
		
		for(Object obj:list){
			StudentCourse studentcourse = (StudentCourse)obj;
			
			Map<String, Object> map =  Obj2Map.toMap(studentcourse, StudentCourse.class);
			
			Integer tecourseid = studentcourse.getTeacherCourseId();
			
			TeacherCourse teachercourse= teacherCourseService.searchByid(tecourseid, DaoTeacherCourseService.tablename);
			
			Integer courseid = teachercourse.getCourseId();
			
			Map<String, Object> coursemap =  Obj2Map.toMap(courseService.searchByid(courseid, DaoCourseService.tablename), Course.class);
			
			map.put("course", coursemap);
			
			resultlist.add(map);
		}
		
		return  Cause.getData(resultlist);
	}
	
	public String selone(Integer id){
		
		List<Object> resultlist = new ArrayList<Object>();
		
	
		StudentCourse studentcourse = searchByid(id, tablename);
		
		Map<String, Object> map =  Obj2Map.toMap(studentcourse, StudentCourse.class);
		
		Integer tecourseid = studentcourse.getTeacherCourseId();
		
		TeacherCourse teachercourse= teacherCourseService.searchByid(tecourseid, DaoTeacherCourseService.tablename);
		
		Integer courseid = teachercourse.getCourseId();
		
		Map<String, Object> coursemap =  Obj2Map.toMap(courseService.searchByid(courseid, DaoCourseService.tablename), Course.class);
		
		map.put("course", coursemap);
		
		resultlist.add(map);

		
		return  Cause.getData(resultlist);
	}
	
	public String getCourseAllStudent(Integer id){
			
		List<Object> list = searchByFeildList(tablename, "teacherCourseId", id);
		
		List<Object> resultlist = new ArrayList<Object>();
		
		for(Object obj:list){
		
			StudentCourse studentcourse = (StudentCourse)obj;
			
			Map<String, Object> stuCourseMap = Obj2Map.toMap(studentcourse, StudentCourse.class);
			
			Users users = usersService.searchByid(studentcourse.getLoginId(), DaoUsersService.tablename);
			TeacherCourseGroup teachercoursegroup = teacherCourseGroupService.searchByid(studentcourse.getTeacherCourseGroupId(), DaoTeacherCourseGroupService.tablename);
				
			Map<String, Object> userMap = Obj2Map.toMapRecursive(users, Users.class);
			Map<String, Object> tgroupMap = Obj2Map.toMap(teachercoursegroup, TeacherCourseGroup.class);
			
			stuCourseMap.put("user", userMap);
			stuCourseMap.put("teachercoursegroup", tgroupMap);
			
			resultlist.add(stuCourseMap);
		}
		
		return  Cause.getData(resultlist);
	}
	
	public String getCourseGroupAllStudent(Integer id){
	    
	    List<Object> list = searchByFeildList(tablename, "teacherCourseGroupId", id);
        
        List<Object> resultlist = new ArrayList<Object>();
        
        for(Object obj:list){
        
            StudentCourse studentcourse = (StudentCourse)obj;
            
            Map<String, Object> stuCourseMap = Obj2Map.toMap(studentcourse, StudentCourse.class);
            
            Users users = usersService.searchByid(studentcourse.getLoginId(), DaoUsersService.tablename);
                
            Map<String, Object> userMap = Obj2Map.toMapRecursive(users, Users.class);
            
            stuCourseMap.put("user", userMap);
            
            resultlist.add(stuCourseMap);
        }
        
        return  Cause.getData(resultlist);
	    
	}
	
}
