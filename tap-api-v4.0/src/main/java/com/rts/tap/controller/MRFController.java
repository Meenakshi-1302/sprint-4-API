
package com.rts.tap.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.MRFSubRequirementDTO;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFAgreement;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.service.MRFService;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(MessageConstants.ORGIN)
public class MRFController {

	private MRFService mrfService;

	public MRFController(MRFService mrfService) {
		super();
		this.mrfService = mrfService;
	}

	@PostMapping(APIConstants.ADD_MRF)
	public ResponseEntity<?> saveMRF(@RequestParam("mrf") String mrfJson, @RequestParam("sla") MultipartFile slaFile) {
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			MRF mrf = objectMapper.readValue(mrfJson, MRF.class);

			byte[] slaBytes = slaFile.getBytes();

			if (mrf.getMrfAgreement() != null) {
				mrf.getMrfAgreement().setServiceLevelAgreement(slaBytes);
			} else {
				return ResponseEntity.badRequest().body("MRF Agreement details are missing.");
			}

			MRF savedMRF = mrfService.addMrf(mrf);
			return ResponseEntity.ok(savedMRF);
		} catch (JsonProcessingException e) {
			return ResponseEntity.badRequest().body("Invalid JSON format: " + e.getMessage());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("File processing error: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An unexpected error occurred: " + e.getMessage());
		}
	}

	@PutMapping(APIConstants.UPDATE_MRF)
	public ResponseEntity<MRF> updateMRF(@PathVariable Long mrfId, @RequestParam("mrf") String mrfJson) {
		try {
			// Parse MRF JSON from the request
			ObjectMapper objectMapper = new ObjectMapper();
			MRF mrf = objectMapper.readValue(mrfJson, MRF.class);

			MRF MrfSla = mrfService.getMrfById(mrfId);
			mrf.setMrfAgreement(MrfSla.getMrfAgreement());

			MRF updatedMRF = mrfService.updateMrf(mrfId, mrf);
			return ResponseEntity.ok(updatedMRF);
		} catch (JsonProcessingException e) {
			return ResponseEntity.badRequest().body(null);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(APIConstants.GET_MRF)
	public ResponseEntity<MRF> getMRFById(@PathVariable Long mrfId) {
		MRF mrf = mrfService.getMrfById(mrfId);
		return mrf != null ? ResponseEntity.ok(mrf) : ResponseEntity.notFound().build();
	}

	@GetMapping(APIConstants.GET_ALL_MRF)
	public ResponseEntity<List<MRF>> getAllMRFs() {
		List<MRF> mrfs = mrfService.getAllMrf();
		return ResponseEntity.ok(mrfs);
	}

	@GetMapping(APIConstants.GET_ALL_SUBREQUIREMENTID_MAPPED_WITH_MRF)
	public ResponseEntity<List<MRFSubRequirementDTO>> getAllMRFsSubRequirementId() {
		List<MRF> mrfs = mrfService.getAllMrf();

		List<MRFSubRequirementDTO> dtoList = mrfs.stream()
				.map(mrf -> new MRFSubRequirementDTO(List
						.of(mrf.getSubRequirements() != null ? mrf.getSubRequirements().getSubRequirementId() : null))) // Adjust
																														// as
																														// necessary
				.collect(Collectors.toList());

		return ResponseEntity.ok(dtoList);
	}

	@DeleteMapping(APIConstants.DELETE_MRF)
	public ResponseEntity<String> deleteMRF(@PathVariable Long mrfId) {
		System.out.println(mrfId);
		String msg = mrfService.deleteMrfById(mrfId);
		return ResponseEntity.ok(msg);

	}

	@GetMapping(APIConstants.GET_RESOURCE_FILLED_COUNT)
	public ResponseEntity<?> getRequirementFilledCount(@PathVariable Long requirementId) {
		try {
			int filledCount = mrfService.getRequirementFilledCount(requirementId);
			return ResponseEntity.ok(filledCount);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching data: " + e.getMessage());
		}
	}

	@PostMapping(APIConstants.ASSIGN_MRF_TO_RECRUITING_MANAGER)
	public ResponseEntity<MRFRecruitingManager> assignMrfToRecruitingManager(
			@RequestBody MRFRecruitingManager mrfRecruitingManager) {
		try {
			MRFRecruitingManager assignMrfToRecruitingManager = mrfService
					.assignMrfToRecruitingManager(mrfRecruitingManager);
			return ResponseEntity.ok(assignMrfToRecruitingManager);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(APIConstants.GET_ALL_RECRUITING_MANAGER)
	public ResponseEntity<List<Employee>> getAllRecruitingManager() {
		List<Employee> listOfRecruitingManager = mrfService.getRecruitingManagersByRoleName();
		return ResponseEntity.ok(listOfRecruitingManager);
	}

	@GetMapping(APIConstants.GET_ALL_ASSIGNED_MRF_TO_RECRUITING_MANAGER)
	public List<MRFRecruitingManager> getAssignedMRFs() {
		return mrfService.getAssignedMRFs();
	}

	@GetMapping(APIConstants.GET_OFFER_ASSIGNED_BY_RECRUITING_MANAGER)
	public ResponseEntity<List<OfferApproval>> getOfferApprovalsByEmployeeId(@PathVariable Long employeeId) {
		List<OfferApproval> approvals = mrfService.getOfferApprovalsByEmployeeId(employeeId);
		return ResponseEntity.ok(approvals);
	}

}
