package com.rts.tap.dto;

import org.springframework.web.multipart.MultipartFile;

public class RecruitingManagerAddCandidateDto {
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private int experience;
	private String resume;
	private String source;
	private long sourceId;
	private String skill;
	private String location;
	private String panNumber;
	private String status;
	private Boolean isPasswordChanged;
	private MultipartFile candidateResume;

	public String getResume() {
		return resume;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getSourceId() {
		return sourceId;
	}

	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsPasswordChanged() {
		return isPasswordChanged;
	}

	public void setIsPasswordChanged(Boolean isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}

	public MultipartFile getCandidateResume() {
		return candidateResume;
	}

	public void setCandidateResume(MultipartFile candidateResume) {
		this.candidateResume = candidateResume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
