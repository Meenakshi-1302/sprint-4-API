package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.OfferApproval;

public interface OfferApprovalService {

	void updateOfferApprovalStatus(OfferApproval offerApproval);

	List<OfferApproval> getOfferApprovalByCandidateIdAndMrfId(Long candidateId, Long mrfId);

	public List<OfferApproval> getOfferApprovalByRecruitingManagerId(Long recruitingManagerId);

}
