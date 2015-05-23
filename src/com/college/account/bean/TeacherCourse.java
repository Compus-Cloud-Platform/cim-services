package com.college.account.bean;

import java.util.Date;

public class TeacherCourse {
	private Integer id;
	private Integer loginId;
	private Integer courseId;
	private String grade;
	private String term;
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
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
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
		return "TeacherCourse [id=" + id + ", loginId=" + loginId
				+ ", courseId=" + courseId + ", grade=" + grade + ", term="
				+ term + ", createTime=" + createTime + ", operId=" + operId
				+ "]";
	}
	
	public TeacherCourse(){}
	
	public TeacherCourse(Integer loginId, Integer courseId, String grade,
			String term, Date createTime, Integer operId) {
		super();
		this.loginId = loginId;
		this.courseId = courseId;
		this.grade = grade;
		this.term = term;
		this.createTime = createTime;
		this.operId = operId;
	}

	
}
