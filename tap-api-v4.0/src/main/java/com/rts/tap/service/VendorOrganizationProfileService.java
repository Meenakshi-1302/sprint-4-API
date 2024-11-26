package com.rts.tap.service;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dto.VendorDto;
import com.rts.tap.dto.VendorProfileDto;
import com.rts.tap.model.Vendor;

public interface VendorOrganizationProfileService {
	String updateOrganizationLogo(Long vendorId,MultipartFile vendorOrganizationLogo);
    Vendor updateVendorPartial(Long vendorId, String address, String contactName, String contactNumber, String websiteUrl);
}
