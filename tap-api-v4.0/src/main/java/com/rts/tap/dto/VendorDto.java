package com.rts.tap.dto;





/** 
 * author: Jeevarajan, Vashanth 
 * version: v1.0
 * updated at: 04-11-2024
**/

public class VendorDto {
	private Long vendorId;
	private String organizationName;
	private String username;
	private String contactName;
	private String contactNumber;
	private String address;
	private String websiteUrl;
	private String taxIdentifyNumber;
	private String email;
	private String password;
	private String isPasswordChanged;
	private String role;
	private byte[] vendorOrganizationLogo;

	public VendorDto() {
		super();
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}


	public String getIsPasswordChanged() {
		return isPasswordChanged;
	}


	public void setIsPasswordChanged(String isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}


	public Long getVendorid() {
		return vendorId;
	}

	public void setVendorid(Long vendorid) {
		this.vendorId = vendorid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getTaxIdentifyNumber() {
		return taxIdentifyNumber;
	}

	public void setTaxIdentifyNumber(String taxIdentifyNumber) {
		this.taxIdentifyNumber = taxIdentifyNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getVendorOrganizationLogo() {
		return vendorOrganizationLogo;
	}

	public void setVendorOrganizationLogo(byte[] vendorOrganizationLogo) {
		this.vendorOrganizationLogo = vendorOrganizationLogo;
	}
	
}
