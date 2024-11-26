package com.rts.tap.dto;

public class VendorProfileDto {

	private String address;

	private String contactNumber;

	private String websiteUrl;

	private String contactName;

	public VendorProfileDto() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Override
	public String toString() {
		return "VendorProfileDto [address=" + address + ", contactNumber=" + contactNumber + ", websiteUrl="
				+ websiteUrl + ", contactName=" + contactName + "]";
	}

}
