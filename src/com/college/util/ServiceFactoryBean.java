package com.college.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.college.account.bean.Users;
import com.college.account.service.OrganizationService;
import com.college.account.service.PositionService;
import com.college.account.service.UsersService;
import com.college.account.service.UsersServiceExt;

public class ServiceFactoryBean
{
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("basic-config.xml");
    
    private static UsersService userservice = (UsersService) getService("userService");
    private static UsersServiceExt userserviceext = (UsersServiceExt) getService("userServiceExt");
    private static PositionService positionservice = (PositionService) getService("positionService");
    
    public static Object getService(String beanName)
    {
        Object service = null;
        
        return ctx.getBean(beanName);
    }
    
    public static OrganizationService getOrganizationService()
    {
        return (OrganizationService) getService("organizationService");
    }
    
    public static UsersService getUserService()
    {
    	return userservice;
    }
    
    public static UsersServiceExt getUserServiceExt()
    {
    	return userserviceext;
    }
    
    public static PositionService getPositionService()
    {
    	return positionservice;
    }
}
