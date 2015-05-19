package com.college.account.bean;

import java.util.Date;

public class Major {

	private Integer id;
	private String name;
	private String code;
	private Integer deptId;
	private String description;
	private Date createTime;
	private Integer operId;
	
	public Major(){}
	
	public Major(String name, String code, Integer deptId, String description,
			Date createTime, Integer operId) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
		this.createTime = createTime;
		this.operId = operId;
		this.deptId = deptId;
	}
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", code=" + code
				+ ", description=" + description + ", createTime=" + createTime
				+ ", deptId=" + deptId+ ", operId=" + operId + "]";
	}
	
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
