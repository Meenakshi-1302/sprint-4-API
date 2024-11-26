package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.InterviewDao;
import com.rts.tap.model.Interview;
import com.rts.tap.service.InterviewService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {
	InterviewDao interviewDao;

	public InterviewServiceImpl(InterviewDao interviewDao) {
		super();
		this.interviewDao = interviewDao;
	}

	@Override
	public List<Interview> getCandidatesByRecruitmentProcessId(Long recruitmentProcessId) {
		return interviewDao.getCandidatesByRecruitmentProcessId(recruitmentProcessId);
	}

	@Override
	public List<Interview> getCandidatesRecruitmentProcessByCandidateIdAndMrfId(Long candidateId, Long mrfId) {
		return interviewDao.getCandidatesRecruitmentProcessByCandidateIdAndMrfId(candidateId, mrfId);
	}

	@Override
	public List<Interview> findAllInterviews() {
		return interviewDao.getAllInterviews();
	}
}


 