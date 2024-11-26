package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.Interview;

public interface InterviewDao {

	List<Interview> getCandidatesByRecruitmentProcessId(Long recruitmentProcessId);

	List<Interview> getCandidatesRecruitmentProcessByCandidateIdAndMrfId(Long candidateId, Long mrfId);

	List<Interview> getAllInterviews();
}


	
	
 
