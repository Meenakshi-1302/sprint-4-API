package com.rts.tap.service;

import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Employee;
import com.rts.tap.model.WorkFlow;

import java.util.List;

public interface ApproverLevelService {
	public void saveApproverLevel(List<ApproverLevel> approverLevel);
	public void updateApproverLevel(ApproverLevel approverLevel);
	public void deleteApproverLevel(long approverLevelId);
	public List<ApproverLevel> getApproverLevelByMrfId(Long mrfId);
	public WorkFlow getWorkFlowByMrfId(Long mrfId);
	public Employee getEmployeeByEmployeeId(Long employeeId);
	public void saveSingleApproverLevel(ApproverLevel approverLevel);
}
