//package com.rts.tap.client.requirement;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.rts.tap.dao.RequirementDAO;
//import com.rts.tap.dto.RequirementDTO;
//import com.rts.tap.serviceimplementation.RequirementServiceImp;
//
//class RequirementTest {
//
//	@InjectMocks
//	private RequirementServiceImp requirementService;
//
//	@Mock
//	private RequirementDAO requirementDAO;
//
package com.rts.tap.client.requirement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

import com.rts.tap.dao.RequirementDAO;
import com.rts.tap.serviceimplementation.RequirementServiceImp;

class RequirementTest {

	@InjectMocks
	private RequirementServiceImp requirementService;

	@Mock
	private RequirementDAO requirementDAO;
	
}

//	private RequirementDTO requirementDTO;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//		requirementDTO = new RequirementDTO();
//		requirementDTO.setRequirementId(1L);
//		requirementDTO.setRequiredResourceCount(5);
//		requirementDTO.setProbableDesignation("Software Engineer");
//		requirementDTO.setRequiredSkills("Java, Spring");
//		requirementDTO.setRequiredExperience("3 years");
//		requirementDTO.setJobLocation("Remote");
//		requirementDTO.setTimeline("1 month");
//		requirementDTO.setMinimumBudget(10000);
//		requirementDTO.setMaximumBudget(20000);
//		requirementDTO.setClientId(1L);
//	}
//
//	@Test
//	void createRequirement_success() {
//		when(requirementDAO.addRequirement(any(RequirementDTO.class), any(Long.class)))
//				.thenReturn("Requirement added successfully");
//
//		String response = requirementService.createRequirement(requirementDTO, 1L);
//
//		assertEquals("Requirement added successfully", response);
//		verify(requirementDAO, times(1)).addRequirement(requirementDTO, 1L);
//	}
//
//	@Test
//	void changeRequirement_success() {
//		when(requirementDAO.updateRequirement(any(RequirementDTO.class)))
//				.thenReturn("Requirement updated successfully");
//
//		String response = requirementService.changeRequirement(requirementDTO);
//
//		assertEquals("Requirement updated successfully", response);
//		verify(requirementDAO, times(1)).updateRequirement(null);
//		verify(requirementDAO, times(1)).updateRequirement(requirementDTO);
//	}
//
//	@Test
//	void removeRequirement_success() {
//		when(requirementDAO.deleteRequirement(any(Long.class))).thenReturn("Requirement deleted successfully");
//
//		String response = requirementService.removeRequirement(1L);
//
//		assertEquals("Requirement deleted successfully", response);
//		verify(requirementDAO, times(1)).deleteRequirement(1L);
//	}
//
////	@Test
////	void findAllRequirements_success() {
////		List<RequirementDTO> requirementList = new ArrayList<>();
////		requirementList.add(requirementDTO);
////		when(requirementDAO.getAllRequirements()).thenReturn(requirementList);
////
////		List<RequirementDTO> response = requirementService.findAllRequirements();
////
////		assertNotNull(response);
////		assertEquals(1, response.size());
////		assertEquals(requirementDTO, response.get(0));
////		verify(requirementDAO, times(1)).getAllRequirements();
////	}
//
//	@Test
//	void RequirementsByClient_success() {
//		List<RequirementDTO> requirementList = new ArrayList<>();
//		requirementList.add(requirementDTO);
//		when(requirementDAO.getAllRequirementsByClient(any(Long.class))).thenReturn(requirementList);
//
//		List<RequirementDTO> response = requirementService.RequirementsByClient(1L);
//
//		assertNotNull(response);
//		assertEquals(1, response.size());
//		assertEquals(requirementDTO, response.get(0));
//		verify(requirementDAO, times(1)).getAllRequirementsByClient(1L);
//	}
//}
//

