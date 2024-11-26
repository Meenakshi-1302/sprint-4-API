package com.rts.tap.dao;

import com.rts.tap.model.Offer;

public interface OfferDao {

	Offer findOfferByCandidateId(Long candidateId);

	Offer updateOfferLetter(Offer offer);

	void saveOffer(Offer offer);

	Offer findByOfferId(Long offerId);

}
