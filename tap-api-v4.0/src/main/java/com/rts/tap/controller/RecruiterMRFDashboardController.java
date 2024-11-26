package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;
import com.rts.tap.service.RecruiterMRFDashboardService;

@RestController
@RequestMapping(path = APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class RecruiterMRFDashboardController {
	
	RecruiterMRFDashboardService recruiterMRFDashboardService;

	public RecruiterMRFDashboardController(RecruiterMRFDashboardService recruiterMRFDashboardService) {
		super();
		this.recruiterMRFDashboardService = recruiterMRFDashboardService;
	}
	
	@GetMapping(path=APIConstants.GET_CANDIDATE_BY_MRFID)
	public ResponseEntity<List<Candidate>> getCandidateByMrfId(@PathVariable Long mrfId) {
		List<Candidate> candidate = recruiterMRFDashboardService.getCandidateByMrfId(mrfId);
		return ResponseEntity.ok(candidate);
	}
	
	@GetMapping(path=APIConstants.GET_OFFER_BY_MRFID)
	public ResponseEntity<List<Offer>> getOfferByMrfId(@PathVariable Long mrfId) {
		List<Offer> offer = recruiterMRFDashboardService.getOfferByMrfId(mrfId);
		return ResponseEntity.ok(offer);
	}
	
	@GetMapping(APIConstants.REMAINING_DAYS_FOR_MRF)
	public ResponseEntity<Long> getRemainingDays(@PathVariable("mrfId") Long mrfId) {
	    Long remainingDays = recruiterMRFDashboardService.getRemainingDays(mrfId);
 
	    if (remainingDays >= 0) {
	        return ResponseEntity.ok(remainingDays); 
	    } else {
	        return ResponseEntity.notFound().build(); 
	    }
	}
    
}
