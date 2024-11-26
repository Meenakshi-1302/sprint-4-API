package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;
import com.rts.tap.service.RecruiterDashboardService;

@RestController
@CrossOrigin(origins = APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(path = APIConstants.RECRUITER_DASHBOARD)
public class RecruiterDashboardController {
	private RecruiterDashboardService recruiterService;

	public RecruiterDashboardController(RecruiterDashboardService recruiterService) {
		super();
		this.recruiterService = recruiterService;
	}

	@GetMapping(APIConstants.GET_TOTAL_MRF_ASSIGNED_TO_RECRUITER)
	public Long totalMrfAssignedToRecruiter(@PathVariable Long mrfRecruitersId) {
		return recruiterService.TotalMrfAssignedToRecruiter(mrfRecruitersId);
	}

	@GetMapping(APIConstants.GET_ALL_MRFLIST_ASSIGNED_TO_RECRUITER)
	public ResponseEntity<List<MRF>> getAllMrfAssigned(@PathVariable Long employeeId) {
		List<MRF> MrfAssigned = recruiterService.getMrfListAssignedToRecruiter(employeeId);
		return ResponseEntity.ok(MrfAssigned);
	}

	@GetMapping(APIConstants.RESOLVED_MRF_OF_RECRUITER)
	public Long resolvedMrfofRecruiter(@PathVariable Long employeeId) {
		return recruiterService.ResolvedMrfAssignedToRecruiter(employeeId);
	}

	@GetMapping(APIConstants.PENDING_MRF_OF_RECRUITER)
	public Long pendingMrfofRecruiter(@PathVariable Long employeeId) {
		return recruiterService.PendingMrfAssignedToRecruiter(employeeId);
	}

	@GetMapping(APIConstants.GET_TOTAL_CANDIDATES_ASSIGNED_TO_RECRUITER)
	public Long totalCandidatesofRecruiter(@PathVariable Long employeeId) {
		return recruiterService.TotalCandidatesofRecruiter(employeeId);
	}

	@GetMapping(APIConstants.GET_HIRED_CANDIDATES_ASSIGNED_TO_RECRUITER)
	public Long HiredCandidatesofRecruiter(@PathVariable Long employeeId) {
		return recruiterService.HiredCandidatesofRecruiter(employeeId);
	}

	@GetMapping(APIConstants.GET_PENDING_CANDIDATES_ASSIGNED_TO_RECRUITER)
	public Long PendingCandidatesofRecruiter(@PathVariable Long employeeId) {
		return recruiterService.PendingCandidatesofRecruiter(employeeId);
	}

	@GetMapping(APIConstants.GET_REJECTED_CANDIDATES_ASSIGNED_TO_RECRUITER)
	public Long rejectedCandidatesofRecruiter(@PathVariable Long employeeId) {
		return recruiterService.RejectedCandidatesofRecruiter(employeeId);
	}

	@GetMapping(APIConstants.GET_OFFER_BY_EMPLOYEEID)
	public ResponseEntity<List<Offer>> getOfferbyEmployeeId(@PathVariable Long employeeId) {
		List<Offer> offer = recruiterService.getOfferByEmployeeId(employeeId);
		return ResponseEntity.ok(offer);
	}

	@GetMapping(APIConstants.GET_RESOLVED_MRF_COUNT_BY_MONTH)
	public ResponseEntity<List<Object[]>> getResolvedMRFCountByMonth(@PathVariable Long employeeId,
			@PathVariable int year) {
		List<Object[]> resolvedMRFCounts = recruiterService.getResolvedMRFCountByMonth(employeeId, year);
		return ResponseEntity.ok(resolvedMRFCounts);
	}

	@GetMapping(APIConstants.GET_RESOLVED_MRF_CANDIDATE_COUNT_BY_MONTH)
	public ResponseEntity<List<Object[]>> getResolvedMRFCandidateCountByMonth(@PathVariable Long employeeId,
			@PathVariable int year) {
		List<Object[]> resolvedMRFCounts = recruiterService.getMonthwiseHiredCountForResolvedMRF(employeeId, year);
		return ResponseEntity.ok(resolvedMRFCounts);
	}

	@GetMapping(APIConstants.GET_PENDING_MRF_COUNT_BY_MONTH)
	public ResponseEntity<List<Object[]>> getPendingMRFCountByMonth(@PathVariable Long employeeId,
			@PathVariable int year) {
		List<Object[]> resolvedMRFCounts = recruiterService.getPendingMRFCountByMonth(employeeId, year);
		return ResponseEntity.ok(resolvedMRFCounts);
	}

}