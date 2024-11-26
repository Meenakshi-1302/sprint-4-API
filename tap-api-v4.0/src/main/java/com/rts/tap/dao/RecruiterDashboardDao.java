package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;

public interface RecruiterDashboardDao {
	public Long TotalMrfAssignedToRecruiter(Long employeeId);

	public Long ResolvedMrfAssignedToRecruiter(Long employeeId);

	public Long PendingMrfAssignedToRecruiter(Long employeeId);

	public List<MRF> getMrfListAssignedToRecruiter(Long employeeId);

	public Long TotalCandidatesofRecruiter(Long employeeId);

	public Long HiredCandidatesofRecruiter(Long employeeId);

	public Long PendingCandidatesofRecruiter(Long employeeId);

	public Long RejectedCandidatesofRecruiter(Long employeeId);

	List<Candidate> getCandidateByEmployeeId(Long employeeId);

	List<Offer> getOfferByEmployeeId(Long employeeId);

	public List<Object[]> getResolvedMRFCountByMonth(Long employeeId, int year);

	public List<Object[]> getPendingMRFCountByMonth(Long employeeId, int year);

	public List<Object[]> getMonthwiseHiredCountForResolvedMRF(Long employeeId, int year);

}