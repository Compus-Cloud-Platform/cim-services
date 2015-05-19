package com.college.account.jersey;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.college.account.bean.StudentCourse;
import com.college.account.bean.TeacherCourse;
import com.college.account.jersey.single.TeacherCourseRestService;
import com.college.account.service.DaoCourseService;
import com.college.account.service.DaoStudentCourseService;
import com.college.account.service.DaoTeacherCourseService;
import com.college.account.service.DaoUsersService;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("/students/")
public class StudentRestService {

	private static DaoStudentCourseService p = ServiceFactoryBean.getStudentCourseService();
	private static DaoUsersService pU = ServiceFactoryBean.getUserService();
	private static DaoTeacherCourseService pT = ServiceFactoryBean.getTeacherCourseService();
	private static DaoCourseService pD = ServiceFactoryBean.getCourseService();
	
	private static final Logger log = Logger4j.getLogger(TeacherCourseRestService.class);
	 
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	@Path("/{id}/courses")
	public String save(@PathParam("id") String id,
			           String jsonString){
		try {
			
			if(!pU.isExist(Integer.parseInt(id))){
    			
    			return Cause.getFailcode(DaoUsersService.USEIDNOTEXIST, "Id", "id not find");
    		}
			
			StudentCourse studentcourse = (StudentCourse)Json2Obj.getObj(jsonString, StudentCourse.class);
			
			if(!pT.isExist(studentcourse.getTeacherCourseId())){
    			
    			return Cause.getFailcode(DaoTeacherCourseService.TEACHERCOURSEIDNOTFIND, "Id", "id not find");
    		}

			return p.save(Integer.parseInt(id), jsonString, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(16000, "", "system error");
	}
	
	@GET
	@Path("/{id}/courses")
	public String getAllcourseforstudent(@PathParam("id") String id){
		
		try {

			List<Integer> teachercourseidlist = p.getAllObject(Integer.parseInt(id));
			List<Integer> courselist = new ArrayList<Integer>();
			for(Integer tempId:teachercourseidlist){
				
				TeacherCourse teachercourse = pT.searchByid(tempId, DaoTeacherCourseService.tablename);
				
				courselist.add(teachercourse.getCourseId());
			}
			
			
			return pD.getList(courselist);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}

		return Cause.getFailcode(16000, "", "system error");
	}
	
	@GET
	@Path("/{id}/teachercourse/{idTC}")
	public String getOnecourseforstudent(@PathParam("id") String id,
			                             @PathParam("idTC") String idTC){
		
		try {

			Integer teachercourseid = p.getOneObject(Integer.parseInt(id), Integer.parseInt(idTC));
			
			TeacherCourse teachercourse = pT.searchByid(teachercourseid, DaoTeacherCourseService.tablename);
			
			return pD.sel(teachercourse.getCourseId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(16000, "", "system error");
	}
	
}
