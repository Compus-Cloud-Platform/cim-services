package com.college.account.bean;

import java.util.Date;

public class UserAdminOrg {
	private Integer id;
	private Integer loginId;
	private Integer orgId;
	private Date createTime;
	private Integer operId;
	
	public UserAdminOrg(){}
	
	public UserAdminOrg(Integer loginId, Integer orgId, Date createTime,
			Integer operId) {
		super();
		this.loginId = loginId;
		this.orgId = orgId;
		this.createTime = createTime;
		this.operId = operId;
	}
	@Override
	public String toString() {
		return "UserAdminOrg [id=" + id + ", loginId=" + loginId + ", orgId="
				+ orgId + ", createTime=" + createTime + ", operId=" + operId
				+ "]";
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
