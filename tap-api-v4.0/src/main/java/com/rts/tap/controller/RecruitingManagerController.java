package com.rts.tap.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.MRFRecruiterDto;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.dto.RecruitingManagerAddCandidateDto;
import com.rts.tap.emailservice.CandidateMailService;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRFRecruiters;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFStatus;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.service.RecruitingManagerService;

import jakarta.mail.MessagingException;

/**
 * author: Vashanth TS , Jeevarajan R, Prince S
 * version: v3.0 
 * updated at: 20-11-2024
 **/

@RestController
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(APIConstants.RECRUITING_MANAGER_URL)
public class RecruitingManagerController {

	RecruitingManagerService recruitingManagerService;
	CandidateMailService candidateMailService;

	public RecruitingManagerController(RecruitingManagerService recruitingManagerService, CandidateMailService candidateMailService) {
		super();
		this.recruitingManagerService = recruitingManagerService;
		this.candidateMailService = candidateMailService;
	}

	/**
	 * this api will accept MrfVendor dto and perform Assign operation and return
	 * message for output success or failed
	 * 
	 * @param(vendor id) - long
	 * @param(Mrf's Id) - long
	 * @param(Recruiting Manager's Id) - long
	 * @return String - successfully saved or not
	 **/
	@PostMapping(APIConstants.RECRUITING_MANAGER_ASSIGN_MRF_VENDOR)
	public ResponseEntity<String> doAssignMrfToVendor(@RequestBody MRFVendorDto mrfVendorDto) {
		return new ResponseEntity<>(recruitingManagerService.mrfAssignToVendor(mrfVendorDto), HttpStatus.OK);
	}

	/**
	 * this api will get all the mrfs that are assigned for specific Recruiting
	 * manager
	 * 
	 * @param(recruiting manager's Id) - long
	 * @return List of MrfRecruitingManager object
	 **/
	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_ALL_MRF)
	public List<MRFRecruitingManager> doGetAllMrfsAssignedForRM(@PathVariable long id) {
		return recruitingManagerService.getAllMrfsAssignedForRM(id);
	}

	/**
	 * this api will get all the mrfs that are assigned for specific vendors manager
	 * 
	 * @return List of MrfVendor object
	 **/
	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_ALL_MRFVENDOR)
	public ResponseEntity<List<MRFVendorDto>> doGetAllRecruitersASsigned() {
		try {
			return new ResponseEntity<>(recruitingManagerService.getAllMrfVendorsRecords(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(APIConstants.RECRUITING_MANAGER_ASSIGN_MRF_RECRUITER)
	public ResponseEntity<String> doAssignMrfToRecruiter(@RequestBody MRFRecruiterDto mrfRecruiterDto) {
		return new ResponseEntity<>(recruitingManagerService.mrfAssignToRecruiter(mrfRecruiterDto), HttpStatus.OK);
	}

	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_ALL_ASSIGNED_RECRUITERS)
	public ResponseEntity<List<MRFRecruiters>> doGetAllRecruiters() {
		try {
			return new ResponseEntity<>(recruitingManagerService.getAllAssignedMrfRecruiterRecords(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(APIConstants.RECRUITING_MANAGER_UPDATE_MRF_RECRUITER)
	public ResponseEntity<String> updateMrfRecruiter(@RequestBody MRFRecruiterDto mrfRecruiterDto,
			@PathVariable int id) {
		// Call the service to update the MRFRecruiter
		String response = recruitingManagerService.updateMrfRecruiter(mrfRecruiterDto, id);

		if (response.equals(MessageConstants.RECRUITER_MRF_UPDATED)) {
			return ResponseEntity.ok(response); // Successful update
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // Failure response
		}
	}

	@PostMapping(APIConstants.RECRUITING_MANAGER_REASSIGN_MRF_RECRUITER)
	public ResponseEntity<String> doReassignMrfToRecruiter(@RequestBody MRFRecruiterDto mrfRecruiterDto) {
		return new ResponseEntity<>(recruitingManagerService.reassignMrfToRecruiter(mrfRecruiterDto), HttpStatus.OK);
	}

	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_ALL_RECRUITERS)
	public ResponseEntity<List<Employee>> doGetAllRecruitersForRecruitingmanagerId(@PathVariable long id) {
		return new ResponseEntity<>(recruitingManagerService.getAllRecruitersByRecruitingManagerID(id), HttpStatus.OK);
	}

	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_ALL_RECRUITERS_BY_MRF)
	public ResponseEntity<List<MRFRecruiters>> doGetAllRecruitersByMrfRecruitingmanagerId(@PathVariable long id) {
		return new ResponseEntity<>(recruitingManagerService.getAllRecruitersByMRFRecruitingManagerId(id),
				HttpStatus.OK);
	}

	@PutMapping(APIConstants.RECRUITING_MANAGER_UPDATE_STAGE)
	public ResponseEntity<String> updateStage(@RequestBody MRFStatus mrfstatus, @PathVariable Long mrfId) {		
		String mrfStage = mrfstatus.getMrfStage();
		recruitingManagerService.updateMrfStage(mrfStage, mrfId);
		return new ResponseEntity<>("MRF stage updated successfully", HttpStatus.OK);
	}

	@GetMapping(APIConstants.RECRUITING_MANAGER_FETCH_VENDORS_BY_MRF)
	public ResponseEntity<List<MRFVendor>> doGetAllVendorsByMrfId(@PathVariable long id) {
		return new ResponseEntity<>(recruitingManagerService.getAllVendorsAssignedForMRFbyMrfId(id), HttpStatus.OK);
	}

	// AUTHOR: PRINCE AROCKIA SAMUEL S
	// This code is for adding candidate by recruiting manager
	@PostMapping(value = "/add-candidate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> addCandidate(
			@ModelAttribute RecruitingManagerAddCandidateDto recruitingManagerAddCandidateDto,
			@RequestParam("candidateResume") MultipartFile candidateResume) throws MessagingException, IOException {
		String resultMessage = recruitingManagerService.addNewCandidate(recruitingManagerAddCandidateDto,
				candidateResume);

		// Send email to candidate
		candidateMailService.sendCandidateConfirmation(recruitingManagerAddCandidateDto);

		return new ResponseEntity<>(resultMessage, HttpStatus.OK);
	}

	// This code is to get all the candidate list of a specific sourceId where the
	// source is in REFERRAL
	@GetMapping("/referral/{sourceId}")
	public List<Candidate> getCandidatesBySourceId(@PathVariable long sourceId) {
		return recruitingManagerService.getCandidatesBySourceId(sourceId);
	}

}