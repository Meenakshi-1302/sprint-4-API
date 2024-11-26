package com.rts.tap.service.impl.recruitingmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.RecruitingManagerDao;
import com.rts.tap.dto.MRFRecruiterDto;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.dto.RecruitingManagerAddCandidateDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruiters;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.serviceimplementation.RecruitingManagerServiceImplementation;

import jakarta.mail.MessagingException;

public class RecruitingManagerServiceImplementationTest {

	@Mock
	private RecruitingManagerDao recruitingManagerDao;

	@InjectMocks
	private RecruitingManagerServiceImplementation recruitingManagerService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Mock
	private MultipartFile candidateResume;	

	@Disabled
	@Test
	public void testGetAllMrfsAssignedForRM() {
		// Given
		long id = 1L;
		MRFRecruitingManager mrf1 = new MRFRecruitingManager();
		MRFRecruitingManager mrf2 = new MRFRecruitingManager();
		List<MRFRecruitingManager> expectedMrfs = Arrays.asList(mrf1, mrf2);
		when(recruitingManagerDao.getAllMrfsAssignedForRM(id)).thenReturn(expectedMrfs);

		// When
		List<MRFRecruitingManager> result = recruitingManagerService.getAllMrfsAssignedForRM(id);

		// Then
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(recruitingManagerDao, times(1)).getAllMrfsAssignedForRM(id);
	}

	@Disabled
	@Test
	public void testGetMrfById() {
		// Given
		long id = 1L;
		MRF expectedMrf = new MRF();
		expectedMrf.setMrfId(id);
		when(recruitingManagerDao.getMrfById(id)).thenReturn(expectedMrf);

		// When
		MRF result = recruitingManagerService.getMrfById(id);

		// Then
		assertNotNull(result);
		assertEquals(id, result.getMrfId());
		verify(recruitingManagerDao, times(1)).getMrfById(id);
	}

	@Disabled
	@Test
	public void testMrfAssignToVendor() {
		// Given
		MRFVendorDto mrfVendorDto = new MRFVendorDto();
		when(recruitingManagerDao.assignMrfToVendor(mrfVendorDto)).thenReturn("Success");

		// When
		String result = recruitingManagerService.mrfAssignToVendor(mrfVendorDto);

		// Then
		assertEquals("Success", result);
		verify(recruitingManagerDao, times(1)).assignMrfToVendor(mrfVendorDto);
	}

	@Disabled
	@Test
	public void testMrfAssignToRecruiter() {
		// Given
		MRFRecruiterDto mrfRecruiterDto = new MRFRecruiterDto();
		mrfRecruiterDto.setCreatedAt(new Date());
		mrfRecruiterDto.setUpdatedAt(new Date());
		when(recruitingManagerDao.assignMrfToRecruiter(mrfRecruiterDto)).thenReturn("Success");

		// When
		String result = recruitingManagerService.mrfAssignToRecruiter(mrfRecruiterDto);

		// Then
		assertEquals("Success", result);
		verify(recruitingManagerDao, times(1)).assignMrfToRecruiter(mrfRecruiterDto);
	}

	@Disabled
	@Test
	public void testGetAllAssignedMrfRecruiterRecords() {
		// Given
		MRFRecruiters mrfRecruiters1 = new MRFRecruiters();
		MRFRecruiters mrfRecruiters2 = new MRFRecruiters();
		List<MRFRecruiters> recruiters = Arrays.asList(mrfRecruiters1, mrfRecruiters2);
		when(recruitingManagerDao.getAllRecruitersAssigned()).thenReturn(recruiters);

		// When
		List<MRFRecruiters> result = recruitingManagerService.getAllAssignedMrfRecruiterRecords();

		// Then
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(recruitingManagerDao, times(1)).getAllRecruitersAssigned();
	}

	@Disabled
	@Test
	public void testUpdateMrfRecruiter() {
		// Given
		long id = 1L;
		MRFRecruiterDto mrfRecruiterDto = new MRFRecruiterDto();
		when(recruitingManagerDao.updateMrfRecruiter(mrfRecruiterDto, id)).thenReturn("Success");

		// When
		String result = recruitingManagerService.updateMrfRecruiter(mrfRecruiterDto, id);

		// Then
		assertEquals("Success", result);
		verify(recruitingManagerDao, times(1)).updateMrfRecruiter(mrfRecruiterDto, id);
	}

	@Disabled
	@Test
	public void testGetAllRecruitersByRecruitingManagerID() {
		// Given
		long id = 1L;
		Employee recruiter1 = new Employee();
		Employee recruiter2 = new Employee();
		List<Employee> recruiters = Arrays.asList(recruiter1, recruiter2);
		when(recruitingManagerDao.getAllRecruitersByRecruitingManagerId(id)).thenReturn(recruiters);

		// When
		List<Employee> result = recruitingManagerService.getAllRecruitersByRecruitingManagerID(id);

		// Then
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(recruitingManagerDao, times(1)).getAllRecruitersByRecruitingManagerId(id);
	}

	@Disabled
	@Test
	public void testGetAllVendorsAssignedForMRFbyMrfId() {
		// Given
		long mrfId = 1L;
		MRFVendor vendor1 = new MRFVendor();
		MRFVendor vendor2 = new MRFVendor();
		List<MRFVendor> vendors = Arrays.asList(vendor1, vendor2);
		when(recruitingManagerDao.getAllVendorsAssignedForMrf(mrfId)).thenReturn(vendors);

		// When
		List<MRFVendor> result = recruitingManagerService.getAllVendorsAssignedForMRFbyMrfId(mrfId);

		// Then
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(recruitingManagerDao, times(1)).getAllVendorsAssignedForMrf(mrfId);
	}

	@Test
	public void testAddNewCandidate_Success() throws IOException, MessagingException {
		// Given
		RecruitingManagerAddCandidateDto dto = new RecruitingManagerAddCandidateDto();
		dto.setFirstName("John");
		dto.setLastName("Doe");
		dto.setMobileNumber("1234567890");
		dto.setEmail("john.doe@example.com");
		dto.setExperience(5);
		dto.setResume("Resume Details");
		dto.setSourceId(101L);
		dto.setSkill("Java");
		dto.setLocation("New York");
		dto.setPanNumber("ABCDE1234F");
		dto.setStatus("Active");
		dto.setIsPasswordChanged(true);

		byte[] resumeBytes = new byte[] { 1, 2, 3 }; // Mock resume byte data
		when(candidateResume.getBytes()).thenReturn(resumeBytes);

		// Mocking the DAO response
		when(recruitingManagerDao.addCandidate(any(Candidate.class))).thenReturn("Success");

		// When
		String result = recruitingManagerService.addNewCandidate(dto, candidateResume);

		// Then
		assertNotNull(result);
		assertEquals("Success", result);

		// Verify that the addCandidate method was called once with the expected
		// Candidate object
		verify(recruitingManagerDao, times(1)).addCandidate(any(Candidate.class));
	}

	@Test
	public void testAddNewCandidate_InvalidResume() throws IOException, MessagingException {
		// Given
		RecruitingManagerAddCandidateDto dto = new RecruitingManagerAddCandidateDto();
		dto.setFirstName("John");
		dto.setLastName("Doe");
		dto.setMobileNumber("1234567890");
		dto.setEmail("john.doe@example.com");
		dto.setExperience(5);
		dto.setResume("Resume Details");
		dto.setSourceId(101L);
		dto.setSkill("Java");
		dto.setLocation("New York");
		dto.setPanNumber("ABCDE1234F");
		dto.setStatus("Active");
		dto.setIsPasswordChanged(true);

		// Simulating an invalid resume (null)
		when(candidateResume.getBytes()).thenThrow(new IOException("Error reading resume"));

		// When & Then
		try {
			recruitingManagerService.addNewCandidate(dto, candidateResume);
		} catch (IOException e) {
			assertEquals("Error reading resume", e.getMessage());
		}

		// Ensure that the DAO method was not called due to the IOException
		verify(recruitingManagerDao, times(0)).addCandidate(any(Candidate.class));
	}

	@Test
	public void testAddNewCandidate_MissingFields() throws IOException, MessagingException {
		// Given
		RecruitingManagerAddCandidateDto dto = new RecruitingManagerAddCandidateDto();
		dto.setFirstName("John");
		dto.setLastName(""); // Missing last name
		dto.setMobileNumber("1234567890");
		dto.setEmail("john.doe@example.com");
		dto.setExperience(5);
		dto.setResume("Resume Details");
		dto.setSourceId(101L);
		dto.setSkill("Java");
		dto.setLocation("New York");
		dto.setPanNumber("ABCDE1234F");
		dto.setStatus("Active");
		dto.setIsPasswordChanged(true);

		byte[] resumeBytes = new byte[] { 1, 2, 3 }; // Mock resume byte data
		when(candidateResume.getBytes()).thenReturn(resumeBytes);

		// Mocking the DAO response
		when(recruitingManagerDao.addCandidate(any(Candidate.class))).thenReturn("Success");

		// When
		String result = recruitingManagerService.addNewCandidate(dto, candidateResume);

		// Then
		assertNotNull(result);
		assertEquals("Success", result);

		// Verify that the addCandidate method was called once with the expected
		// Candidate object
		verify(recruitingManagerDao, times(1)).addCandidate(any(Candidate.class));
	}
}