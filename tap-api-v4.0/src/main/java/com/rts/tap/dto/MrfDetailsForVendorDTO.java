package com.rts.tap.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFAgreement;
import com.rts.tap.model.MRFCriteria;
import com.rts.tap.model.MRFStatus;
import com.rts.tap.model.Requirement;

public class MrfDetailsForVendorDTO {
	private MRF mrf;
	private Long mrfId;
	private String mrfDepartmentName;
	private String mrfRequiredTechnology;
	private String probableDesignation;
	private int requiredResourceCount;
	private Date createdAt;
	private Date updateAt;
	private MRFCriteria mrfCriteria;
	private String requiredSkills;
	private int jobDescriptionId;
	private Employee recruitingManager;

	public MrfDetailsForVendorDTO() {
		super();
	}

	public MRF getMrf() {
		return mrf;
	}

	public void setMrf(MRF mrf) {
		this.mrf = mrf;
	}

	public Long getMrfId() {
		return mrfId;
	}

	public void setMrfId(Long mrfId) {
		this.mrfId = mrfId;
	}

	public String getMrfDepartmentName() {
		return mrfDepartmentName;
	}

	public void setMrfDepartmentName(String mrfDepartmentName) {
		this.mrfDepartmentName = mrfDepartmentName;
	}

	public String getMrfRequiredTechnology() {
		return mrfRequiredTechnology;
	}

	public void setMrfRequiredTechnology(String mrfRequiredTechnology) {
		this.mrfRequiredTechnology = mrfRequiredTechnology;
	}

	public String getProbableDesignation() {
		return probableDesignation;
	}

	public void setProbableDesignation(String probableDesignation) {
		this.probableDesignation = probableDesignation;
	}

	public int getRequiredResourceCount() {
		return requiredResourceCount;
	}

	public void setRequiredResourceCount(int requiredResourceCount) {
		this.requiredResourceCount = requiredResourceCount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public MRFCriteria getMrfCriteria() {
		return mrfCriteria;
	}

	public void setMrfCriteria(MRFCriteria mrfCriteria) {
		this.mrfCriteria = mrfCriteria;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public int getJobDescriptionId() {
		return jobDescriptionId;
	}

	public void setJobDescriptionId(int jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
	}

	public Employee getRecruitingManager() {
		return recruitingManager;
	}

	public void setRecruitingManager(Employee recruitingManager) {
		this.recruitingManager = recruitingManager;
	}

}
