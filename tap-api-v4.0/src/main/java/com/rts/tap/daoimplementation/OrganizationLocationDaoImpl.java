package com.rts.tap.daoimplementation;

import com.rts.tap.dao.OrganizationLocationDao;
import com.rts.tap.model.OrganizationLocation;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository 
@Transactional 
public class OrganizationLocationDaoImpl implements OrganizationLocationDao {

   
    public OrganizationLocationDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	private EntityManager entityManager;

    @Override
    public void save(OrganizationLocation organizationLocation) {
        if (organizationLocation.getLocationId() == null) {
            // If it's a new organization location, persist it
            entityManager.persist(organizationLocation);
        } else {
            // If the location ID is not null, update the existing location
            entityManager.merge(organizationLocation);
        }
    }

    @Override
    public List<OrganizationLocation> findAll() {
        // Querying all organization locations from the database
        String hql = "FROM OrganizationLocation";  // HQL (Hibernate Query Language)
        return entityManager.createQuery(hql, OrganizationLocation.class).getResultList();
    }

    @Override
    public Optional<OrganizationLocation> findById(Long id) {
        // Fetching an organization location by its ID
        OrganizationLocation organizationLocation = entityManager.find(OrganizationLocation.class, id);
        return Optional.ofNullable(organizationLocation);  // Return wrapped in Optional
    }
}
