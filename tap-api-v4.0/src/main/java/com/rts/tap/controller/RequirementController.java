package com.rts.tap.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Requirement;
import com.rts.tap.service.RequirementService;

@RestController
@CrossOrigin(origins = APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(path = APIConstants.REQUIREMENT_REQUESTMAPPING_API)
public class RequirementController {

	private final RequirementService requirementService;

	public RequirementController(RequirementService requirementService) {
		this.requirementService = requirementService;
	}

	@PostMapping(path = APIConstants.REQUIREMENT_ADD_API)
	public ResponseEntity<String> createRequirement(@RequestBody Requirement requirementDTO) {
		Long clientId = requirementDTO.getClient().getClientId();
		String response = requirementService.createRequirement(requirementDTO, clientId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping(path = APIConstants.REQUIREMENT_UPDATE_API)
	public ResponseEntity<String> updateRequirement(@RequestBody Requirement requirementDTO) {
		String response = requirementService.changeRequirement(requirementDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping(path = APIConstants.REQUIREMENT_DELETE_API)
	public ResponseEntity<String> deleteRequirement(@PathVariable("requirementId") Long requirementId) {
		try {
			String res = requirementService.removeRequirement(requirementId);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting requirement: " + e.getMessage());
		}
	}

	@GetMapping(path = APIConstants.REQUIREMENT_GETALL_REQUIREMENT_API)
	public ResponseEntity<List<Requirement>> getAllRequirements() {
		List<Requirement> requirements = requirementService.findAllRequirements();
		return ResponseEntity.ok(requirements); // Returns 200 OK with the list
	}

	@GetMapping(path = APIConstants.REQUIREMENT_REQUIREMENTBY_CLIENT_API)
	public List<Requirement> findRequirementsByClientId(@PathVariable("clientId") Long clientId) {
		return requirementService.RequirementsByClient(clientId);
	}

	@GetMapping(path = APIConstants.REQUIREMENT_COUNT_CLIENT_API)
	public Integer clientRequirementCount(@PathVariable("clientId") Long clientId) {
		return requirementService.requirementCount(clientId);
	}

	@GetMapping(path = APIConstants.REQUIREMENT_LIST_BY_CLIENT_API)
	public List<Requirement> clientRequirements(@PathVariable("clientId") Long clientId) {
		return requirementService.getRequirementsByClientId(clientId);
	}

//	@GetMapping(APIConstants.GET_CLIENT_HIRED)
//	public List<MRF> getHiredCandidatesByClient(@PathVariable("requirementId") Long requirementId) {
//		return requirementService.HiredCandidateList(requirementId);
//	}
	
	//Keerthana
	@GetMapping(path = APIConstants.REQUIREMENT_GET_REQUIREMENTBYId_API)
	public ResponseEntity<Requirement> getRequirementsById(@PathVariable("requirementId") Long requirementId) {
		Requirement requirements = requirementService.findRequirementsById(requirementId);
		System.out.println(requirements);
		return ResponseEntity.ok(requirements); // Returns 200 OK with the list
	}

}

