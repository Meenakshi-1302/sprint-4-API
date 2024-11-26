package com.rts.tap.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class InterviewDto {

	private String interviewTitle;

	private LocalDate interviewDate;

	private LocalTime interviewFromTime;

	private LocalTime interviewToTime;

	private String meetingUrl;

	private String candidateStatus;

	private String Others;

	public InterviewDto() {
		super();
	}

	public InterviewDto(String interviewTitle, LocalDate interviewDate, LocalTime interviewFromTime,
			LocalTime interviewToTime, String meetingUrl, String candidateStatus, String others) {
		super();
		this.interviewTitle = interviewTitle;
		this.interviewDate = interviewDate;
		this.interviewFromTime = interviewFromTime;
		this.interviewToTime = interviewToTime;
		this.meetingUrl = meetingUrl;
		this.candidateStatus = candidateStatus;
		Others = others;
	}

	public String getInterviewTitle() {
		return interviewTitle;
	}

	public void setInterviewTitle(String interviewTitle) {
		this.interviewTitle = interviewTitle;
	}

	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}

	public LocalTime getInterviewFromTime() {
		return interviewFromTime;
	}

	public void setInterviewFromTime(LocalTime interviewFromTime) {
		this.interviewFromTime = interviewFromTime;
	}

	public LocalTime getInterviewToTime() {
		return interviewToTime;
	}

	public void setInterviewToTime(LocalTime interviewToTime) {
		this.interviewToTime = interviewToTime;
	}

	public String getMeetingUrl() {
		return meetingUrl;
	}

	public void setMeetingUrl(String meetingUrl) {
		this.meetingUrl = meetingUrl;
	}

	public String getCandidateStatus() {
		return candidateStatus;
	}

	public void setCandidateStatus(String candidateStatus) {
		this.candidateStatus = candidateStatus;
	}

	public String getOthers() {
		return Others;
	}

	public void setOthers(String others) {
		Others = others;
	}

}
