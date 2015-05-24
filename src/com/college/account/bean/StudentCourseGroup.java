package com.college.account.bean;

import java.util.Date;

public class StudentCourseGroup {
	
	private Integer id;
	private Users users;
	private TeacherCourseGroup teacherCourseGroup;
	private Date createTime;
	private Integer operId;
	
	public StudentCourseGroup(Users users,
			TeacherCourseGroup teacherCourseGroup, Date createTime,
			Integer operId) {
		super();
		this.users = users;
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
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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
