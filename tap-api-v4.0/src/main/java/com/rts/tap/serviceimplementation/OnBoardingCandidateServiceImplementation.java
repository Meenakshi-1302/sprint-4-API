package com.rts.tap.serviceimplementation;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.OnBoardingCandidateDao;
import com.rts.tap.service.OnBoardingCandidateService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OnBoardingCandidateServiceImplementation implements OnBoardingCandidateService {

	OnBoardingCandidateDao onBoardingCandidateDao;

	public OnBoardingCandidateServiceImplementation(OnBoardingCandidateDao onBoardingCandidateDao) {
		super();
		this.onBoardingCandidateDao = onBoardingCandidateDao;
	}

	@Override
	public String doChangeCandidateStatus(long candidateId) {
		return onBoardingCandidateDao.changeCandidateStatus(candidateId);
	}

}
