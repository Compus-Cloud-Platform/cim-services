package com.college.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.college.account.service.OrganizationService;

public class ServiceFactoryBean
{
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("basic-config.xml");
    
    public static Object getService(String beanName)
    {
        Object service = null;
        
        return ctx.getBean(beanName);
    }
    
    public static OrganizationService getOrganizationService()
    {
        return (OrganizationService) getService("organizationService");
    }
}
