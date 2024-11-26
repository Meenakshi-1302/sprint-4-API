package com.rts.tap.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.RecruitProcessDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;
import com.rts.tap.model.RecruitmentProcess;
import com.rts.tap.service.CandidateService;

import jakarta.mail.MessagingException;

@CrossOrigin(origins = APIConstants.FRONT_END_URL)
@RestController
@RequestMapping(path = APIConstants.BASE_CANDIDATE_URL)
public class CandidateController {

	private CandidateService candidateService;

	public CandidateController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}

	@PostMapping(APIConstants.SAVE_CANDIDATE_URL)
	public void createCandiadte(@RequestBody Candidate candidate) {
		candidateService.save(candidate);

	}

//    @PostMapping(APIConstants.SAVE_CANDIDATE_URL)
//    public void createCandidate(@RequestParam("firstName") String firstName,
//                                @RequestParam("lastName") String lastName,
//                                @RequestParam("mobileNumber") String mobileNumber,
//                                @RequestParam("email") String email,
//                                @RequestParam("experience") int experience,
//                                @RequestParam("resume") String resume,
//                                @RequestParam("source") String source,
//                                @RequestParam("sourceId") long sourceId,
//                                @RequestParam("skill") String skill,
//                                @RequestParam("location") String location,
//                                @RequestParam("panNumber") String panNumber,
//                                @RequestParam("status") String status,
//                                @RequestParam("candidateResume") MultipartFile candidateResume)
//    							 {
//
//        try {
//            byte[] resumeData = resume.getBytes(); // Convert the resume file to byte array
//            
//            Candidate candidate = new Candidate();
//            candidate.setFirstName(firstName);
//            candidate.setLastName(lastName);
//            candidate.setMobileNumber(mobileNumber);
//            candidate.setEmail(email);
//            candidate.setExperience(experience);
//            candidate.setResume(resume);
//            candidate.setSource(source);
//            candidate.setSourceId(sourceId);
//            candidate.setSkill(skill);
//            candidate.setLocation(location);
//            candidate.setPanNumber(panNumber);
//            candidate.setStatus(status);
//            candidate.setCandidateResume(resumeData); // Set the BLOB (resume)
//
//            candidateService.save(candidate); // Save the candidate with the resume (BLOB)
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error uploading candidate: " + e.getMessage());
//        }
//    }

	/*
	 * Commented by team- D Author - Meenakshi Priyadharshini Ananthan
	 */
//	@PostMapping("/uploadCandidates")
//    public String uploadCandidates(@RequestParam("file") MultipartFile file) {
//        try {
//            candidateService.bulkSave(file);
//            return "Candidates uploaded successfully!";
//        } catch (Exception e) {
//            return "Error uploading candidates: " + e.getMessage();
//        }
//    }

	@GetMapping(path = APIConstants.GET_ALL_CANDIDATE_URL)
	public List<Candidate> getAllCandidates() {
		return candidateService.findAll();
	}

	@GetMapping(path = APIConstants.GET_BY_ID_CANDIDATE_URL)
	public Candidate getCandidateById(@PathVariable Long id) {
		return candidateService.findById(id);
	}

	@PutMapping(path = APIConstants.UPDATE_CANDIDATE_URL)
	public void updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
		candidate.setCandidateId(id);
		candidateService.update(candidate);
	}

	@DeleteMapping(path = APIConstants.DELETE_CANDIDATE_URL)
	public void deleteCandidate(@PathVariable Long id) {
		candidateService.delete(id);
	}



	@GetMapping(path = APIConstants.LIST_APPLIED_JOBS)
	public ResponseEntity<List<MrfJd>> appliedJobsList(@RequestParam("candidateId") Long candidateId) {
		List<MrfJd> jd = candidateService.appliedJobsByCandidateId(candidateId);
		return ResponseEntity.ok(jd);
	}

	@GetMapping(path = APIConstants.COUNT_APPLIED_JOBS)
	public ResponseEntity<Long> appliedJobsCounts(@RequestParam("candidateId") Long candidateId) {
		Long count = candidateService.appliedJobsCount(candidateId);
		return ResponseEntity.ok(count);
	}

	@GetMapping(path = APIConstants.ASSESSMENT_COUNT_CANDIDATE)
	public ResponseEntity<Long> assessmentsCounts(@RequestParam("candidateId") Long candidateId) {
		Long count = candidateService.assessmentCounts(candidateId);
		return ResponseEntity.ok(count);
	}

	@GetMapping(path = APIConstants.INTERVIEW_COUNT_CANDIDATE)
	public ResponseEntity<Long> interviewsCounts(@RequestParam("candidateId") Long candidateId) {
		Long count = candidateService.interviewCounts(candidateId);
		return ResponseEntity.ok(count);
	}

	@GetMapping(path = APIConstants.INTERVIEW_LEVELS_CANDIDATE)
	public ResponseEntity<List<RecruitProcessDto>> interviewLevelsByMrfJdId(@RequestParam("mrfJdId") Long mrfJdId) {
		List<RecruitProcessDto> rp = candidateService.interviewLevels(mrfJdId);
		return ResponseEntity.ok(rp);
	}

	@GetMapping(path = APIConstants.TYPE_OF_INTERVIEW)
	public ResponseEntity<Object> recruitmentProcess(@RequestParam("recruitmentProcessId") Long recruitmentProcessId,
			@RequestParam("candidateId") Long candidateId) {
		Object rp = candidateService.getRecruitmentProcessById(recruitmentProcessId, candidateId);

		if (rp == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(rp);
	}

	@PostMapping(value = "/applyjob", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> addCandidate(@ModelAttribute CandidateDto candidateDto,
			@RequestParam("candidateResume") MultipartFile candidateResume) throws MessagingException, IOException {
		String resultMessage = candidateService.applyJob(candidateDto, candidateResume);
		return new ResponseEntity<>(resultMessage, HttpStatus.OK);
	}

	@PatchMapping(path=APIConstants.UPDATE_CANDIDATE_PROFILE)
	    public ResponseEntity<Candidate> updateCandidatePartial(
	            @PathVariable("candidateId") Long candidateId,
	            @RequestParam (required = false)String firstName,
	            @RequestParam (required = false)String lastName,
	            @RequestParam (required = false)Integer experience,
	            @RequestParam (required = false)String skill,
	            @RequestParam (required = false)String location,
	            @RequestParam (required = false)MultipartFile candidateResume,
	            @RequestParam (required = false)MultipartFile candidateProfileImage) {
	 
	        Candidate updatedCandidate = candidateService.updateCandidatePartial(
	                candidateId, firstName, lastName, experience, skill, location, candidateResume, candidateProfileImage);
	        
	        if (updatedCandidate != null) {
	            return ResponseEntity.ok(updatedCandidate);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
		}

	@PatchMapping(path = APIConstants.UPDATE_OFFER_STATUS)
	public ResponseEntity<String> updateOfferByCandidateAndOfferId(@RequestParam Long offerId, @RequestParam Long candidateId){
		String response = candidateService.updateOffer(offerId, candidateId);
		return ResponseEntity.ok(response);
	}
}
