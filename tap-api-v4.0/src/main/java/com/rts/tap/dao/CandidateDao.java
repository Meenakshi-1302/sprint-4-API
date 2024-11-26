package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.dto.RecruitProcessDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;
import com.rts.tap.model.RecruitmentProcess;

import jakarta.mail.MessagingException;

public interface CandidateDao<T> {

	public Candidate findByEmail(String email);
	
	void save(Candidate candidate);

	void update(Candidate candidate);

	Candidate findById(Long id);

	List<Candidate> findAll();

	void delete(Long id);

	List<Candidate> findCandidateByMrfId(long mrfId);

	List<MrfJd> appliedJobs(Long candidateId);

	Long appliedJobsCount(Long candidateId);

	Long assessmentCountByCandidateId(Long candidateId);

	Long interviewCountByCandidateId(Long candidateId);

	List<RecruitProcessDto> levelsOfInterview(Long mrfJdId);

	Object getRecruitmentProcessById(Long recruitmentProcessId, Long candidateId);
	
	String applyJob(Candidate candidate) throws MessagingException;
	
	//Team-D Offer acceptance
	public String updateOffer(Long offerId, Long candidateId);
	 
}
