package com.college.account.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;

import com.college.account.service.DaoDepartmentService;
import com.college.account.service.DaoMajorService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("")
public class ResourceRestService {
	private static final Logger log = Logger4j.getLogger(ResourceRestService.class);
	private static DaoDepartmentService pD = ServiceFactoryBean.getDepartmentService();
	private static DaoMajorService pM = ServiceFactoryBean.getMajorService();

	@GET
	@Path("/departments")
	public String getDepartmentAllObject(){
		try {
			
			return pD.getAllObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@GET
	@Path("/departments/{id}")
	public String getDepartmentOneObject(@PathParam("id") String id){
		try {
			
			return pD.getObject(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@GET
	@Path("/majors")
	public String getMajorAllObject(){
		try {
			
			return pM.getAllObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@GET
	@Path("/majors/{id}")
	public String getMajorOneObject(@PathParam("id") String id){
		try {
			
			return pM.getObject(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
}
