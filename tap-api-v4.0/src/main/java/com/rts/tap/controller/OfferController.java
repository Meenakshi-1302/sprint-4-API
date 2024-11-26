package com.rts.tap.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.model.Offer;
import com.rts.tap.service.OfferService;

@RestController
@RequestMapping(path = APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class OfferController {
	OfferService offerService;

	public OfferController(OfferService offerService) {
		super();
		this.offerService = offerService;
	}

	@PostMapping(path = APIConstants.GENERATE_OFFERLETTER_FOR_CANDIDATE, consumes = "multipart/form-data")
	public ResponseEntity<Offer> generateOfferLetter(@RequestParam("offer") MultipartFile offerJsonFile,
			@RequestParam("offerLetter") MultipartFile offerLetter) throws IOException {
		String offerJson = new String(offerJsonFile.getBytes(), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		Offer offer = objectMapper.readValue(offerJson, Offer.class);
		OfferLetterDto offerLetterDto = new OfferLetterDto();
		offerLetterDto.setOffer(offer);
		offerLetterDto.setOfferLetter(offerLetter);
		return ResponseEntity.ok(offerService.generateOfferLetter(offerLetterDto));
	}

	@GetMapping(APIConstants.GET_OFFERLETTER_BY_CANDIDATE_ID)
	public ResponseEntity<Offer> getOfferLetter(@PathVariable Long candidateId) {
		return ResponseEntity.ok(offerService.getOfferLetterByCandidateId(candidateId));
	}

	@PutMapping(path = APIConstants.UPDATE_OFFER_LETTER, consumes = "multipart/form-data")
	public ResponseEntity<Offer> updateOfferLetter(@PathVariable Long offerId,
			@RequestParam("offer") MultipartFile offerJsonFile, @RequestParam("offerLetter") MultipartFile offerLetter)
			throws IOException {
		String offerJson = new String(offerJsonFile.getBytes(), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		Offer offer = objectMapper.readValue(offerJson, Offer.class);
		OfferLetterDto offerLetterDto = new OfferLetterDto();
		offerLetterDto.setOffer(offer);
		offerLetterDto.setOfferLetter(offerLetter);
		return ResponseEntity.ok(offerService.updateOfferLetter(offerId, offerLetterDto));
	}
}