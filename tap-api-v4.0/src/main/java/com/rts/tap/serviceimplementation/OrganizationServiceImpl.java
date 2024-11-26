package com.rts.tap.serviceimplementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.OrganizationDao;
import com.rts.tap.model.Organization;
import com.rts.tap.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationDao repo;

	public void addOrganization(Organization organization) {
		repo.save(organization);
	}

	@Override
	public List<Organization> getAllOrganization() {
		return repo.getAllOrganization();
		 
	}

	@Override
    public void updateOrganization(Organization organization) {
        repo.update(organization); 
    }

}
