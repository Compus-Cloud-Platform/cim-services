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
import com.college.account.service.OrganizationService;
import com.college.account.service.Service;
import com.college.account.service.UsersService;
import com.college.account.service.UsersServiceExt;

public class ServiceFactoryBean
{
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("basic-config.xml");
    private static UsersService userservice = (UsersService) getService("userService");
    private static UsersServiceExt userserviceext = (UsersServiceExt) getService("userServiceExt");
    
    public static Object getService(String beanName)
    {
        Object service = null;
        return ctx.getBean(beanName);
    }
    
    public static Service<Organization> getOrganizationService(){return (Service<Organization>) getService("organizationService");}
    public static UsersService getUserService(){return userservice;}
    public static UsersServiceExt getUserServiceExt(){return userserviceext;}
    public static Service<Position> getPositionService(){return (Service<Position>) getService("positionService");}
    public static Service<Department> getDepartmentService(){return (Service<Department>) getService("departmentService");}
    public static Service<Major> getMajorService(){return (Service<Major>) getService("majorService");}
    public static Service<Course> getCourseService(){return (Service<Course>) getService("courseService");}
    public static Service<Club> getClubService(){return (Service<Club>) getService("clubService");}
    public static Service<Role> getRoleService(){return (Service<Role>) getService("roleService");}
    public static Service<Permission> getPermissionService(){return (Service<Permission>) getService("permissionService");}
    public static Service<DeptOrg> getDeptOrgService(){return (Service<DeptOrg>) getService("deptOrgService");}
    public static Service<MajorDept> getMajorDeptService(){return (Service<MajorDept>) getService("majorDeptService");}
    public static Service<StudentCourse> getStudentCourseService(){return (Service<StudentCourse>) getService("studentCourseService");}
    
    public static Service<TeacherCourse> getTeacherCourseService(){return (Service<TeacherCourse>) getService("teacherCourseService");}
    public static Service<TeacherCourseGroup> getTeacherCourseGroupService(){return (Service<TeacherCourseGroup>) getService("teacherCourseGroupService");}
    public static Service<UserAdminOrg> getUserAdminOrgService(){return (Service<UserAdminOrg>) getService("userAdminOrgService");}
    public static Service<UserStudentMajor> getUserStudentMajorService(){return (Service<UserStudentMajor>) getService("userStudentMajorService");}
    public static Service<UserTeacherDept> getUserTeacherDeptService(){return (Service<UserTeacherDept>) getService("userTeacherDeptService");}
    
}
