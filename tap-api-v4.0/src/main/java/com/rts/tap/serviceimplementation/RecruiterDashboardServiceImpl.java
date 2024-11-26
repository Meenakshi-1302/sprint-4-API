package com.rts.tap.serviceimplementation;

import java.util.List;
import org.springframework.stereotype.Service;
import com.rts.tap.dao.RecruiterDashboardDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;
import com.rts.tap.service.RecruiterDashboardService;

@Service
public class RecruiterDashboardServiceImpl implements RecruiterDashboardService {
	private RecruiterDashboardDao recruiterDao;

	public RecruiterDashboardServiceImpl(RecruiterDashboardDao recruiterDao) {
		super();
		this.recruiterDao = recruiterDao;
	}

	@Override
	public Long TotalMrfAssignedToRecruiter(Long employeeId) {
		return recruiterDao.TotalMrfAssignedToRecruiter(employeeId);
	}

	@Override
	public List<MRF> getMrfListAssignedToRecruiter(Long employeeId) {
		return recruiterDao.getMrfListAssignedToRecruiter(employeeId);
	}

	@Override
	public Long ResolvedMrfAssignedToRecruiter(Long employeeId) {
		return recruiterDao.ResolvedMrfAssignedToRecruiter(employeeId);
	}

	@Override
	public Long PendingMrfAssignedToRecruiter(Long employeeId) {
		return recruiterDao.PendingMrfAssignedToRecruiter(employeeId);
	}

	public Long TotalCandidatesofRecruiter(Long employeeId) {
		return recruiterDao.TotalCandidatesofRecruiter(employeeId);
	}

	@Override
	public Long HiredCandidatesofRecruiter(Long employeeId) {
		return recruiterDao.HiredCandidatesofRecruiter(employeeId);
	}

	@Override
	public Long PendingCandidatesofRecruiter(Long employeeId) {
		return recruiterDao.PendingCandidatesofRecruiter(employeeId);

	}

	@Override
	public List<Candidate> getCandidateByEmployeeId(Long employeeId) {
		return recruiterDao.getCandidateByEmployeeId(employeeId);
	}

	@Override
	public List<Offer> getOfferByEmployeeId(Long employeeId) {
		return recruiterDao.getOfferByEmployeeId(employeeId);
	}

	@Override
	public List<Object[]> getResolvedMRFCountByMonth(Long employeeId, int year) {
		return recruiterDao.getResolvedMRFCountByMonth(employeeId, year);
	}

	@Override
	public List<Object[]> getMonthwiseHiredCountForResolvedMRF(Long employeeId, int year) {
		return recruiterDao.getMonthwiseHiredCountForResolvedMRF(employeeId, year);
	}

	@Override
	public Long RejectedCandidatesofRecruiter(Long employeeId) {
		return recruiterDao.RejectedCandidatesofRecruiter(employeeId);
	}

	@Override
	public List<Object[]> getPendingMRFCountByMonth(Long employeeId, int year) {
		return recruiterDao.getPendingMRFCountByMonth(employeeId, year);
	}

}