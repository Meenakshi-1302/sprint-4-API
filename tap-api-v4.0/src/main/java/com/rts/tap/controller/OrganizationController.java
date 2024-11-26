package com.rts.tap.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Organization;
import com.rts.tap.service.OrganizationService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)

public class OrganizationController {

	
	private OrganizationService organizationService;
	
	public OrganizationController(OrganizationService organizationService) {
		super();
		this.organizationService = organizationService;
	}

	@PostMapping(APIConstants.ADD_ORGANIZATION_URL)
	public String addAdmin(@RequestBody Organization organization) {
		try {	        
			organizationService.addOrganization(organization);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return MessageConstants.FAILURE_MESSAGE;
		}
	}
	
	 @GetMapping(APIConstants.GETALL_ORGANIZATION_URL)
	    public List<Organization> viewAllOrganization() {
	        return organizationService.getAllOrganization();
	    }
	 
	 @PutMapping(APIConstants.UPDATE_ORGANIZATION_URL)
	    public String updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
	        try {
	            // Set the organization ID to ensure the correct entity is updated
	            organization.setOrganizationId(id);
	            organizationService.updateOrganization(organization);
	            return MessageConstants.SUCCESS_MESSAGE;
	        } catch (Exception e) {
	            return MessageConstants.FAILURE_MESSAGE;
	        }
	    }
    
}

