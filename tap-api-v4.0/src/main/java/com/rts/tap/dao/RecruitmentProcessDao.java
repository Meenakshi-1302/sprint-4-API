package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.Candidate;
import com.rts.tap.model.RecruitmentProcess;

public interface RecruitmentProcessDao {
	public void setRecruitmentLevel(RecruitmentProcess recruitmentProcess);

	public void updateRecruitmentProcess(RecruitmentProcess recruitmentProcess);

	public RecruitmentProcess findById(Long recruitmentProcessId);
	
	public List<RecruitmentProcess> findRecruitmentProcessByMrfId(Long mrfId);

	public void deleteRecruitmentProcessById(Long recruitmentProcessId);

	List<Candidate> findCandidateByRpId(Long rpId);
}
