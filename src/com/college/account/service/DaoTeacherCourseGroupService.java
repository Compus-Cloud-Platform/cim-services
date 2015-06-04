package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.TeacherCourseGroup;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoTeacherCourseGroupService extends  DaoService<TeacherCourseGroup>{
	
	public static String tablename = "TeacherCourseGroup";
	
	public static int COURSEGROUPIDNOTFIND = 19001;
	public static int COURSEGROUPNAMENULL = 19002;
	public static int COURSEGROUPIDBNULL = 19003;
	
	private DaoUsersService usersService;
	private DaoStudentCourseService studentCourseService;
	
	public void setStudentCourseService(DaoStudentCourseService studentCourseService) {
		this.studentCourseService = studentCourseService;
	}

	public void setUsersService(DaoUsersService usersService) {
		this.usersService = usersService;
	}

	
		

	public boolean isExist(Integer id){
		
		TeacherCourseGroup teachercoursegroup = null;
		
		if(null == id)return false;
		
		teachercoursegroup = searchByid(id, tablename);
		
		return (null == teachercoursegroup)?false:true;
	}
	
	public boolean selNameUniq(String name, Integer tearcherCourseId){
		
		TeacherCourseGroup teachercoursegroup = searchByMutiFeild(tablename, "nameteacherCourseId", new Object[]{name, tearcherCourseId});
		
		return (null != teachercoursegroup)?true:false;
	}
	
	public String save(String jsonString){

		TeacherCourseGroup teachercoursegroup = (TeacherCourseGroup)Json2Obj.getObj(jsonString, TeacherCourseGroup.class);
		
		if(null == teachercoursegroup.getName()){
			return Cause.getFailcode(COURSEGROUPNAMENULL, "name", "name can not null");
		}
		
		if(null == teachercoursegroup.getTeacherCourseId()){
			return Cause.getFailcode(COURSEGROUPIDBNULL, "id", "teachercoursegroup id can not null");
		}
		
		if(selNameUniq(teachercoursegroup.getName(), teachercoursegroup.getTeacherCourseId())){
			return Cause.getFailcode(COURSEGROUPIDBNULL, "name", "name is exist");
		}

		teachercoursegroup.setCreateTime(new Date());
		
		Integer id = create(teachercoursegroup);
		
	    return Cause.getSuccess(id);
	}
	
	
	public String sel(Integer id){
		
		TeacherCourseGroup teachercoursegroup = searchByid(id, tablename);
		
		if(null == teachercoursegroup){
			
			return Cause.getFailcode(COURSEGROUPIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(teachercoursegroup, TeacherCourseGroup.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String getAllObject(){
		
		List<Object> list = searchAll(tablename);
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		TeacherCourseGroup teachercoursegroup = null;
		TeacherCourseGroup teachercoursegroupfind = null;
		
		teachercoursegroup = (TeacherCourseGroup)Json2Obj.getObj(jsonString, TeacherCourseGroup.class);
		
		teachercoursegroupfind = searchByid(id, tablename);
		
		if(null == teachercoursegroupfind){
			return Cause.getFailcode(COURSEGROUPIDNOTFIND, "id", "org id not find");
		}
		
		
		Json2Obj.updateObject(teachercoursegroup, teachercoursegroupfind);
		
		update(teachercoursegroupfind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		TeacherCourseGroup teachercoursegroup = null;
		
		teachercoursegroup = searchByid(id, tablename);
		
		if(null == teachercoursegroup){
			return Cause.getFailcode(COURSEGROUPIDNOTFIND, "id", "org id not find");
		}
		
		delete(TeacherCourseGroup.class, id);
		
		return Cause.getSuccess(id);
	}
	
	public String getGroupUserInfoByCourse(Integer id){
		
		List<Object> list = searchByFeildList(tablename, "teacherCourseId", id);
		
		for(Object obj:list){
			TeacherCourseGroup teachercoursegroup = (TeacherCourseGroup)obj;
		}
		
		return Cause.getData(list);

	}
}
