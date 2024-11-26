package com.rts.tap.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mrfCriteria_table")
public class MRFCriteria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mrfCriteriaId;

	@Column
	private String employmentMode;

	@Column
	private String educationalQualification;

	@Column
	private String yearsOfExperience;

	@Column
	private double minimumCTC;

	@Column
	private double maximumCTC;

	@Column
	private Date contractStartDate;

	@Column
	private Date closureDate;

	@Column
	private String jobLocation;

	public MRFCriteria() {
		super();
	}

	public MRFCriteria(Long mrfCriteriaId, String employmentMode, String educationalQualification,
			String yearsOfExperience, double minimumCTC, double maximumCTC, Date contractStartDate, Date closureDate,
			String jobLocation) {
		super();
		this.mrfCriteriaId = mrfCriteriaId;
		this.employmentMode = employmentMode;
		this.educationalQualification = educationalQualification;
		this.yearsOfExperience = yearsOfExperience;
		this.minimumCTC = minimumCTC;
		this.maximumCTC = maximumCTC;
		this.contractStartDate = contractStartDate;
		this.closureDate = closureDate;
		this.jobLocation = jobLocation;
	}

	public Long getMrfCriteriaId() {
		return mrfCriteriaId;
	}

	public void setMrfCriteriaId(Long mrfCriteriaId) {
		this.mrfCriteriaId = mrfCriteriaId;
	}

	public String getEmploymentMode() {
		return employmentMode;
	}

	public void setEmploymentMode(String employmentMode) {
		this.employmentMode = employmentMode;
	}

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public double getMinimumCTC() {
		return minimumCTC;
	}

	public void setMinimumCTC(double minimumCTC) {
		this.minimumCTC = minimumCTC;
	}

	public double getMaximumCTC() {
		return maximumCTC;
	}

	public void setMaximumCTC(double maximumCTC) {
		this.maximumCTC = maximumCTC;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(Date closureDate) {
		this.closureDate = closureDate;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	@Override
	public String toString() {
		return "MRFCriteria [mrfCriteriaId=" + mrfCriteriaId + ", employmentMode=" + employmentMode
				+ ", educationalQualification=" + educationalQualification + ", yearsOfExperience=" + yearsOfExperience
				+ ", minimumCTC=" + minimumCTC + ", maximumCTC=" + maximumCTC + ", contractStartDate="
				+ contractStartDate + ", closureDate=" + closureDate + ", jobLocation=" + jobLocation + "]";
	}

}
