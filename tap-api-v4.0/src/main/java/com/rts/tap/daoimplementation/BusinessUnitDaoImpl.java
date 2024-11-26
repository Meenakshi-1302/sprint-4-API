package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class BusinessUnitDaoImpl implements BusinessUnitDao {

	
	private EntityManager entityManager;

	public BusinessUnitDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void save(BusinessUnit businessUnit) {
		entityManager.persist(businessUnit);
	}
	
	

	@Override
	public BusinessUnit findById(Long businessunitId) {
	    try {
	        // Fetch the BusinessUnit by its ID
	        return entityManager.find(BusinessUnit.class, businessunitId);
	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        return null; // Return null if there's an issue
	    }
	}

	
	@Override
	public BusinessUnit getBusinessUnitByLocation(String location) {
	    String hql = "FROM BusinessUnit bu WHERE bu.businessUnitLocation = :location";
	    TypedQuery<BusinessUnit> query = entityManager.createQuery(hql, BusinessUnit.class);
	    query.setParameter("location", location);
	    
	    // Using getResultList() to avoid NoResultException
	    List<BusinessUnit> results = query.getResultList();
	    
	    // Return the first result if available or null if not found
	    return results.isEmpty() ? null : results.get(0);
	}
	
	@Override
    public List<BusinessUnit> findAll() {
        TypedQuery<BusinessUnit> query = entityManager.createQuery(
                "SELECT b FROM BusinessUnit b", 
                BusinessUnit.class);
        return query.getResultList();  // Fetch all business units
    }

	

	
}
	


