package com.college.account.bean;

import java.util.Date;

public class UserStudentMajor {
	private Integer id;
	private Integer loginId;
	private Integer majorDeptId;
	private Date createTime;
	private Integer operId;
	
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
	public Integer getMajorDeptId() {
		return majorDeptId;
	}
	public void setMajorDeptId(Integer majorDeptId) {
		this.majorDeptId = majorDeptId;
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
		return "UserStudentMajor [id=" + id + ", loginId=" + loginId
				+ ", majorDeptId=" + majorDeptId + ", createTime=" + createTime
				+ ", operId=" + operId + "]";
	}

	public UserStudentMajor(){}
	
	public UserStudentMajor(Integer loginId, Integer majorDeptId,
			Date createTime, Integer operId) {
		super();
		this.loginId = loginId;
		this.majorDeptId = majorDeptId;
		this.createTime = createTime;
		this.operId = operId;
	}

}
