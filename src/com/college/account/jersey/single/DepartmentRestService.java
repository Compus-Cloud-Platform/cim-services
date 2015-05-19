package com.college.account.jersey.single;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.college.account.service.DaoDepartmentService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/departments")
public class DepartmentRestService {

	private static final Logger log = Logger4j.getLogger(DepartmentRestService.class);
	private static DaoDepartmentService pD = ServiceFactoryBean.getDepartmentService();
	
	
	@GET
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
	@Path("/{id}")
	public String getDepartmentOneObject(@PathParam("id") String id){
		try {
			
			return pD.getObject(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public String saveDep(String jsonString){
		try {
			
			return pD.save(jsonString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@DELETE
	@Path("/{id}")
	public String deleteDep(@PathParam("id") String id){
		try {
			
			return pD.del(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String updateDep(@PathParam("id") String id, String jsonString){
		try {
			
			return pD.upd(Integer.parseInt(id), jsonString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
}
