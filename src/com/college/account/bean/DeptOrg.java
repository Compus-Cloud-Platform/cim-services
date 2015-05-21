package com.college.account.bean;

import java.util.Date;

public class DeptOrg {
	
	private Integer id;
	private Department department;
	private Organization organization;
	private Date createTime;
	private Integer operId;
	

	public DeptOrg(){}
	
	public DeptOrg(Department department, Organization organization,
			Date createTime, Integer operId) {
		super();
		this.department = department;
		this.organization = organization;
		this.createTime = createTime;
		this.operId = operId;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Integer getOperId() {
		return operId;
	}


	public void setOperId(Integer operId) {
		this.operId = operId;
	}


	
	


}
