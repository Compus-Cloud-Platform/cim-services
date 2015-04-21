package com.college.account;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.college.account.bean.Organization;
import com.college.account.service.OrganizationService;

public class Test
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("basic-config.xml");
        OrganizationService service = (OrganizationService)ctx.getBean("organizationService");
        //service.createOrganization(new Organization("Xibei nongling", "006", "yangling", "college", new Date(), new Date(), 22));
        /*service.create(new Organization("Jiao tong University", "002", "Shanghai", "college1", new Date(), new Date(), 22));
        service.create(new Organization("Xibei University", "003", "Shanghai", "college2", new Date(), new Date(), 22));
        service.create(new Organization("Fudan University", "004", "Shanghai", "college3", new Date(), new Date(), 22));
        service.create(new Organization("Lanzhou University", "005", "Shanghai", "college4", new Date(), new Date(), 22));
        /*Organization o = (Organization)service.get(Organization.class,1);
        o.setAddress("Xi'an");
        System.out.println(o.toString());
        service.update(o);
        Organization o1 = (Organization)service.get(Organization.class,1);
        System.out.println(o1.toString());*/
    }
    
}
