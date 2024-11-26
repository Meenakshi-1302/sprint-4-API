package com.rts.tap.dto;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.model.Offer;

public class OfferLetterDto {
	private Offer offer;
	private MultipartFile offerLetter;

	public OfferLetterDto() {
		super();
	}

	public OfferLetterDto(Offer offer, MultipartFile offerLetter) {
		super();
		this.offer = offer;
		this.offerLetter = offerLetter;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public MultipartFile getOfferLetter() {
		return offerLetter;
	}

	public void setOfferLetter(MultipartFile offerLetter) {
		this.offerLetter = offerLetter;
	}

}
