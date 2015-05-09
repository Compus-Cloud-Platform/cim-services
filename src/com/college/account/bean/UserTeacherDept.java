package com.college.account.bean;

import java.util.Date;

public class UserTeacherDept {
	
	public UserTeacherDept(){}
	
	public UserTeacherDept(Integer loginId, Integer deptOrgId, Date createTime,
			Integer operId) {
		super();
		this.loginId = loginId;
		this.deptOrgId = deptOrgId;
		this.createTime = createTime;
		this.operId = operId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getDeptOrgId() {
		return deptOrgId;
	}
	public void setDeptOrgId(Integer deptOrgId) {
		this.deptOrgId = deptOrgId;
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
	@Override
	public String toString() {
		return "UserTeacherDept [id=" + id + ", loginId=" + loginId
				+ ", deptOrgId=" + deptOrgId + ", createTime=" + createTime
				+ ", operId=" + operId + "]";
	}
	private Integer id;
	private Integer loginId;
	private Integer deptOrgId;
	private Date createTime;
	private Integer operId;
	

}
