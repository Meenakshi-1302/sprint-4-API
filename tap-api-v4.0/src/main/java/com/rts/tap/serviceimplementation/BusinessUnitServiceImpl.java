package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.service.BusinessUnitService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

    @Autowired
    BusinessUnitDao businessUnitDao;

	public void addBusinessUnit(BusinessUnit businessUnit) {
		businessUnitDao.save(businessUnit);
	}


    @Override
    public BusinessUnit getBusinessUnitByLocation(String workLocation) {
        return businessUnitDao.getBusinessUnitByLocation(workLocation);  // This method is defined in the DAO/repository
    }
    


   


    @Override
    public BusinessUnit getBusinessUnitById(Long businessunitId) {
        // Use the repository to find BusinessUnit by ID (directly return the result)
        return businessUnitDao.findById(businessunitId);  // No need for Optional handling if the DAO returns null

    }





    @Override
    public List<BusinessUnit> getAllBusinessUnits() {
        return businessUnitDao.findAll();  // Fetch all business units from the DAO

    }



	@Override
	public void updateBusinessUnit(Long id, BusinessUnit updatedBusinessUnit) {
	    // Retrieve the business unit, or return null if not found
	    BusinessUnit existingBusinessUnit = businessUnitDao.findById(id);

	    // If found, update the business unit
	    if (existingBusinessUnit != null) {
	        existingBusinessUnit.setBusinessUnitName(updatedBusinessUnit.getBusinessUnitName());
	        existingBusinessUnit.setDescription(updatedBusinessUnit.getDescription());
	        existingBusinessUnit.setBusinessUnitLocation(updatedBusinessUnit.getBusinessUnitLocation());
	        businessUnitDao.save(existingBusinessUnit);
	    } else {
	        // Handle the case where the BusinessUnit was not found
	        // For example, throw an exception or return an error message
	        throw new RuntimeException("Business unit not found with id " + id);
	    }
	}



}
