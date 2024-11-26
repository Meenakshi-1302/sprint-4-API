package com.rts.tap.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import java.util.Date;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;

@Repository
public interface RecruiterMRFDashboardDao {

	List<Candidate> getCandidateByMrfId(long mrfId);
	List<Offer> getOfferByMrfId(long mrfId);
	Date[] getContractDates(Long mrfId);
	
}
