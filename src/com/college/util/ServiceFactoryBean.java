package com.college.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.college.account.bean.Club;
import com.college.account.bean.Course;
import com.college.account.bean.Department;
import com.college.account.bean.DeptOrg;
import com.college.account.bean.Major;
import com.college.account.bean.MajorDept;
import com.college.account.bean.Organization;
import com.college.account.bean.Permission;
import com.college.account.bean.Position;
import com.college.account.bean.Role;
import com.college.account.bean.StudentCourse;
import com.college.account.bean.TeacherCourse;
import com.college.account.bean.TeacherCourseGroup;
import com.college.account.bean.UserAdminOrg;
import com.college.account.bean.UserStudentMajor;
import com.college.account.bean.UserTeacherDept;
import com.college.account.bean.Users;
import com.college.account.bean.UsersExt;
import com.college.account.service.DaoOrganizationService;
import com.college.account.service.DaoPositionService;
import com.college.account.service.DaoUsersService;
import com.college.account.service.DaoUsersServiceExt;
import com.college.account.service.Service;

public class ServiceFactoryBean
{
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("basic-config.xml");
    
    public static Object getService(String beanName)
    {
    	
        @SuppressWarnings("unused")
		Object service = null;
        return ctx.getBean(beanName);
    }
    
    public static DaoOrganizationService getOrganizationService(){return (DaoOrganizationService) getService("organizationService");}
    public static DaoUsersService getUserService(){return (DaoUsersService) getService("userService");}
    public static DaoUsersServiceExt getUserServiceExt(){return (DaoUsersServiceExt) getService("userServiceExt");}
    public static DaoPositionService getPositionService(){return (DaoPositionService) getService("positionService");}
    @SuppressWarnings("unchecked")
	public static Service<Department> getDepartmentService(){return (Service<Department>) getService("departmentService");}
    @SuppressWarnings("unchecked")
    public static Service<Major> getMajorService(){return (Service<Major>) getService("majorService");}
    @SuppressWarnings("unchecked")
    public static Service<Course> getCourseService(){return (Service<Course>) getService("courseService");}
    @SuppressWarnings("unchecked")
    public static Service<Club> getClubService(){return (Service<Club>) getService("clubService");}
    @SuppressWarnings("unchecked")
    public static Service<Role> getRoleService(){return (Service<Role>) getService("roleService");}
    @SuppressWarnings("unchecked")
    public static Service<Permission> getPermissionService(){return (Service<Permission>) getService("permissionService");}
    @SuppressWarnings("unchecked")
    public static Service<DeptOrg> getDeptOrgService(){return (Service<DeptOrg>) getService("deptOrgService");}
    @SuppressWarnings("unchecked")
    public static Service<MajorDept> getMajorDeptService(){return (Service<MajorDept>) getService("majorDeptService");}
    @SuppressWarnings("unchecked")
    public static Service<StudentCourse> getStudentCourseService(){return (Service<StudentCourse>) getService("studentCourseService");}
    @SuppressWarnings("unchecked")
    public static Service<TeacherCourse> getTeacherCourseService(){return (Service<TeacherCourse>) getService("teacherCourseService");}
    @SuppressWarnings("unchecked")
    public static Service<TeacherCourseGroup> getTeacherCourseGroupService(){return (Service<TeacherCourseGroup>) getService("teacherCourseGroupService");}
    @SuppressWarnings("unchecked")
    public static Service<UserAdminOrg> getUserAdminOrgService(){return (Service<UserAdminOrg>) getService("userAdminOrgService");}
    @SuppressWarnings("unchecked")
    public static Service<UserStudentMajor> getUserStudentMajorService(){return (Service<UserStudentMajor>) getService("userStudentMajorService");}
    @SuppressWarnings("unchecked")
    public static Service<UserTeacherDept> getUserTeacherDeptService(){return (Service<UserTeacherDept>) getService("userTeacherDeptService");}
    
}
