package com.rts.tap.serviceimplementation;

import com.rts.tap.dao.OrganizationLocationDao;  // Assuming you have a DAO for data access
import com.rts.tap.model.OrganizationLocation;
import com.rts.tap.service.OrganizationLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Marking the class as a Spring Service bean
public class OrganizationLocationServiceImpl implements OrganizationLocationService {

    private final OrganizationLocationDao organizationLocationDao;  // DAO for data access

    public OrganizationLocationServiceImpl(OrganizationLocationDao organizationLocationDao) {
        this.organizationLocationDao = organizationLocationDao;  // Dependency Injection
    }

    @Override
    public void addOrganizationLocation(OrganizationLocation organizationLocation) {
        // Save the OrganizationLocation to the database
        organizationLocationDao.save(organizationLocation);
    }

    @Override
    public List<OrganizationLocation> getAllOrganizationLocations() {
        // Retrieve all OrganizationLocations from the database
        return organizationLocationDao.findAll();
    }

    @Override
    public OrganizationLocation getOrganizationLocationById(Long id) {
        // Retrieve an OrganizationLocation by its ID
        Optional<OrganizationLocation> organizationLocation = organizationLocationDao.findById(id);
        return organizationLocation.orElse(null);  // Return the found location or null if not found
    }

    @Override
    public void updateOrganizationLocation(OrganizationLocation organizationLocation) {
        // Update an existing OrganizationLocation
        organizationLocationDao.save(organizationLocation);  // save will update if the entity already exists
    }
}
