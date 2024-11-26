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
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.service.BusinessUnitService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)

public class BusinessUnitController {

	@Autowired
	private BusinessUnitService businessUnitService;
	

	@PostMapping(APIConstants.ADD_BUSINESSUNIT_URL)
	public String addBusinessUnit(@RequestBody BusinessUnit businessUnit) {
		try {	       
			System.out.println(businessUnit.getBusinessUnitName());
			businessUnitService.addBusinessUnit(businessUnit);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return MessageConstants.FAILURE_MESSAGE;
		}
	}
	
	
	 @GetMapping(APIConstants.GET_BUSINESSUNIT_BY_LOCATION_URL)
	    public BusinessUnit getBusinessUnitByLocation(@PathVariable String location) {
	        return businessUnitService.getBusinessUnitByLocation(location);
	 }
	 
	 
	 @GetMapping(APIConstants.GETALL_BUSINESSUNIT_URL)  // Define this in your constants
	    public List<BusinessUnit> getAllBusinessUnits() {
	        return businessUnitService.getAllBusinessUnits();

	    }
	 @PutMapping(APIConstants.UPDATE_BUSINESSUNIT_URL) // Define this in your constants
		public String updateBusinessUnit(@PathVariable Long id, @RequestBody BusinessUnit businessUnit) {
			try {
				// Update the business unit using the service layer
				businessUnitService.updateBusinessUnit(id, businessUnit);
				return MessageConstants.SUCCESS_MESSAGE;
			} catch (Exception e) {
				return MessageConstants.FAILURE_MESSAGE;
			}
		}
    
}
