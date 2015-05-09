package com.college.account.bean;

import java.util.Date;

public class DeptOrg {
	
	private Integer id;
	private Integer deptId;
	private Integer orgId;
	private Date createTime;
	private Integer operId;
	
	@Override
	public String toString() {
		return "DeptOrg [id=" + id + ", deptId=" + deptId + ", orgId=" + orgId
				+ ", createTime=" + createTime + ", operId=" + operId + "]";
	}
	public DeptOrg(){}
	
	public DeptOrg(Integer deptId, Integer orgId, Date createTime,
			Integer operId) {
		super();
		this.deptId = deptId;
		this.orgId = orgId;
		this.createTime = createTime;
		this.operId = operId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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
