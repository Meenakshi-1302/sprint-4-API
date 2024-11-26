package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.Interview;

public interface InterviewService {

	List<Interview> getCandidatesByRecruitmentProcessId(Long recruitmentProcessId);

	List<Interview> getCandidatesRecruitmentProcessByCandidateIdAndMrfId(Long candidateId, Long mrfId);

	List<Interview> findAllInterviews();
}
