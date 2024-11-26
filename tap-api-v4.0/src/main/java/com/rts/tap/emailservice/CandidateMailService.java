package com.rts.tap.emailservice;

import com.rts.tap.dto.RecruitingManagerAddCandidateDto;

import jakarta.mail.MessagingException;

public interface CandidateMailService {	
	String sendCandidateConfirmation(RecruitingManagerAddCandidateDto candidateDto) throws MessagingException;
}
