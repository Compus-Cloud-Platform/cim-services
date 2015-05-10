package com.college.account.service;


import java.util.Date;
import java.util.Map;

import com.college.account.bean.Organization;

import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Json2Obj;

/**
 * @author Dennis Dai
 */
public class DaoOrganizationService extends  DaoService<Organization>
{
	private int ORGIDNOTFIND = 3001;
	private int ORGNAMENULL = 3002;
	private int ORGOPERIDNULL = 3003;
	private String tablename = "Organization";
	
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
	
	public String search(Integer id){
		
		Organization organization = searchByid(id, tablename);
		
		if(null == organization){
			
			return Cause.getFailcode(ORGIDNOTFIND, "id", "org id not find");
		}
		
		return Cause.getSuccess(id);
	}
}
