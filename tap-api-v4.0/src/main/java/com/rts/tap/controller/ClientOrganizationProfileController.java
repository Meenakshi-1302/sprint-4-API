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
import com.rts.tap.service.ClientOrganizationProfileService;

@RestController
@CrossOrigin(origins = APIConstants.BASE_URL)
@RequestMapping(path = APIConstants.GET_CLIENT_URL)
public class ClientOrganizationProfileController {

	private ClientOrganizationProfileService clientOrganizationProfileService;

	public ClientOrganizationProfileController(ClientOrganizationProfileService clientOrganizationProfileService) {
		super();
		this.clientOrganizationProfileService = clientOrganizationProfileService;
	}
	
	@PatchMapping(path = APIConstants.UPDATE_CLIENT_ORGANIZATION_LOGO_BY_ID)
	public ResponseEntity<String> updateClientOrganizationLogo(@PathVariable("clientId") Long clientId, @RequestParam("organizationLogo") MultipartFile organizationLogo) { 
		String response = clientOrganizationProfileService.updateOrganizationLogo(clientId, organizationLogo);
		if (response.equals("Organization logo updated successfully!")) { 
			return ResponseEntity.ok(response);
		} else { 
			return ResponseEntity.badRequest().body(response); 
		}
	}
}
