package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.SubRequirements;
import com.rts.tap.service.SubRequirementService;

@RestController
@CrossOrigin(origins = APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(path = APIConstants.REQUESTMAPPING_SUB_REQUIREMENTS)
public class SubRequirementsController {

	private SubRequirementService subRequirementService;

	public SubRequirementsController(SubRequirementService subRequirementService) {
		this.subRequirementService = subRequirementService;
	}

	@PostMapping(path = APIConstants.ADD_SUB_REQUIREMENTS)
	public ResponseEntity<String> createSubRequirement(@RequestBody SubRequirements subRequirements) {
		String response = subRequirementService.addSubRequirement(subRequirements);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(path = APIConstants.VIEW_SUB_REQUIREMENTS)
	public ResponseEntity<List<SubRequirements>> getAllSubRequirements() {
		List<SubRequirements> subRequirements = subRequirementService.viewAllSubRequirements();
		return ResponseEntity.ok(subRequirements);
	}

}
