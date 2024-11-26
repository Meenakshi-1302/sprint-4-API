package com.rts.tap.serviceimplementation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.VendorDao;
import com.rts.tap.dao.VendorOrganizationProfileDAO;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.dto.VendorProfileDto;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.VendorOrganizationProfileService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VendorOrganizationLogoServiceImpl implements VendorOrganizationProfileService {

	private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png");

	private VendorOrganizationProfileDAO vendorOrganizationProfileDAO;
	private VendorDao vendorDao;

	public VendorOrganizationLogoServiceImpl(VendorOrganizationProfileDAO vendorOrganizationProfileDAO) {
		super();
		this.vendorOrganizationProfileDAO = vendorOrganizationProfileDAO;

	}

	@Override
	public String updateOrganizationLogo(Long vendorId, MultipartFile vendorOrganizationLogo) {

		if (vendorOrganizationLogo == null || vendorOrganizationLogo.isEmpty()) {
			return " Vendor Organization logo is missing";
		}
		if (vendorOrganizationLogo.getSize() > 153600) { // 150 KB in bytes
			return "File size exceeds the limit of 150 KB.";
		}
		if (!ALLOWED_CONTENT_TYPES.contains(vendorOrganizationLogo.getContentType())) {
			return "Invalid file type. Only JPG, JPEG, and PNG files are allowed.";
		}
		try {
			Vendor vendor = vendorOrganizationProfileDAO.findVendorOrganizationByVendorId(vendorId);
			if (vendor == null) {
				return "Vendor organization not found";
			}
			byte[] logoBytes = vendorOrganizationLogo.getBytes();
			vendor.setVendorOrganizationLogo(logoBytes);
			vendorOrganizationProfileDAO.updateVendorOrganizationLogo(vendor);
			return "Vendor Organization logo updated successfully!";
		} catch (IOException e) {
			return "Error updating organization logo: " + e.getMessage();
		}

	}

	@Override
    public Vendor updateVendorPartial(Long vendorId, String address, String contactName, String contactNumber, String websiteUrl) {
        Vendor vendor = vendorOrganizationProfileDAO.findVendorById(vendorId);
        if (vendor == null) {
            return null; // Vendor not found
        }

        // Update the fields if new values are provided
        if (address != null) {
            vendor.setAddress(address);
        }
        if (contactName != null) {
            vendor.setContactName(contactName);
        }
        if (contactNumber != null) {
            vendor.setContactNumber(contactNumber);
        }
        if (websiteUrl != null) {
            vendor.setWebsiteUrl(websiteUrl);
        }

        vendorOrganizationProfileDAO.updateVendor(vendor); // Save changes to the database
        return vendor;
    }

}
