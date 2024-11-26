package com.rts.tap.dto;

public class LoginResponse {

	private Long id;
	private String status;
	private String role;
	private Long employeeId;
	private String isPasswordChange;

	public LoginResponse(String status) {
		super();
		this.status = status;
	}

	public String getIsPasswordChange() {
		return isPasswordChange;
	}

	public void setIsPasswordChange(String isPasswordChange) {
		this.isPasswordChange = isPasswordChange;
	}

	private Long clientId;
	private Long candidateId;
	private Long vendorId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public LoginResponse(Long id, String status, String role, Long employeeId) {
		super();
		this.id = id;
		this.status = status;
		this.role = role;
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "LoginResponse [id=" + id + ", status=" + status + ", role=" + role + ", employeeId=" + employeeId + "]";
	}

	public LoginResponse(Long id, String status, String role) {
		super();
		this.id = id;
		this.status = status;
		this.role = role;
	}

	public LoginResponse(Long id, String status, String role, Long employeeId, String isPasswordChange) {
		super();
		this.id = id;
		this.status = status;
		this.role = role;
		this.employeeId = employeeId;
		this.isPasswordChange = isPasswordChange;
	}

	public LoginResponse(String status, Long vendorId, String role) {
		this.status = status;
		this.vendorId = vendorId;
		this.role = role;

	}

	public LoginResponse(Long candidateId) {
		this.candidateId = candidateId;
	}

//	public void ClientResponse(Long id, Long clientId, String role) {
//		this.id = id;
//		this.clientId = clientId;
//		this.role = role;
//
//	}

}
