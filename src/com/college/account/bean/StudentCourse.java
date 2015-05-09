package com.college.account.bean;

import java.util.Date;

public class StudentCourse {
	
	private Integer id;
	private Integer loginId;
	private Integer teacherCourseId;
	private String score;
	private Integer setnum;
	private Integer teacherCourseGroupId;
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
	public Integer getTeacherCourseId() {
		return teacherCourseId;
	}
	public void setTeacherCourseId(Integer teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getSetnum() {
		return setnum;
	}
	public void setSetnum(Integer setnum) {
		this.setnum = setnum;
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
	
	
	@Override
	public String toString() {
		return "StudentCourse [id=" + id + ", loginId=" + loginId
				+ ", teacherCourseId=" + teacherCourseId + ", score=" + score
				+ ", setnum=" + setnum + ", teacherCourseGroupId="
				+ teacherCourseGroupId + ", createTime=" + createTime
				+ ", operId=" + operId + "]";
	}
	
	public StudentCourse(){}
	
	public StudentCourse(Integer loginId, Integer teacherCourseId,
			String score, Integer setnum, Integer teacherCourseGroupId,
			Date createTime, Integer operId) {
		super();
		this.loginId = loginId;
		this.teacherCourseId = teacherCourseId;
		this.score = score;
		this.setnum = setnum;
		this.teacherCourseGroupId = teacherCourseGroupId;
		this.createTime = createTime;
		this.operId = operId;
	}
}
