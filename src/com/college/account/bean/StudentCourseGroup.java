package com.college.account.bean;

import java.util.Date;

public class StudentCourseGroup {
	
	private Integer id;
	private Users user;
	private TeacherCourseGroup teacherCourseGroup;
	private Date createTime;
	private Integer operId;
	
	public StudentCourseGroup(Users users,
			TeacherCourseGroup teacherCourseGroup, Date createTime,
			Integer operId) {
		super();
		this.user = users;
		this.teacherCourseGroup = teacherCourseGroup;
		this.createTime = createTime;
		this.operId = operId;
	}

	public StudentCourseGroup(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return user;
	}

	public void setUsers(Users users) {
		this.user = users;
	}

	public TeacherCourseGroup getTeacherCourseGroup() {
		return teacherCourseGroup;
	}

	public void setTeacherCourseGroup(TeacherCourseGroup teacherCourseGroup) {
		this.teacherCourseGroup = teacherCourseGroup;
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
