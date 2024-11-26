package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;

public interface RecruiterDashboardService {
	public Long TotalMrfAssignedToRecruiter(Long employeeId);

	public List<MRF> getMrfListAssignedToRecruiter(Long employeeId);

	public Long ResolvedMrfAssignedToRecruiter(Long employeeId);

	public Long PendingMrfAssignedToRecruiter(Long employeeId);

	public Long TotalCandidatesofRecruiter(Long employeeId);

	Long HiredCandidatesofRecruiter(Long employeeId);

	Long PendingCandidatesofRecruiter(Long employeeId);

	public Long RejectedCandidatesofRecruiter(Long employeeId);

	public List<Candidate> getCandidateByEmployeeId(Long employeeId);

	public List<Offer> getOfferByEmployeeId(Long employeeId);

	public List<Object[]> getResolvedMRFCountByMonth(Long employeeId, int year);

	public List<Object[]> getPendingMRFCountByMonth(Long employeeId, int year);

	public List<Object[]> getMonthwiseHiredCountForResolvedMRF(Long employeeId, int year);

}