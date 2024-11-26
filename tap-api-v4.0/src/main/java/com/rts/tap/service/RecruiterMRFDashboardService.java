package com.rts.tap.service;

import java.util.List;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;

public interface RecruiterMRFDashboardService {

	List<Candidate> getCandidateByMrfId(long mrfId);
	List<Offer> getOfferByMrfId(long mrfId);
	Long getRemainingDays(Long mrfId);
	
}
