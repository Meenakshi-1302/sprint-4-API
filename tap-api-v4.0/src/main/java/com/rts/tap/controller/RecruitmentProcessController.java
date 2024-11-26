package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.RecruitmentProcessDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.RecruitmentProcess;
import com.rts.tap.service.RecruitmentProcessService;

@RestController
@RequestMapping(path=APIConstants.BASE_URL)
@CrossOrigin(origins=APIConstants.FRONT_END_URL)
public class RecruitmentProcessController {
	
	RecruitmentProcessService recruitmentProcessService;

	public RecruitmentProcessController(RecruitmentProcessService recruitmentProcessService) {
		super();
		this.recruitmentProcessService = recruitmentProcessService;
	}
	
	@PostMapping(APIConstants.ADD_RECRUITMENT_PROCESS)
	public ResponseEntity<String> addRecruitmentProcess(@RequestBody RecruitmentProcessDto recruitmentProcessDto) {
		recruitmentProcessService.setRecruitmentProcess(recruitmentProcessDto);
		return ResponseEntity.ok(MessageConstants.RECRUITMENT_PROCESS_ADDED);
	}
	
	@PutMapping(APIConstants.UPDATE_RECRUITMENT_PROCESS_LEVEL)
	public ResponseEntity<String> updateRecruitmentProcessLevel(@RequestBody RecruitmentProcess recruitmentProcess) {
		recruitmentProcessService.updateRecruitmentProcessLevel(recruitmentProcess);
		return ResponseEntity.ok(MessageConstants.RECRUITMENT_PROCESS_LEVEL_UPDATED);
	}
	
	@DeleteMapping(APIConstants.DELETE_RECRUITMENT_PROCESS_LEVEL)
	public ResponseEntity<String> deleteRecruitmentProcessLevels(@PathVariable Long recruitmentProcessId) {
		recruitmentProcessService.deleteRecruitmentProcessLevel(recruitmentProcessId);
		return ResponseEntity.ok(MessageConstants.RECRUITMENT_PROCESS_LEVEL_DELETED);
	}
	
	@PostMapping(APIConstants.INSERT_RECRUITMENT_PROCESS_LEVEL)
	public ResponseEntity<String> insertRecruitmentProcessLevel(@RequestBody RecruitmentProcess recruitmentProcess) {
		recruitmentProcessService.insertRecruitmentProcessLevel(recruitmentProcess);
		return ResponseEntity.ok(MessageConstants.RECRUITMENT_PROCESS_LEVEL_INSERTED);
	}
	
	@GetMapping(APIConstants.GET_RECRUITMENT_PROCESS_LEVELS)
	public ResponseEntity<List<RecruitmentProcess>> getRecruitmentProcessLevel(@PathVariable Long mrfId) {
		List<RecruitmentProcess> recruitmentProcesses = recruitmentProcessService.getRecruitmentProcessLevels(mrfId);
		return ResponseEntity.ok(recruitmentProcesses);
	}
	
	@GetMapping(APIConstants.GET_CANDIDATE_BY_RPID)
		public ResponseEntity<List<Candidate>> getCandidateByRpId(@PathVariable Long rpId) {
			List<Candidate> candidates = recruitmentProcessService.getCandidateByRpId(rpId);
			return ResponseEntity.ok(candidates);
	}
}
