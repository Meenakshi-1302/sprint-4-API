package com.rts.tap.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.OrganizationLocation;
import com.rts.tap.service.OrganizationLocationService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL) 
public class OrganizationLocationController {

    private final OrganizationLocationService organizationLocationService;

    // Constructor-based Dependency Injection
    public OrganizationLocationController(OrganizationLocationService organizationLocationService) {
        this.organizationLocationService = organizationLocationService;
    }

    // Create a new OrganizationLocation
    @PostMapping(APIConstants.ADD_ORG_LOCATION_URL)
    public ResponseEntity<String> addOrganizationLocation(@RequestBody OrganizationLocation organizationLocation) {
        try {
            organizationLocationService.addOrganizationLocation(organizationLocation);
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.ORG_LOCATION_CREATED_SUCCESS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(MessageConstants.ORG_LOCATION_CREATION_FAILED + ": " + e.getMessage());
        }
    }

    @GetMapping(APIConstants.GET_ALL_ORG_LOCATIONS_URL)
    public ResponseEntity<List<OrganizationLocation>> getAllOrganizationLocations() {
        try {
            List<OrganizationLocation> locations = organizationLocationService.getAllOrganizationLocations();
            if (locations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(locations);
            }
            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // You could also return a specific error message in a response body
        }
    }

    // Get OrganizationLocation by ID
    @GetMapping(APIConstants.GET_ORG_LOCATION_BY_ID_URL)
    public ResponseEntity<Object> getOrganizationLocationById(@PathVariable Long id) {
        try {
            OrganizationLocation location = organizationLocationService.getOrganizationLocationById(id);
            if (location != null) {
                return ResponseEntity.ok(location);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(MessageConstants.ORG_LOCATION_NOT_FOUND + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(MessageConstants.INTERNAL_SERVER_ERROR + ": " + e.getMessage());
        }
    }

    // Update an existing OrganizationLocation by ID
    @PutMapping(APIConstants.UPDATE_ORG_LOCATION_URL)
    public ResponseEntity<String> updateOrganizationLocation(@PathVariable Long id, @RequestBody OrganizationLocation organizationLocation) {
        try {
            organizationLocation.setLocationId(id); // Ensure ID is set for update
            organizationLocationService.updateOrganizationLocation(organizationLocation);
            return ResponseEntity.ok(MessageConstants.ORG_LOCATION_UPDATED_SUCCESS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(MessageConstants.ORG_LOCATION_UPDATE_FAILED + ": " + e.getMessage());
        }
    }
}