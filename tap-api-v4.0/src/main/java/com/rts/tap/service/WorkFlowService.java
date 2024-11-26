package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.WorkFlow;

public interface WorkFlowService {

	WorkFlow getWorkflowByMrfIdForRecruitmentProcess(Long mrfId);
	
	public List<WorkFlow> getWorkFlowByEmployeeId(Long employeeId);
	
	void updateWorkFlow(WorkFlow workflow);
	
	WorkFlow getWorkFlowById(Long workFlowId);

	void updateWorkFlowAsPendingForRecruitmentProcess(Long mrfId);

	void updateWorkFlowAsPendingForApprovalProcess(Long mrfId);

}
