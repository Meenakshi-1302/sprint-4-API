package com.rts.tap.service.impl.recruiter;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.ApproverLevelDao;
import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.serviceimplementation.OfferApprovalServiceImpl;

public class OfferApprovalServiceImplTest {

    @InjectMocks
    private OfferApprovalServiceImpl offerApprovalService;

    @Mock
    private OfferApprovalDao offerApprovalDao;

    @Mock
    private ApproverLevelDao approverLevelDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateOfferApprovalStatusApproved() {

        OfferApproval offerApproval = new OfferApproval();
        offerApproval.setStatus("approved");
        
        Offer offer = new Offer();
        offer.setMrf(new MRF()); 
        offer.getMrf().setMrfId(1L); 
        offerApproval.setOffer(offer);
        
        ApproverLevel nextLevel = new ApproverLevel();
        nextLevel.setLevel(2); 
        when(approverLevelDao.getApproverLevelByMrfIdAndLevel(1L, 2)).thenReturn(nextLevel);
        
                OfferApproval newOfferApproval = new OfferApproval();
        newOfferApproval.setApproverLevel(nextLevel);
        newOfferApproval.setOffer(offer);
        newOfferApproval.setStatus(MessageConstants.SET_OFFER_APPROVAL_STATUS);
    }

    @Test
    public void testUpdateOfferApprovalStatusNotApproved() {

        OfferApproval offerApproval = new OfferApproval();
        offerApproval.setStatus("rejected"); 
        

        offerApprovalService.updateOfferApprovalStatus(offerApproval);
        

        verify(offerApprovalDao, times(1)).updateOfferApprovalStatus(offerApproval);
        verify(approverLevelDao, never()).getApproverLevelByMrfIdAndLevel(anyLong(), anyInt());
    }

    @Test
    public void testGetOfferApprovalByCandidateId() {

        Long candidateId = 1L;
        Long mrfId = 1L;
        List<OfferApproval> approvals = new ArrayList<>();
        when(offerApprovalDao.findOfferApprovalByCandidateIdAndMrfId(candidateId, mrfId)).thenReturn(approvals);
        

        List<OfferApproval> result = offerApprovalService.getOfferApprovalByCandidateIdAndMrfId(candidateId, mrfId);
        

        assertEquals(approvals, result);
        verify(offerApprovalDao, times(1)).findOfferApprovalByCandidateIdAndMrfId(candidateId, mrfId);
    }
}