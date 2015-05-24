package com.college.account.bean;

import java.util.Date;

public class StudentCourseGroup {
	
	private Integer id;
	private Integer studentId;
	private Integer teacherCourseGroupId;
	private Date createTime;
	private Integer operId;
	
	public StudentCourseGroup(){}
	
	public StudentCourseGroup(Integer studentId, Integer teacherCourseGroupId,
			Date createTime, Integer operId) {
		super();
		this.studentId = studentId;
		this.teacherCourseGroupId = teacherCourseGroupId;
		this.createTime = createTime;
		this.operId = operId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getTeacherCourseGroupId() {
		return teacherCourseGroupId;
	}
	public void setTeacherCourseGroupId(Integer teacherCourseGroupId) {
		this.teacherCourseGroupId = teacherCourseGroupId;
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
