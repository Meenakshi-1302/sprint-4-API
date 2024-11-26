package com.rts.tap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.dto.VendorProfileDto;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.VendorOrganizationProfileService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = APIConstants.CROSS_ORIGIN_URL, allowCredentials = "true")
@RequestMapping(path = APIConstants.VENDOR_URL)
public class VendorProfileController {

	private VendorOrganizationProfileService vendorProfileService;

	public VendorProfileController(VendorOrganizationProfileService vendorProfileService) {
		super();
		this.vendorProfileService = vendorProfileService;
	}

	@PatchMapping(path = APIConstants.UPDATE_VENDOR_ORGANIZATION_LOGO_BY_ID)
	public ResponseEntity<String> updateVendorOrganizationLogo(@PathVariable("vendorId") Long vendorId,
			@RequestParam("vendorOrganizationLogo") MultipartFile vendorOrganizationLogo) {
		String response = vendorProfileService.updateOrganizationLogo(vendorId, vendorOrganizationLogo);
		if (response.equals("Vendor logo updated successfully!")) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body(response);
		}
	}



	
	@PatchMapping("/updatevendor/{vendorId}")
    public ResponseEntity<Vendor> updateVendorPartial(
            @PathVariable("vendorId") Long vendorId,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String contactName,
            @RequestParam(required = false) String contactNumber,
            @RequestParam(required = false) String websiteUrl) {

        Vendor vendor = vendorProfileService.updateVendorPartial(vendorId, address, contactName, contactNumber, websiteUrl);
        if (vendor != null) {
            return ResponseEntity.ok(vendor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
