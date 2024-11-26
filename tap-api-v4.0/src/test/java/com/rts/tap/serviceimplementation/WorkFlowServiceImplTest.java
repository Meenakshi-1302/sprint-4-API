package com.rts.tap.serviceimplementation;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.WorkFlow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WorkFlowServiceImplTest {

	@InjectMocks
	private WorkFlowServiceImpl workFlowService;

	@Mock
	private WorkFlowDao workFlowDao;

	@Mock
	private EmployeeDao employeeDao;

	@Mock
	private MRFDao mrfDao;

	private MRF sampleMrf;
	private Employee sampleEmployee;
	private WorkFlow sampleWorkFlow;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		sampleMrf = new MRF();
		sampleMrf.setMrfId(1L);

		sampleEmployee = new Employee();
		sampleEmployee.setEmployeeId(1L);
		sampleEmployee.setEmployeeEmail("vignes@gmail.com");

		sampleWorkFlow = new WorkFlow();
		sampleWorkFlow.setWorkflowId(1L);
		sampleWorkFlow.setWorkFlowType("Recruitment Process");
	}

	@Disabled
	@Test
	public void testGetWorkflowByMrfIdForRecruitmentProcess() {
		when(mrfDao.findById(1L)).thenReturn(sampleMrf);
		when(workFlowDao.findWorkFlowForRecruitmentProcessByMrf(sampleMrf)).thenReturn(sampleWorkFlow);

		WorkFlow result = workFlowService.getWorkflowByMrfIdForRecruitmentProcess(1L);

		assertEquals(sampleWorkFlow, result);
		verify(mrfDao).findById(1L);
		verify(workFlowDao).findWorkFlowForRecruitmentProcessByMrf(sampleMrf);
	}

	@Disabled
	@Test
	public void testGetWorkFlowByEmployeeId() {
		when(employeeDao.getEmployeeById(1L)).thenReturn(sampleEmployee);
		List<WorkFlow> workFlows = Arrays.asList(sampleWorkFlow);
		when(workFlowDao.findWorkFlowForRecruitmentProcessByEmployee(sampleEmployee)).thenReturn(workFlows);

		List<WorkFlow> result = workFlowService.getWorkFlowByEmployeeId(1L);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(sampleWorkFlow, result.get(0));
		verify(employeeDao).getEmployeeById(1L);
		verify(workFlowDao).findWorkFlowForRecruitmentProcessByEmployee(sampleEmployee);
	}

	@Disabled
	@Test
	public void testUpdateWorkFlow() {
		workFlowService.updateWorkFlow(sampleWorkFlow);

		verify(workFlowDao).updateWorkFlow(sampleWorkFlow);
	}

	@Disabled
	@Test
	public void testGetWorkFlowById() {
		when(workFlowDao.getWorkFlowById(1L)).thenReturn(sampleWorkFlow);

		WorkFlow result = workFlowService.getWorkFlowById(1L);

		assertEquals(sampleWorkFlow, result);
		verify(workFlowDao).getWorkFlowById(1L);
	}
}