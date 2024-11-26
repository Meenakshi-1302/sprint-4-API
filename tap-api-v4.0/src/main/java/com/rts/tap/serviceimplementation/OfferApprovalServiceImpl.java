package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.ApproverLevelDao;
import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.service.OfferApprovalService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OfferApprovalServiceImpl implements OfferApprovalService {
	OfferApprovalDao offerApprovalDao;
	ApproverLevelDao approverLevelDao;

	public OfferApprovalServiceImpl(OfferApprovalDao offerApprovalDao, ApproverLevelDao approverLevelDao) {
		super();
		this.offerApprovalDao = offerApprovalDao;
		this.approverLevelDao = approverLevelDao;
	}

	@SuppressWarnings("null")
	@Override
	public void updateOfferApprovalStatus(OfferApproval offerApproval) {
		offerApprovalDao.updateOfferApprovalStatus(offerApproval);

		if (offerApproval.getStatus().equalsIgnoreCase("approved")) {
			OfferApproval offerApproval2 = null;
			ApproverLevel approverLevel = approverLevelDao.getApproverLevelByMrfIdAndLevel(
					offerApproval.getOffer().getMrf().getMrfId(), offerApproval.getApproverLevel().getLevel() + 1);
			offerApproval2.setApproverLevel(approverLevel);
			offerApproval2.setOffer(offerApproval.getOffer());
			offerApproval2.setStatus(MessageConstants.SET_OFFER_APPROVAL_STATUS);
			offerApprovalDao.saveOfferApproval(offerApproval2);
		}
	}

	@Override
	public List<OfferApproval> getOfferApprovalByCandidateIdAndMrfId(Long candidateId, Long mrfId) {
		return offerApprovalDao.findOfferApprovalByCandidateIdAndMrfId(candidateId, mrfId);
	}
	
	// edited by team-c
	@Override
	public List<OfferApproval> getOfferApprovalByRecruitingManagerId(Long recruitingManagerId) {
		return offerApprovalDao.findOfferApprovalByApproverId(recruitingManagerId);
	}

}
