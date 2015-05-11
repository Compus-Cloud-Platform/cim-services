package com.college.account.jersey;

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
	
	private static DaoMajorService p = ServiceFactoryBean.getMajorService();
    private static final Logger log = Logger4j.getLogger(MajorRestService.class);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(String jsonString){
    	
    	try {
			
			return p.save(jsonString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(5000, "", "system error");
    }
    
    @PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String updateInfo(@PathParam("id") String id,
	                                          String jsonString){
		try {
			
			String result=  p.upd(Integer.parseInt(id), jsonString);
			return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(5000, "", "system error");
	}
    
    @DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String deleteInfo(@PathParam("id") String id){
		try {
			
			String result=  p.del(Integer.parseInt(id));
			return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(5000, "", "system error");
	}
    
    @GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String searchInfo(@PathParam("id") String id)
	{
		try {
			
			return p.sel(Integer.parseInt(id));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(5000, "", "system error");
		
	}

}
