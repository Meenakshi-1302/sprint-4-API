package com.rts.tap.service;

import java.util.List;

import com.rts.tap.dto.RecruitmentProcessDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.RecruitmentProcess;

public interface RecruitmentProcessService {
	public void setRecruitmentProcess(RecruitmentProcessDto recruitmentProcessDto);

	public void updateRecruitmentProcessLevel(RecruitmentProcess recruitmentProcess);

	public void deleteRecruitmentProcessLevel(Long recruitmentProcessId);

	public List<RecruitmentProcess> getRecruitmentProcessLevels(Long mrfId);

	public void insertRecruitmentProcessLevel(RecruitmentProcess recruitmentProcess);

	List<Candidate> getCandidateByRpId(Long rpId);
}
