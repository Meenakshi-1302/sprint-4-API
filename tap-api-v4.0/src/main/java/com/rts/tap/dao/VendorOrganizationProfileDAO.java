package com.rts.tap.dao;



import com.rts.tap.dto.VendorDto;
import com.rts.tap.dto.VendorProfileDto;
import com.rts.tap.model.Vendor;

public interface VendorOrganizationProfileDAO {
	Vendor findVendorOrganizationByVendorId(Long vendorId);
	Vendor updateVendorOrganizationLogo(Vendor vendor);
	public Vendor updateVendorById(Long vendorId, VendorProfileDto vendor);
	void updateVendor(Vendor vendor);
	Vendor findVendorById(Long vendorId);
	 
}
