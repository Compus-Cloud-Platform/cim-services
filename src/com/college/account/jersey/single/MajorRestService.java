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


import com.college.account.service.DaoMajorService;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/majors")
public class MajorRestService {

	private static final Logger log = Logger4j.getLogger(MajorRestService.class);
	private static DaoMajorService pM = ServiceFactoryBean.getMajorService();

	
	
	@GET
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
	@Path("/{id}")
	public String getMajorOneObject(@PathParam("id") String id){
		try {
			
			return pM.getObject(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public String saveMaj(String jsonString){
		try {
			
			return pM.save(jsonString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@DELETE
	@Path("/{id}")
	public String deleteMaj(@PathParam("id") String id){
		try {
			
			return pM.del(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String updateMaj(@PathParam("id") String id, String jsonString){
		try {
			
			return pM.upd(Integer.parseInt(id), jsonString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(10000, "", "system error");
	}
}
