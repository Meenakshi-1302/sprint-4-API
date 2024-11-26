package com.rts.tap.model;

import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.PrePersist;

public class CandidateCsvRepresentation {

	@CsvBindByName(column = "firstname")
	private String firstName;

	@CsvBindByName(column = "lastname") // Corrected to "lastname"
	private String lastName;

	@CsvBindByName(column = "mobileNumber") // Corrected to "mobileNumber"
	private String mobileNumber;

	@CsvBindByName(column = "email") // Corrected to "email"
	private String email;

	@CsvBindByName(column = "experience") // Corrected to "experience"
	private int experience;

	@CsvBindByName(column = "resume") // Corrected to "resume"
	private String resume;

	@CsvBindByName(column = "source") // Corrected to "source"
	private String source;

	@CsvBindByName(column = "sourceId") // Corrected to "sourceId"
	private long sourceId;

	@CsvBindByName(column = "skill") // Corrected to "skill"
	private String skill;

	@CsvBindByName(column = "location") // Corrected to "location"
	private String location;

	@CsvBindByName(column = "panNumber") // Corrected to "panNumber"
	private String panNumber;

	@CsvBindByName(column = "status") // Corrected to "status"
	private String status;

	@CsvBindByName(column = "assignedAt") // Corrected to "assignedAt"
	private LocalDateTime assignedAt;

	@PrePersist
	protected void onCreate() {
		assignedAt = LocalDateTime.now();
	}

	public CandidateCsvRepresentation() {
		super();
	}

	// Custom builder method
	public static CandidateCsvRepresentationBuilder builder() {
		return new CandidateCsvRepresentationBuilder();
	}

	public static class CandidateCsvRepresentationBuilder {
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
		private LocalDateTime assignedAt;

		public CandidateCsvRepresentationBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public CandidateCsvRepresentationBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public CandidateCsvRepresentationBuilder mobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}

		public CandidateCsvRepresentationBuilder email(String email) {
			this.email = email;
			return this;
		}

		public CandidateCsvRepresentationBuilder experience(int experience) {
			this.experience = experience;
			return this;
		}

		public CandidateCsvRepresentationBuilder resume(String resume) {
			this.resume = resume;
			return this;
		}

		public CandidateCsvRepresentationBuilder source(String source) {
			this.source = source;
			return this;
		}

		public CandidateCsvRepresentationBuilder sourceId(long sourceId) {
			this.sourceId = sourceId;
			return this;
		}

		public CandidateCsvRepresentationBuilder skill(String skill) {
			this.skill = skill;
			return this;
		}

		public CandidateCsvRepresentationBuilder location(String location) {
			this.location = location;
			return this;
		}

		public CandidateCsvRepresentationBuilder panNumber(String panNumber) {
			this.panNumber = panNumber;
			return this;
		}

		public CandidateCsvRepresentationBuilder status(String status) {
			this.status = status;
			return this;
		}

		public CandidateCsvRepresentation build() {
			CandidateCsvRepresentation candidateCsv = new CandidateCsvRepresentation();
			candidateCsv.setFirstName(this.firstName);
			candidateCsv.setLastName(this.lastName);
			candidateCsv.setMobileNumber(this.mobileNumber);
			candidateCsv.setEmail(this.email);
			candidateCsv.setExperience(this.experience);
			candidateCsv.setResume(this.resume);
			candidateCsv.setSource(this.source);
			candidateCsv.setSourceId(this.sourceId);
			candidateCsv.setSkill(this.skill);
			candidateCsv.setLocation(this.location);
			candidateCsv.setPanNumber(this.panNumber);
			candidateCsv.setStatus(this.status);
			// assignedAt is set by PrePersist method, if applicable
			return candidateCsv;
		}
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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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

	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}

	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}

}