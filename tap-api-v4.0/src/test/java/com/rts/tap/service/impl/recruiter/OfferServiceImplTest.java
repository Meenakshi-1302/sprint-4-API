package com.rts.tap.service.impl.recruiter;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.ApproverLevelDao;
import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.dao.OfferDao;
import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Offer;
import com.rts.tap.serviceimplementation.OfferServiceImpl;

public class OfferServiceImplTest {

	@InjectMocks
	private OfferServiceImpl offerService;

	@Mock
	private OfferDao offerDao;

	@Mock
	private ApproverLevelDao approverLevelDao;

	@Mock
	private OfferApprovalDao offerApprovalDao;

	@Mock
	private OfferLetterDto offerLetterDto;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGenerateOfferLetter() throws IOException {

		Offer offer = new Offer();
		offer.setOfferId(1L);
		offer.setMrf(null); // Set a mock MRF as needed
		when(offerLetterDto.getOffer()).thenReturn(offer);

		MultipartFile multipartFile = mock(MultipartFile.class);
		when(multipartFile.getBytes()).thenReturn("MockOfferLetterContent".getBytes());
		when(offerLetterDto.getOfferLetter()).thenReturn(multipartFile);

		ApproverLevel approverLevel = new ApproverLevel();
		when(approverLevelDao.getApproverLevelByMrfIdAndLevel(anyLong(), anyInt())).thenReturn(approverLevel);

			}

	@Test
	public void testGetOfferLetterByCandidateId() {

		Long candidateId = 1L;
		Offer offer = new Offer();
		when(offerDao.findOfferByCandidateId(candidateId)).thenReturn(offer);

		Offer result = offerService.getOfferLetterByCandidateId(candidateId);

		assertEquals(offer, result);
		verify(offerDao, times(1)).findOfferByCandidateId(candidateId);
	}

	@Test
	public void testUpdateOfferLetter() throws IOException {

		Long offerId = 1L;
		Offer existingOffer = new Offer();
		existingOffer.setOfferId(offerId);
		existingOffer.setJoiningDate(null); 

		when(offerDao.findByOfferId(offerId)).thenReturn(existingOffer);
		when(offerLetterDto.getOfferLetter()).thenReturn(mock(MultipartFile.class));

		MultipartFile multipartFile = mock(MultipartFile.class);
		when(multipartFile.getBytes()).thenReturn("UpdatedOfferLetterContent".getBytes());
		when(offerLetterDto.getOfferLetter()).thenReturn(multipartFile);

		when(offerLetterDto.getOffer()).thenReturn(existingOffer);

		Offer updatedOffer = offerService.updateOfferLetter(offerId, offerLetterDto);

		verify(offerDao, times(1)).findByOfferId(offerId);
	}
}