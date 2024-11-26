package com.rts.tap.daoimplementation;
import org.springframework.stereotype.Repository;
 
import com.rts.tap.dao.OfferDao;
import com.rts.tap.model.Offer;
import jakarta.persistence.EntityManager;

@Repository
public class OfferDaoImpl implements OfferDao {
	EntityManager entityManager;
	public OfferDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	@Override
	public void saveOffer(Offer offer) {
		entityManager.persist(offer);
	}
	@Override
	public Offer findOfferByCandidateId(Long candidateId) {
		String hql = "Select o from Offer o where o.candidate.candidateId = :candidateId";
		return (Offer) entityManager.createQuery(hql).setParameter("candidateId", candidateId).getSingleResult();
	}
	@Override
	public Offer updateOfferLetter(Offer offer) {
		return entityManager.merge(offer);
	}
 
	@Override
	public Offer findByOfferId(Long offerId) {
		return entityManager.find(Offer.class, offerId);
	}
}