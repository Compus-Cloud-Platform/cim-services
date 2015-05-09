package com.college.account.bean;

import java.util.Date;

public class TeacherCourseGroup {
	
	private Integer id;
	private String name;
	private Integer teacherCourseId;
	private Date createTime;
	private Integer operId;
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
	public Integer getTeacherCourseId() {
		return teacherCourseId;
	}
	public void setTeacherCourseId(Integer teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
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
		return "TeacherCourseGroup [id=" + id + ", name=" + name
				+ ", teacherCourseId=" + teacherCourseId + ", createTime="
				+ createTime + ", operId=" + operId + "]";
	}
	
	public TeacherCourseGroup(){}
	
	public TeacherCourseGroup(String name, Integer teacherCourseId,
			Date createTime, Integer operId) {
		super();
		this.name = name;
		this.teacherCourseId = teacherCourseId;
		this.createTime = createTime;
		this.operId = operId;
	}
	
	
	
}
