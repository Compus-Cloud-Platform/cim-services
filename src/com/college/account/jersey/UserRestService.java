package com.college.account.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.college.account.service.DaoUsersService;
import com.college.account.service.DaoUsersServiceExt;
import com.college.util.Cause;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;

@Path("/users")
public class UserRestService {

	private static final Logger log = Logger4j.getLogger(UserRestService.class);
	
	private static DaoUsersService p = ServiceFactoryBean.getUserService();
	private static DaoUsersServiceExt pext = ServiceFactoryBean.getUserServiceExt();
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(String jsonString){
		
		try {
			
			String result=  p.save(jsonString);
			
			if(!Cause.isSuccess(result)){
				return result;
			}
			
			pext.save(jsonString);
			
			return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(1000, "", "system error");
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public String updateInfo(@PathParam("id") String id,
	                                          String jsonString){
		try {
			
			String result[]=  p.upd(Integer.parseInt(id), jsonString);
			
			if(!Cause.isSuccess(result[0])){
				return result[0];
			}
			
			pext.upd(result[1], jsonString);
			
			return result[0];
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		
		return Cause.getFailcode(1000, "", "system error");
	}
}
