package com.rts.tap.dto;

import java.util.List;

import com.rts.tap.model.RecruitmentProcess;

public class RecruitmentProcessDto {
	List<RecruitmentProcess> recruitmentProcesses;
	Long recruiterManagerId;

	public RecruitmentProcessDto() {
		super();
	}

	public List<RecruitmentProcess> getRecruitmentProcesses() {
		return recruitmentProcesses;
	}

	public void setRecruitmentProcesses(List<RecruitmentProcess> recruitmentProcesses) {
		this.recruitmentProcesses = recruitmentProcesses;
	}

	public Long getRecruiterManagerId() {
		return recruiterManagerId;
	}

	public void setRecruiterManagerId(Long recruiterManagerId) {
		this.recruiterManagerId = recruiterManagerId;
	}
}
