package com.college.account.jersey;

import java.util.HashMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
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
    
    
    @GET
    @Path("/getOrganizations")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getInfo() 
    {
        String result = null;
    
        return result;
    }
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/deleteOrganization")
    public String deleteInfo(@QueryParam("id") String idString)
    {
        return "success";
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
