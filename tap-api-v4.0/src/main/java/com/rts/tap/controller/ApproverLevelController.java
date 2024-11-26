package com.rts.tap.controller;

import java.util.List;

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
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Employee;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.service.ApproverLevelService;

@RestController
@RequestMapping(path = APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class ApproverLevelController {

	private ApproverLevelService approverLevelService;

	public ApproverLevelController(ApproverLevelService approverLevelService) {
		super();
		this.approverLevelService = approverLevelService;
	}

	@PostMapping(path = APIConstants.ADD_APPROVERLEVEL_URL)
	public ResponseEntity<String> saveApproverLevel(@RequestBody List<ApproverLevel> approverLevel) {
	        approverLevelService.saveApproverLevel(approverLevel);
	        return ResponseEntity.ok(MessageConstants.APPROVER_LEVEL_ADDED);  
	}
	
	@PutMapping(path=APIConstants.UPDATE_APPROVERLEVEL_URL)
	public ResponseEntity<String> updateApproverLevel(@RequestBody ApproverLevel approverLevel) {	   
	        approverLevelService.updateApproverLevel(approverLevel);
	        return ResponseEntity.ok(MessageConstants.APPROVER_LEVEL_UPDATED);	       
	}
	
	@DeleteMapping(path=APIConstants.DELETE_APPROVERLEVEL_URL)
	public ResponseEntity<String> deleteApproverLevel(@PathVariable("approverLevelId")long approverLevelId) {
			approverLevelService.deleteApproverLevel(approverLevelId);
			return ResponseEntity.ok(MessageConstants.APPROVER_LEVEL_DELETED);	 		
	}
	
	@GetMapping(path=APIConstants.GET_APPROVERLEVEL_URL)
	public ResponseEntity<List<ApproverLevel>> getApproverLevelByMrfId(@PathVariable Long mrfId) {
		List<ApproverLevel> approverLevel = approverLevelService.getApproverLevelByMrfId(mrfId);
		return ResponseEntity.ok(approverLevel);
	}
	
	@GetMapping(path=APIConstants.GET_WORKFLOW_URL)
	public ResponseEntity<WorkFlow> getWorkFlowByMrfId(@PathVariable Long mrfId) {
		WorkFlow workFlow = approverLevelService.getWorkFlowByMrfId(mrfId);
		return ResponseEntity.ok(workFlow);
	}
	
	@GetMapping(path=APIConstants.GET_EMPLOYEE_URL)
	public ResponseEntity<Employee> getEmployeeByEmployeeId(@PathVariable Long employeeId) {
		Employee employee = approverLevelService.getEmployeeByEmployeeId(employeeId);
		return ResponseEntity.ok(employee);
	}

	@PostMapping(path = APIConstants.ADD_SINGLE_APPROVERLEVEL_URL)
	public ResponseEntity<String> saveSingleApproverLevel(@RequestBody ApproverLevel approverLevel) {
	        approverLevelService.saveSingleApproverLevel(approverLevel);
	        return ResponseEntity.ok(MessageConstants.APPROVER_LEVEL_ADDED);  
	}
}
