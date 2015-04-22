package com.college.account.bean;

import java.util.Date;

public class Users {
	
	private int id;
	private String loginId;
	private String name;
	private String loginPassword;
	private String email;
	private int PositionId;
	private Date employDate;
	private Date createTime;
	private int orgId;
	private int operId;
	
	
	public Users(){}
	
	public Users(String loginId, String name, String loginPassword,
			String email, int positionId, Date employDate, Date createTime,
			int orgId, int operId) {
		super();
		this.loginId = loginId;
		this.name = name;
		this.loginPassword = loginPassword;
		this.email = email;
		PositionId = positionId;
		this.employDate = employDate;
		this.createTime = createTime;
		this.orgId = orgId;
		this.operId = operId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPositionId() {
		return PositionId;
	}
	public void setPositionId(int positionId) {
		PositionId = positionId;
	}
	public Date getEmployDate() {
		return employDate;
	}
	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getOperId() {
		return operId;
	}
	public void setOperId(int operId) {
		this.operId = operId;
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", loginId=" + loginId + ", name=" + name
				+ ", loginPassword=" + loginPassword + ", email=" + email
				+ ", PositionId=" + PositionId + ", employDate=" + employDate
				+ ", createTime=" + createTime + ", orgId=" + orgId
				+ ", operId=" + operId + "]";
	}
}
