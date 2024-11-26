package com.rts.tap.serviceimplementation;
import java.io.IOException;
import org.springframework.stereotype.Service;
 
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.ApproverLevelDao;
import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.dao.OfferDao;
import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Offer;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.service.OfferService;
 
import jakarta.transaction.Transactional;
@Service
@Transactional
public class OfferServiceImpl implements OfferService {
	OfferDao offerDao;
	ApproverLevelDao approverLevelDao;
	OfferApprovalDao offerApprovalDao;
	public OfferServiceImpl(OfferDao offerDao, ApproverLevelDao approverLevelDao, OfferApprovalDao offerApprovalDao) {
		super();
		this.offerDao = offerDao;
		this.approverLevelDao = approverLevelDao;
		this.offerApprovalDao = offerApprovalDao;
	}
 
	@Override
	public Offer generateOfferLetter(OfferLetterDto offerLetterDto) throws IOException {
		Offer offer = offerLetterDto.getOffer();
		offer.setOfferLetter(offerLetterDto.getOfferLetter().getBytes());
		offerDao.saveOffer(offer);
		offer.setOfferId(offer.getOfferId());
		OfferApproval offerApproval = new OfferApproval();
		ApproverLevel approverLevel = approverLevelDao.getApproverLevelByMrfIdAndLevel(offerLetterDto.getOffer().getMrf().getMrfId(), 1);
		offerApproval.setApproverLevel(approverLevel);
		offerApproval.setOffer(offer);
		offerApproval.setStatus(MessageConstants.SET_OFFER_APPROVAL_STATUS);
		offerApprovalDao.saveOfferApproval(offerApproval);
		return offer;
	}
	@Override
	public Offer getOfferLetterByCandidateId(Long candidateId) {
		return offerDao.findOfferByCandidateId(candidateId);
	}
 
	@Override
	public Offer updateOfferLetter(Long offerId, OfferLetterDto offerLetterDto) throws IOException {
		Offer offer = offerDao.findByOfferId(offerId);
		System.out.println(offerLetterDto.getOfferLetter().getBytes());
		System.out.println(offer.getCandidateOfferLetter());
		System.out.println(offerLetterDto.getOffer().getJoiningDate());
		System.out.println(offerLetterDto.getOffer().getOfferPackage());
		offer.setOfferPackage(offerLetterDto.getOffer().getOfferPackage());
		offer.setJoiningDate(offerLetterDto.getOffer().getJoiningDate());
		offer.setOfferLetter(offerLetterDto.getOfferLetter().getBytes());
		return offerDao.updateOfferLetter(offer);
	}
}
