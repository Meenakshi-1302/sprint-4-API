package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Candidate;
import com.rts.tap.service.ClientService;
import com.rts.tap.service.DashboardService;

@RequestMapping(APIConstants.CLIENT_REQUESTMAPPING_API)
@RestController
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
public class ClientDashboardController {

	private DashboardService clientService;
	private ClientService service;
	

	public ClientDashboardController(DashboardService clientService, ClientService service) {
		super();
		this.clientService = clientService;
		this.service = service;
	}
	
	@GetMapping(APIConstants.GET_CLIENT_CANDIDATE_HIRED)
	public Long getHiredCountByClient(@PathVariable("clientId") Long clientId) {
		return clientService.HiredCandidatesByClientId(clientId);
	}

	@GetMapping(APIConstants.GET_CLIENT_CANDIDATE_SHORTLISTED)
	public Long getShortlistedCountByClient(@PathVariable("clientId") Long clientId) {
		return clientService.ShortlistedCandidatesByClientId(clientId);
	}

	@GetMapping(APIConstants.GET_CLIENT_HIRED)
	public Long getHiredCandidatesByClient(@PathVariable("requirementId") Long requirementId) {
		return clientService.HiredCandidateList(requirementId);
	}

	@GetMapping(APIConstants.GET_CLIENT_SHORTLISTED)
	public Long getShortListedCandidatesByClient(@PathVariable("requirementId") Long requirementId) {
		return clientService.ShortListedCandidateList(requirementId);
	}

	@GetMapping(APIConstants.GET_CANDIDATE_HIRED)
	public ResponseEntity<List<Candidate>> getHiredCandidates(@PathVariable("clientId") Long clientId) {
		List<Candidate> hiredCandidates = clientService.hiredPeopleData(clientId);
		return ResponseEntity.ok(hiredCandidates);
	}

	@GetMapping(APIConstants.GET_CANDIDATE_SHORTLISTED)
	public ResponseEntity<List<Candidate>> getShortlist(@PathVariable("clientId") Long clientId) {
		List<Candidate> shortlistedCandidates = clientService.shortListedPeople(clientId);
		return ResponseEntity.ok(shortlistedCandidates);
	}

	@GetMapping(APIConstants.GET_CANDIDATE_SHORTLISTED_REQUIREMENT)
	public ResponseEntity<List<Candidate>> getShortlistRequirement(@PathVariable("requirementId") Long requirementId) {
		List<Candidate> shortlistedCandidates = clientService.shortListedByRequirment(requirementId);
		return ResponseEntity.ok(shortlistedCandidates);
	}

	@GetMapping(APIConstants.GET_CANDIDATE_HIRED_REQUIREMENT)
	public ResponseEntity<List<Candidate>> getHiredRequirement(@PathVariable("requirementId") Long requirementId) {
		List<Candidate> shortlistedCandidates = clientService.hiredByRequirement(requirementId);
		return ResponseEntity.ok(shortlistedCandidates);
	}

	@GetMapping(APIConstants.CLIENT_ID_BY_EMAIL)
	public ResponseEntity<Long> getClientIdByEmail(@RequestParam("email") String email){
		Long id = service.getClientIdByEmail(email);
		return ResponseEntity.ok(id);
	}
}
