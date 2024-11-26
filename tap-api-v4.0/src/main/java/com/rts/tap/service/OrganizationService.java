package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.Organization;

public interface OrganizationService {
	
	void addOrganization(Organization organization);
	List<Organization> getAllOrganization();
	void updateOrganization(Organization organization);
     
}
