package com.college.account.bean;

import java.util.Date;

public class MajorDept {
	private Integer id;
	private Integer majorId;
	private Integer deptOrgId;
	private Date createTime;
	private Integer operId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
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
		return "MajorDept [id=" + id + ", majorId=" + majorId + ", deptOrgId="
				+ deptOrgId + ", createTime=" + createTime + ", operId="
				+ operId + "]";
	}
	public MajorDept(){}
	public MajorDept(Integer majorId, Integer deptOrgId, Date createTime,
			Integer operId) {
		super();
		this.majorId = majorId;
		this.deptOrgId = deptOrgId;
		this.createTime = createTime;
		this.operId = operId;
	}
	
}
