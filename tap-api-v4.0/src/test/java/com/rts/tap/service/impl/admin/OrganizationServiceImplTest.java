package com.rts.tap.service.impl.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.OrganizationDao;
import com.rts.tap.model.Organization;
import com.rts.tap.serviceimplementation.OrganizationServiceImpl;

class OrganizationServiceImplTest {

    @Mock
    private OrganizationDao organizationDao;

    @InjectMocks
    private OrganizationServiceImpl organizationService;

    private Organization organization;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("Test Organization");
        organization.setOrganizationAddress("123 Test St");
        organization.setContactPersonName("John Doe");
        organization.setContactPersonEmail("john.doe@example.com");
        organization.setContactPersonPhone("1234567890");
        organization.setOrganizationWebsiteUrl("http://test.org");
        // Set other fields as necessary...
    }

    @Test
    void testAddOrganization() {
        // Call the addOrganization method
        organizationService.addOrganization(organization);

        // Verify that the save method was called on the organizationDao
        verify(organizationDao, times(1)).save(organization);
    }

    @Test
    void testGetAllOrganization() {
        // Create a list of organizations to return from the mock
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        // Mock the behavior of the organizationDao
        when(organizationDao.getAllOrganization()).thenReturn(organizations);

        // Call the getAllOrganization method
        List<Organization> result = organizationService.getAllOrganization();

        // Verify that the getAllOrganization method was called on the organizationDao
        verify(organizationDao, times(1)).getAllOrganization();

        // Assert that the returned list is not null and contains the expected data
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Organization", result.get(0).getOrganizationName());
    }
}