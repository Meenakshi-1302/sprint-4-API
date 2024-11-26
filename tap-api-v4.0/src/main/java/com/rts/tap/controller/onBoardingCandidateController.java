package com.rts.tap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.service.OnBoardingCandidateService;

@RestController
@RequestMapping("/onBoard")
@CrossOrigin(APIConstants.FRONT_END_URL)
public class onBoardingCandidateController {
	
	OnBoardingCandidateService onBoardingCandidateService;

	public onBoardingCandidateController(OnBoardingCandidateService onBoardingCandidateService) {
		super();
		this.onBoardingCandidateService = onBoardingCandidateService;
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<String> doChangeStatusOnBoardingCandidate(@PathVariable("id") long id){
		return new ResponseEntity<>(onBoardingCandidateService.doChangeCandidateStatus(id),HttpStatus.OK);
	}

}
