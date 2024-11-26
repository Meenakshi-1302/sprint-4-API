package com.rts.tap.dto;

import com.rts.tap.model.MRF;

/**
 * Author : Shakeel Ahamed
 **/

public class RecruitProcessDto {

	private Long recruitmentProcessId;

	private int level;

	private String recruitmentProcessType;

	private String recruitmentProcessName;

	private String report;

	private String completedStatus;

	private Long mrfId;

	public RecruitProcessDto() {
		super();
	}

	public RecruitProcessDto(Long recruitmentProcessId, int level, String recruitmentProcessType,
			String recruitmentProcessName, String report, String completedStatus, Long mrfId) {
		super();
		this.recruitmentProcessId = recruitmentProcessId;
		this.level = level;
		this.recruitmentProcessType = recruitmentProcessType;
		this.recruitmentProcessName = recruitmentProcessName;
		this.report = report;
		this.completedStatus = completedStatus;
		this.mrfId = mrfId;
	}

	public Long getRecruitmentProcessId() {
		return recruitmentProcessId;
	}

	public void setRecruitmentProcessId(Long recruitmentProcessId) {
		this.recruitmentProcessId = recruitmentProcessId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getRecruitmentProcessType() {
		return recruitmentProcessType;
	}

	public void setRecruitmentProcessType(String recruitmentProcessType) {
		this.recruitmentProcessType = recruitmentProcessType;
	}

	public String getRecruitmentProcessName() {
		return recruitmentProcessName;
	}

	public void setRecruitmentProcessName(String recruitmentProcessName) {
		this.recruitmentProcessName = recruitmentProcessName;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(String completedStatus) {
		this.completedStatus = completedStatus;
	}

	public Long getMrfId() {
		return mrfId;
	}

	public void setMrfId(Long mrfId) {
		this.mrfId = mrfId;
	}

}
