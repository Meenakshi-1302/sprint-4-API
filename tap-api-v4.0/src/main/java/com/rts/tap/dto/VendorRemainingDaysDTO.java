package com.rts.tap.dto;

import com.rts.tap.model.MRF;

public class VendorRemainingDaysDTO {

	private Long mrfId;
	private String probableDesignation;
	private Long remainingDays;
	private MRF mrf;

	public VendorRemainingDaysDTO() {
		super();
	}

	public Long getMrfId() {
		return mrfId;
	}

	public void setMrfId(Long mrfId) {
		this.mrfId = mrfId;
	}

	public String getProbableDesignation() {
		return probableDesignation;
	}

	public void setProbableDesignation(String probableDesignation) {
		this.probableDesignation = probableDesignation;
	}

	public Long getRemainingDays() {
		return remainingDays;
	}

	public void setRemainingDays(Long remainingDays) {
		this.remainingDays = remainingDays;
	}

	public MRF getMrf() {
		return mrf;
	}

	public void setMrf(MRF mrf) {
		this.mrf = mrf;
	}

}
