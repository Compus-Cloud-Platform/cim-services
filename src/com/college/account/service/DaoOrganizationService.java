package com.college.account.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.Organization;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

/**
 * @author Dennis Dai
 */
public class DaoOrganizationService extends  DaoService<Organization>
{
	public static int ORGIDNOTFIND = 3001;
	public static int ORGNAMENULL = 3002;
	public static int ORGOPERIDNULL = 3003;
	public String tablename = "Organization";
	
	public String save(String jsonString){

		Organization organization = (Organization)Json2Obj.getObj(jsonString, Organization.class);
		
		if(null == organization.getName()){
			return Cause.getFailcode(ORGNAMENULL, "name", "name can not null");
		}
		
		if(null == organization.getOperId()){
			return Cause.getFailcode(ORGOPERIDNULL, "operId", "operId can not null");
		}
		
		organization.setCreateDate(new Date());
		
		Integer id = create(organization);
		
	    return Cause.getSuccess(id);
	}
	
	public boolean selIsExist(Integer id){
		
		return (null != searchByid(id, tablename))?true:false;
	}
	
	
	public String sel(Integer id){
		
		Organization organization = searchByid(id, tablename);
		
		if(null == organization){
			
			return Cause.getFailcode(ORGIDNOTFIND, "id", "org id not find");
		}
		
		List<Object> list = new ArrayList<Object>(1);
		
		Map<String, Object> map = Obj2Map.toMap(organization, Organization.class);
		
		list.add(map);
		
		return Cause.getData(list);
	}
	
	public String upd(Integer id, String jsonString){
		
		Organization organization = null;
		Organization organizationfind = null;
		
		organization= (Organization)Json2Obj.getObj(jsonString, Organization.class);
		
		organizationfind = searchByid(id, tablename);
		
		if(null == organizationfind){
			return Cause.getFailcode(ORGIDNOTFIND, "id", "org id not find");
		}
		
		Json2Obj.updateObject(organization, organizationfind);
		
		update(organizationfind);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer id){
		
		Organization organization = null;

		organization = searchByid(id, tablename);
		
		if(null == organization){
			return Cause.getFailcode(ORGIDNOTFIND, "id", "org id not find");
		}
		
		delete(Organization.class, id);
		
		return Cause.getSuccess(id);
	}
}
