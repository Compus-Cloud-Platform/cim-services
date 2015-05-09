package com.college.account.service;


import com.college.account.bean.Organization;
import com.college.util.Cause;

/**
 * @author Dennis Dai
 */
public class DaoOrganizationService extends  DaoService<Organization>
{
	private int ORGIDNOTFIND = 3001;
	private String tablename = "Organization";
	
	public String search(Integer id){
		
		Organization organization = searchByid(id, tablename);
		
		if(null == organization){
			
			return Cause.getFailcode(ORGIDNOTFIND, "id", "org id not find");
		}
		
		return Cause.getSuccess(id);
	}
}
