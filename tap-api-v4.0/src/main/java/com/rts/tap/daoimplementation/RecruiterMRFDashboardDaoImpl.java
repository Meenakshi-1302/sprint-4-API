package com.rts.tap.daoimplementation;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.rts.tap.dao.RecruiterMRFDashboardDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RecruiterMRFDashboardDaoImpl implements RecruiterMRFDashboardDao {
	
	EntityManager entityManager;

	public RecruiterMRFDashboardDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@Override
	public List<Candidate> getCandidateByMrfId(long mrfId) {		
		String hql = "SELECT  c FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE m.mrfId = :mrfId ";

		List<Candidate> results = entityManager.createQuery(hql, Candidate.class)
				.setParameter("mrfId", mrfId).getResultList();

		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Offer> getOfferByMrfId(long mrfId){
		  String hql="Select o from Offer o where o.mrf.mrfId=:mrfId";
		  return entityManager.createQuery(hql).setParameter("mrfId", mrfId).getResultList();	
	}
	
	@Override
	public Date[] getContractDates(Long mrfId) {
		String hql = "SELECT mrc.contractStartDate, mrc.closureDate " + "FROM MRF mrf " + "JOIN mrf.mrfCriteria mrc " + // directly
				"WHERE mrf.mrfId = :mrfId";
 
		Query query = entityManager.createQuery(hql);
		query.setParameter("mrfId", mrfId);
 
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		System.out.println(results);

		if (!results.isEmpty()) {
			Object[] result = results.get(0); 
			Date contractStartDate = (Date) result[0];
			Date closureDate = (Date) result[1];
			return new Date[] { contractStartDate, closureDate }; 
		}
		return null; 
	}

}
