package com.rts.tap.dto;

import java.sql.Date;
import java.time.LocalTime;

public class ScoreDto {

	private String assessmentName;

	private String assessmentLink;

	private String assessmentType;

	private Date assessmentStartDate;

	private Date assessmentEndDate;

	private LocalTime assessmentStartTime;

	private LocalTime assessmentEndTime;

	private Double Score;

	private String Remarks;

	private String Status;

	private String passkey;

	public ScoreDto() {
		super();
	}

	public ScoreDto(String assessmentName, String assessmentLink, String assessmentType, Date assessmentStartDate,
			Date assessmentEndDate, LocalTime assessmentStartTime, LocalTime assessmentEndTime, Double score,
			String remarks, String status, String passkey) {
		super();
		this.assessmentName = assessmentName;
		this.assessmentLink = assessmentLink;
		this.assessmentType = assessmentType;
		this.assessmentStartDate = assessmentStartDate;
		this.assessmentEndDate = assessmentEndDate;
		this.assessmentStartTime = assessmentStartTime;
		this.assessmentEndTime = assessmentEndTime;
		Score = score;
		Remarks = remarks;
		Status = status;
		this.passkey = passkey;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	public String getAssessmentLink() {
		return assessmentLink;
	}

	public void setAssessmentLink(String assessmentLink) {
		this.assessmentLink = assessmentLink;
	}

	public String getAssessmentType() {
		return assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public Date getAssessmentStartDate() {
		return assessmentStartDate;
	}

	public void setAssessmentStartDate(Date assessmentStartDate) {
		this.assessmentStartDate = assessmentStartDate;
	}

	public Date getAssessmentEndDate() {
		return assessmentEndDate;
	}

	public void setAssessmentEndDate(Date assessmentEndDate) {
		this.assessmentEndDate = assessmentEndDate;
	}

	public LocalTime getAssessmentStartTime() {
		return assessmentStartTime;
	}

	public void setAssessmentStartTime(LocalTime assessmentStartTime) {
		this.assessmentStartTime = assessmentStartTime;
	}

	public LocalTime getAssessmentEndTime() {
		return assessmentEndTime;
	}

	public void setAssessmentEndTime(LocalTime assessmentEndTime) {
		this.assessmentEndTime = assessmentEndTime;
	}

	public Double getScore() {
		return Score;
	}

	public void setScore(Double score) {
		Score = score;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPasskey() {
		return passkey;
	}

	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

}
