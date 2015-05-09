package com.college.account.bean;

import java.util.Date;

public class Permission {

	private Integer id;
	private String name;
	private String description;
	private Date createTime;
	private Integer operId;
	
	
	public Permission(){}
	
	public Permission(String name, String description,
			Date createTime, Integer operId) {
		super();
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.operId = operId;
	}
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name
				+ ", description=" + description + ", createTime=" + createTime
				+ ", operId=" + operId + "]";
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
