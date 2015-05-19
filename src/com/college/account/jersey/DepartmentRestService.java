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

import com.college.account.bean.Department;
import com.college.account.service.DaoDepartmentService;
import com.college.account.service.DaoDeptOrgService;
import com.college.account.service.DaoOrganizationService;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("/organizations/{id}/departments")
public class DepartmentRestService {

	private static DaoOrganizationService p = ServiceFactoryBean.getOrganizationService();
	private static DaoDepartmentService pD = ServiceFactoryBean.getDepartmentService();
    private static DaoDeptOrgService pDO = ServiceFactoryBean.getDeptOrgService();
    private static final Logger log = Logger4j.getLogger(DepartmentRestService.class);
    
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public String saveDep(@PathParam("id") String id,
                                           String jsonString){
    	try {
			
			if(!p.selIsExist(Integer.parseInt(id))){
				return Cause.getFailcode(DaoOrganizationService.ORGIDNOTFIND, "id", "id not find");
			}
			
			Department department = (Department)Json2Obj.getObj(jsonString, Department.class);
			
			if(null == department.getId()){
				/* need save*/
				if(null == department.getName()){
					return Cause.getFailcode(DaoDepartmentService.DEPARTMENTLOSTNAME, "name", "must has name");
				}else{
					Integer resultid = null;
					if(pD.selNameUniq(department.getName())){
						resultid = Cause.getResultId(pD.save(jsonString));
					}else{
						resultid = ((Department)pD.searchByFeild(DaoDepartmentService.tablename, "name", department.getName())).getId();
					}
					
					pDO.save(Integer.parseInt(id), resultid, department.getOperId());
					
					return Cause.getSuccess(resultid);
				}
			}
			
			if(!pD.selIsExist(department.getId())){
				return Cause.getFailcode(DaoOrganizationService.ORGIDNOTFIND, "id", "id not find");
			}
			
			pDO.save(Integer.parseInt(id), department.getId(), department.getOperId());
			
			return Cause.getSuccess(department.getId());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(3000, "", "system error");
    }
	
	@POST
	@Path("/{idD}")
    public String save(@PathParam("id") String id,
    		              @PathParam("idD") String idD){
		return null;
		
	}
    @DELETE
    @Path("/{idD}")
    public String deleteDep(@PathParam("id") String id,
    		                @PathParam("idD") String idD){
    	
    	try{
    		return pDO.del(id, idD);
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(3000, "", "system error");
    	
    }
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public String getDep(@PathParam("id") String id){
    	
    	try{
    		
    		return pDO.get(id);
    		
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(3000, "", "system error");
    }
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{idD}")
    public String getOneDep(@PathParam("id") String id,
                            @PathParam("idD") String idD){
    	try{
    		
    		return pDO.getOne(id, idD);
    		
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(3000, "", "system error");
    }	
	

}
