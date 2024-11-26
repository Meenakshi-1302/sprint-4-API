package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Interview;
import com.rts.tap.service.InterviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class InterviewController {
	InterviewService interviewService;

	public InterviewController(InterviewService interviewService) {
		super();
		this.interviewService = interviewService;
	}

	@GetMapping(APIConstants.GET_CANDIDATES_BY_RECRUITMENT_PROCESS)
	public ResponseEntity<List<Interview>> getCandidatesByRecruitmentProcessId(
			@PathVariable Long recruitmentProcessId) {
		List<Interview> interviews = interviewService.getCandidatesByRecruitmentProcessId(recruitmentProcessId);
		return ResponseEntity.ok(interviews);
	}

	@GetMapping(APIConstants.GET_CANDIDATE_RECRUITMENT_PROCESS_BY_CANDIDATE_ID_MRF_ID)
	public ResponseEntity<List<Interview>> getCandidatesRecruitmentProcessByCandidateId(@RequestParam("candidateId") Long candidateId, @RequestParam("mrfId") Long mrfId) {
		List<Interview> interviews = interviewService.getCandidatesRecruitmentProcessByCandidateIdAndMrfId(candidateId, mrfId);
		return ResponseEntity.ok(interviews);
	}

	@GetMapping(APIConstants.GET_ALL_INTERVIEW_URL)
    public ResponseEntity<List<Interview>> getAllInterviews() {
        List<Interview> interviews = interviewService.findAllInterviews();
        return ResponseEntity.ok(interviews);
    }
}
