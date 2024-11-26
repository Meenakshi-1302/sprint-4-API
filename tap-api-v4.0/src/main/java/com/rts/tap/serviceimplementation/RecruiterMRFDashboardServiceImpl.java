package com.rts.tap.serviceimplementation;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.rts.tap.dao.RecruiterMRFDashboardDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;
import com.rts.tap.service.RecruiterMRFDashboardService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecruiterMRFDashboardServiceImpl implements RecruiterMRFDashboardService {
	
	RecruiterMRFDashboardDao recruiterMRFDashboardDao;
	
	public RecruiterMRFDashboardServiceImpl(RecruiterMRFDashboardDao recruiterMRFDashboardDao) {
		super();
		this.recruiterMRFDashboardDao = recruiterMRFDashboardDao;
	}

	@Override
	public List<Candidate> getCandidateByMrfId(long mrfId) {
		return recruiterMRFDashboardDao.getCandidateByMrfId(mrfId) ;
	}
	
	@Override
	public List<Offer> getOfferByMrfId(long mrfId) {
		return recruiterMRFDashboardDao.getOfferByMrfId(mrfId) ;
	}
	
	@Override
	public Long getRemainingDays(Long mrfId) {
	    Date[] contractDates = recruiterMRFDashboardDao.getContractDates(mrfId);
 
	    if (contractDates != null && contractDates.length == 2) {
	        Date closureDate = contractDates[1];
 
	        if (closureDate != null) {
	            long currentDateMillis = System.currentTimeMillis();
	            long closureDateMillis = closureDate.getTime();
 
	            if (currentDateMillis < closureDateMillis) {
	                return (closureDateMillis - currentDateMillis) / (24 * 60 * 60 * 1000); // Return remaining days
	            } else {
	                return 0L; 
	            }
	        }
	    }
	    return 0L; 
	}

}
