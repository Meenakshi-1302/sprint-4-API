package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.service.WorkFlowService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WorkFlowServiceImpl implements WorkFlowService {
	
	WorkFlowDao workFlowDao;
	EmployeeDao employeeDao;
	MRFDao mrfDao;

	public WorkFlowServiceImpl(WorkFlowDao workFlowDao, EmployeeDao employeeDao, MRFDao mrfDao) {
		super();
		this.workFlowDao = workFlowDao;
		this.employeeDao = employeeDao;
		this.mrfDao = mrfDao;
	}

	@Override
	public WorkFlow getWorkflowByMrfIdForRecruitmentProcess(Long mrfId) {
		MRF mrf = mrfDao.findById(mrfId);
		return workFlowDao.findWorkFlowForRecruitmentProcessByMrf(mrf);
	}
	
	@Override
	public List<WorkFlow> getWorkFlowByEmployeeId(Long employeeId) {
	    Employee employee = employeeDao.getEmployeeById(employeeId);
	    List<WorkFlow> workFlows = (List<WorkFlow>) workFlowDao.findWorkFlowForRecruitmentProcessByEmployee(employee);
	    return workFlows;
	}
	
	@Override
	public void updateWorkFlow(WorkFlow workflow) {
		workFlowDao.updateWorkFlow(workflow);
		
	}

	@Override
	public WorkFlow getWorkFlowById(Long workFlowId) {
		return workFlowDao.getWorkFlowById(workFlowId);
	}

	@Override
	public void updateWorkFlowAsPendingForRecruitmentProcess(Long mrfId) {
		WorkFlow workFlow = workFlowDao.findWorkFlowForRecruitmentProcessByMrf(mrfDao.findById(mrfId));
		workFlow.setStatus(MessageConstants.SET_WORKFLOW_STATUS_AS_PENDING);
		workFlowDao.updateWorkFlow(workFlow);
	}

	@Override
	public void updateWorkFlowAsPendingForApprovalProcess(Long mrfId) {
		WorkFlow workFlow = workFlowDao.getWorkFlowApproverLevelByMrfId(mrfId);
		workFlow.setStatus(MessageConstants.SET_WORKFLOW_STATUS_AS_PENDING);
		workFlowDao.updateWorkFlow(workFlow);
	}

}
