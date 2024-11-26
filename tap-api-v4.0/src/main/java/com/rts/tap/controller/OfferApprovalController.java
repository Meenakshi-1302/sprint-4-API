package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.service.OfferApprovalService;

@RestController
@RequestMapping(path = APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class OfferApprovalController {
	OfferApprovalService offerApprovalService;

	public OfferApprovalController(OfferApprovalService offerApprovalService) {
		super();
		this.offerApprovalService = offerApprovalService;
	}

	@PutMapping(APIConstants.UPDATE_OFFER_APPROVAL_STATUS)
	public ResponseEntity<String> updateOfferApprovalStatus(@RequestBody OfferApproval offerApproval) {
		offerApprovalService.updateOfferApprovalStatus(offerApproval);
		return ResponseEntity.ok(MessageConstants.OFFER_APPROVAL_STATUS_UPDATED);
	}
	
	@GetMapping(APIConstants.GET_OFFER_APPROVAL_BY_CANDIDATE_ID_AND_MRF_ID)
	public ResponseEntity<List<OfferApproval>> getOfferApprovalByCandidateId(@RequestParam("candidateId") Long candidateId, @RequestParam("mrfId") Long mrfId){
		return ResponseEntity.ok(offerApprovalService.getOfferApprovalByCandidateIdAndMrfId(candidateId, mrfId));
	}
	@GetMapping(APIConstants.GET_OFFER_APPROVAL_BY_RECRUITINGMANAGERID)
	public ResponseEntity<List<OfferApproval>> getOfferApprovalByRMId(@PathVariable Long recruitingManagerId){
		return ResponseEntity.ok(offerApprovalService.getOfferApprovalByRecruitingManagerId(recruitingManagerId));
	}

}
