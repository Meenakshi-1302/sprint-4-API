package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.Organization;

public interface OrganizationDao {

	void save(Organization organization);
	List<Organization> getAllOrganization();
	void update(Organization organization);
	
}

