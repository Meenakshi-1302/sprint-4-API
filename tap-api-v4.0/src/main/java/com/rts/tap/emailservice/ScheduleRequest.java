package com.rts.tap.emailservice;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public class ScheduleRequest {
	private String assessmentName;
	private String assessmentLink;
	private String assessmentType;
	private Date assessmentStartDate;

	private Date assessmentEndDate;

	private LocalTime assessmentStartTime;

	private LocalTime assessmentEndTime;
	private List<String> candidateEmails;

	public ScheduleRequest() {
		super();
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

	public List<String> getCandidateEmails() {
		return candidateEmails;
	}

	public void setCandidateEmails(List<String> candidateEmails) {
		this.candidateEmails = candidateEmails;
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

}
