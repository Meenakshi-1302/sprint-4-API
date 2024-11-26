package com.rts.tap.dto;

import java.sql.Date;

public class MRFRecruiterDto {

	private long mrfRecruitingManagerId;
	private long recruiterId;
	private String recruiterAssignedStatus;
	private java.util.Date createdAt;
	private java.util.Date updatedAt;
	private java.util.Date closedDate;
	private int assignedCount;
	private int achievedCount;
	public MRFRecruiterDto() {
		super();
	}
	public long getMrfRecruitingManagerId() {
		return mrfRecruitingManagerId;
	}
	public void setMrfRecruitingManagerId(long mrfRecruitingManagerId) {
		this.mrfRecruitingManagerId = mrfRecruitingManagerId;
	}
	public long getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getRecruiterAssignedStatus() {
		return recruiterAssignedStatus;
	}
	public void setRecruiterAssignedStatus(String recruiterAssignedStatus) {
		this.recruiterAssignedStatus = recruiterAssignedStatus;
	}
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Date date) {
		this.createdAt = date;
	}
	public java.util.Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(java.util.Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public java.util.Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(java.util.Date date) {
		this.closedDate = date;
	}
	public int getAssignedCount() {
		return assignedCount;
	}
	public void setAssignedCount(int assignedCount) {
		this.assignedCount = assignedCount;
	}
	public int getAchievedCount() {
		return achievedCount;
	}
	public void setAchievedCount(int achievedCount) {
		this.achievedCount = achievedCount;
	}	
}