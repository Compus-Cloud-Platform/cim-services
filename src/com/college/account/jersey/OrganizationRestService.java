package com.college.account.jersey;

import java.util.HashMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.college.account.bean.Department;
import com.college.account.bean.DeptOrg;
import com.college.account.bean.Organization;
import com.college.account.service.DaoOrganizationService;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Logger4j;
import com.college.util.Obj2Map;
import com.college.util.ServiceFactoryBean;


@Path("/organizations")
public class OrganizationRestService
{
    private int ORGANIZATIONLOSTID = 2001;
    private int ORGANIZATIONLOSTNAME = 2002;
    private static DaoOrganizationService p = ServiceFactoryBean.getOrganizationService();
    private static final Logger log = Logger4j.getLogger(OrganizationRestService.class);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(String jsonString){
    	
    	try {
			
			return p.save(jsonString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(2000, "", "system error");
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
		
		return Cause.getFailcode(2000, "", "system error");
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
		
		return Cause.getFailcode(2000, "", "system error");
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
		
		return Cause.getFailcode(1000, "", "system error");
		
	}
    
    
    
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}/departments")
    public String saveOgaDep(@PathParam("id") String id,
                                              String jsonString)
    {
        Integer idtemp = null;
        
        Organization organization = ServiceFactoryBean.getOrganizationService().searchByid(Integer.parseInt(id), "Organization");
        
        if(null == organization){
            Cause.getFailcode(ORGANIZATIONLOSTID, "id", null);
        }
        
        Department department = null;
        department = (Department)Json2Obj.getObj(jsonString, Department.class);
        
        if(null == department.getName()){
            return Cause.getFailcode(ORGANIZATIONLOSTNAME, "name", null);
        }
        
        department.setCreateTime(new Date());
        
        idtemp = ServiceFactoryBean.getDepartmentService().create(department);
        
        department.setId(idtemp);
        
        DeptOrg deptorg = new DeptOrg();
        
        deptorg.setCreateTime(new Date());
        deptorg.setDeptId(idtemp);
        deptorg.setOrgId(Integer.parseInt(id));
        
        ServiceFactoryBean.getDeptOrgService().create(deptorg);
        
        List list = new ArrayList(1);
        
        list.add(Obj2Map.toMap(department, Department.class));
        
        return Cause.getData(list);
    }
}
