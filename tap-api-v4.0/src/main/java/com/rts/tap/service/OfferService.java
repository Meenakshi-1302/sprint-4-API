package com.rts.tap.service;

import java.io.IOException;

import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.model.Offer;

public interface OfferService {

	Offer generateOfferLetter(OfferLetterDto offerLetterDto) throws IOException;

	Offer getOfferLetterByCandidateId(Long candidateId);

	Offer updateOfferLetter(Long offerId, OfferLetterDto offerLetterDto) throws IOException;

}
