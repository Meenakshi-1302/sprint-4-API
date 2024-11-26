package com.rts.tap.dto;

public class WorkFlowDto {

	private Long workflowId;

	private String workFlowType;

	private int count;

	private long employeeId;

	private long mrfId;

	private String status;

	private String reason;

	public WorkFlowDto() {
		super();
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkFlowType() {
		return workFlowType;
	}

	public void setWorkFlowType(String workFlowType) {
		this.workFlowType = workFlowType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getMrfId() {
		return mrfId;
	}

	public void setMrfId(long mrfId) {
		this.mrfId = mrfId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}	
}
