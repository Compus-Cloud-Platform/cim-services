package com.college.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.college.account.service.DaoClubService;
import com.college.account.service.DaoCourseService;
import com.college.account.service.DaoDepartmentService;
import com.college.account.service.DaoDeptOrgService;
import com.college.account.service.DaoMajorDeptOrgService;
import com.college.account.service.DaoMajorService;
import com.college.account.service.DaoMediaCategoryService;
import com.college.account.service.DaoMediaResourceService;
import com.college.account.service.DaoMediaTagMapService;
import com.college.account.service.DaoMediaTagService;
import com.college.account.service.DaoOrganizationService;
import com.college.account.service.DaoPermissionService;
import com.college.account.service.DaoPositionService;
import com.college.account.service.DaoRoleService;
import com.college.account.service.DaoStudentCourseGroupService;
import com.college.account.service.DaoStudentCourseService;
import com.college.account.service.DaoTeacherCourseGroupService;
import com.college.account.service.DaoTeacherCourseService;
import com.college.account.service.DaoUserAdminOrgService;
import com.college.account.service.DaoUserStudentMajorService;
import com.college.account.service.DaoUserTeacherDeptService;
import com.college.account.service.DaoUsersService;
import com.college.account.service.DaoUsersServiceExt;
import com.college.common.DaoUploadService;

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
	public static DaoDepartmentService getDepartmentService(){return (DaoDepartmentService) getService("departmentService");}
    public static DaoMajorService getMajorService(){return (DaoMajorService) getService("majorService");}
    public static DaoCourseService getCourseService(){return (DaoCourseService) getService("courseService");}
    public static DaoClubService getClubService(){return (DaoClubService) getService("clubService");}
    public static DaoRoleService getRoleService(){return (DaoRoleService) getService("roleService");}
    public static DaoPermissionService getPermissionService(){return (DaoPermissionService) getService("permissionService");}
    public static DaoDeptOrgService getDeptOrgService(){return (DaoDeptOrgService) getService("deptOrgService");}
    public static DaoMajorDeptOrgService getMajorDeptService(){return (DaoMajorDeptOrgService) getService("majorDeptOrgService");}
    public static DaoUserAdminOrgService getUserAdminOrgService(){return (DaoUserAdminOrgService) getService("userAdminOrgService");}
    public static DaoUserStudentMajorService getUserStudentMajorService(){return (DaoUserStudentMajorService) getService("userStudentMajorService");}
    public static DaoUserTeacherDeptService getUserTeacherDeptService(){return (DaoUserTeacherDeptService) getService("userTeacherDeptService");}
    public static DaoTeacherCourseService getTeacherCourseService(){return (DaoTeacherCourseService) getService("teacherCourseService");}
    public static DaoStudentCourseService getStudentCourseService(){return (DaoStudentCourseService) getService("studentCourseService");}
    public static DaoTeacherCourseGroupService getTeacherCourseGroupService(){return (DaoTeacherCourseGroupService) getService("teacherCourseGroupService");}
    public static DaoStudentCourseGroupService getStudentCourseGroupService(){return (DaoStudentCourseGroupService) getService("studentCourseGroupService");}
    
    public static DaoMediaCategoryService getMediaCategoryService(){return (DaoMediaCategoryService) getService("mediaCategoryService");}
    public static DaoMediaResourceService getMediaResourceService(){return (DaoMediaResourceService) getService("mediaResourceService");}
    public static DaoMediaTagMapService getMediaTagMapService(){return (DaoMediaTagMapService) getService("mediaTagMapService");}
    public static DaoMediaTagService getMediaTagService(){return (DaoMediaTagService) getService("mediaTagService");}
    //common
    public static DaoUploadService getUploadService() {return (DaoUploadService) getService("service.Upload");}
    
    
    
}
