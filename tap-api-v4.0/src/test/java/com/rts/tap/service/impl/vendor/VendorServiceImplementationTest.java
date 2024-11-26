package com.rts.tap.service.impl.vendor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rts.tap.dao.VendorDao;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.exception.VendorNotFoundException;
import com.rts.tap.model.Vendor;
import com.rts.tap.serviceimplementation.VendorServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplementationTest {

	@Mock
	private VendorDao vendorDao;

	@InjectMocks
	private VendorServiceImplementation vendorService;

	private VendorDto testVendorDto;
	private Vendor testVendor;

	@BeforeEach
	void setUp() {
		// Setup test VendorDto
		testVendorDto = new VendorDto();
		testVendorDto.setVendorId(1L);
		testVendorDto.setOrganizationName("Test Organization");
		testVendorDto.setEmail("test@example.com");
		testVendorDto.setContactName("John Doe");
		testVendorDto.setContactNumber("1234567890");
		testVendorDto.setAddress("Test Address");
		testVendorDto.setWebsiteUrl("http://test.com");
		testVendorDto.setTaxIdentifyNumber("TAX123");
		testVendorDto.setUsername("testuser");
		testVendorDto.setPassword("password123");
		testVendorDto.setIsPasswordChanged("N");
		testVendor = new Vendor();
		testVendor.setVendorId(1L);
		testVendor.setOrganizationName("Test Organization");
	}

	@Disabled
	@Test
	void testGetVendorById_Success() {
		// Given
		when(vendorDao.findById(1L)).thenReturn(testVendor);

		// When
		VendorDto result = vendorService.getVendorById(1L);

		// Then
		assertNotNull(result);
		assertEquals(testVendor.getOrganizationName(), result.getOrganizationName());
	}

	@Disabled
	@Test
	void testGetVendorById_NotFound() {
		// Given
		when(vendorDao.findById(999L)).thenReturn(null);

		// Then
		assertThrows(VendorNotFoundException.class, () -> {
			vendorService.getVendorById(999L);
		});
	}

	@Disabled
	@Test
	void testGetAllVendors() {
		// Given
		List<Vendor> vendors = Arrays.asList(testVendor);
		when(vendorDao.findAllVendor()).thenReturn(vendors);

		// When
		List<VendorDto> results = vendorService.getAllVendors();

		// Then
		assertNotNull(results);
		assertEquals(1, results.size());
		assertEquals(testVendor.getOrganizationName(), results.get(0).getOrganizationName());
	}

	@Disabled
	@Test
	void testUpdateVendor_Success() {
		// Given
		when(vendorDao.findById(1L)).thenReturn(testVendor);
		when(vendorDao.updateVendor(1L, testVendorDto)).thenReturn(testVendor);

		// When
		Vendor result = vendorService.updateVendor(1L, testVendorDto);

		// Then
		assertNotNull(result);
		assertEquals(testVendor.getVendorId(), result.getVendorId());
		verify(vendorDao).updateVendor(1L, testVendorDto);
	}

	@Disabled
	@Test
	void testUpdateVendor_NotFound() {
		// Given
		when(vendorDao.findById(999L)).thenReturn(null);

		// Then
		assertThrows(VendorNotFoundException.class, () -> {
			vendorService.updateVendor(999L, testVendorDto);
		});
	}
	
	@Disabled
	@Test
    void testGenerateVendorUsername() {
        // When
        String username = vendorService.generateVendorUsername("Test Organization");

        // Then
        assertNotNull(username);
        assertTrue(username.startsWith("tes"));
        assertEquals(8, username.length());
    }
	
	@Disabled
	@Test
    void testGenerateVendorUsername_ShortName() {
        // When
        String username = vendorService.generateVendorUsername("AB");

        // Then
        assertNotNull(username);
        assertTrue(username.startsWith("ab"));
        assertEquals(7, username.length());
    }
	
	@Disabled
	@Test
    void testGenerateStrongPassword() {
        // When
        String password = vendorService.generateStrongPassword();

        // Then
        assertNotNull(password);
        assertEquals(12, password.length());
    }
}
