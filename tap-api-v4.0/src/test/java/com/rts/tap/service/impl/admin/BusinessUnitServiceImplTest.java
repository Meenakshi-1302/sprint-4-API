package com.rts.tap.service.impl.admin;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.serviceimplementation.BusinessUnitServiceImpl;

class BusinessUnitServiceImplTest {

    @InjectMocks
    private BusinessUnitServiceImpl businessUnitService;

    @Mock
    private BusinessUnitDao businessUnitDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBusinessUnit() {
        // Arrange
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessunitId(1L); // Assuming BusinessUnit has an ID field
        businessUnit.setBusinessUnitName("Research and Development"); // Set other fields as necessary

        // Act
        businessUnitService.addBusinessUnit(businessUnit);

        // Assert
        verify(businessUnitDao, times(1)).save(businessUnit); // Verify that save was called once with the businessUnit object
    }
}