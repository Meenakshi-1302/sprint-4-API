package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.WorkFlowDto;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.service.WorkFlowService;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class WorkFlowController {
	WorkFlowService workFlowService;
	
	public WorkFlowController(WorkFlowService workFlowService) {
		super();
		this.workFlowService = workFlowService;
	}

	@GetMapping(APIConstants.GET_WORKFLOW_FOR_RECRUITMENT_PROCESS)
	public ResponseEntity<WorkFlow> getWorkflowForRecruitmentProcess(@PathVariable Long mrfId) {
		try {
			WorkFlow workFlow = workFlowService.getWorkflowByMrfIdForRecruitmentProcess(mrfId);
			return ResponseEntity.ok(workFlow);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping(APIConstants.RECRUITING_MANAGER_GET_WORKFLOW_BY_EMPLOYEEID)
	public ResponseEntity<List<WorkFlow>> getWorkflowByEmployeeId(@PathVariable Long employeeId) {
	    try {
	        List<WorkFlow> workFlows = workFlowService.getWorkFlowByEmployeeId(employeeId);
	        return ResponseEntity.ok(workFlows);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	@PatchMapping(APIConstants.RECRUITING_MANAGER_UPDATE_WORKFLOW)
	public String updateWorkFlow(@PathVariable Long workflowId, @RequestBody WorkFlowDto workFlowDto) {
		WorkFlow workflow = workFlowService.getWorkFlowById(workflowId);
		workflow.setStatus(workFlowDto.getStatus());
		if(workFlowDto.getReason()!= null) {
			workflow.setReason(workFlowDto.getReason());	
		}
		workFlowService.updateWorkFlow(workflow);
		return MessageConstants.WORKFLOW_APPROVAL_STATUS_SUCCESS;
	}
	
	@PatchMapping(APIConstants.UPDATE_WORKFLOW_STATUS_AS_PENDING_FOR_RECRUITMENT_PROCESS)
	public String updateWorkFlowAsPendingForRecruitmentProcess(@PathVariable Long mrfId) {
		workFlowService.updateWorkFlowAsPendingForRecruitmentProcess(mrfId);
		return MessageConstants.WORKFLOW_STATUS_UPDATED;
	}
	
	@PatchMapping(APIConstants.UPDATE_WORKFLOW_STATUS_AS_PENDING_FOR_APPROVAL_PROCESS)
	public String updateWorkFlowAsPendingForApprovalProcess(@PathVariable Long mrfId) {
		workFlowService.updateWorkFlowAsPendingForApprovalProcess(mrfId);
		return MessageConstants.WORKFLOW_STATUS_UPDATED;
	}
}
