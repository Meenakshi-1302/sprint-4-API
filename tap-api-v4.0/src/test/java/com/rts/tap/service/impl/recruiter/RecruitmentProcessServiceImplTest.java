package com.rts.tap.service.impl.recruiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.rts.tap.dao.AssessmentDao;
import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.InterviewerDao;
import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.RecruitmentProcessDao;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.dto.RecruitmentProcessDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.RecruitmentProcess;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.serviceimplementation.RecruitmentProcessServiceImpl;

public class RecruitmentProcessServiceImplTest {

	@InjectMocks
	private RecruitmentProcessServiceImpl recruitmentProcessService;

	@Mock
	private RecruitmentProcessDao recruitmentProcessDao;

	@Mock
	private MRFDao mrfDao;

	@Mock
	private EmployeeDao employeeDao;

	@Mock
	private WorkFlowDao workFlowDao;

	@Mock
	private InterviewerDao interviewerDao;

	@Mock
	private AssessmentDao assessmentDao;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSetRecruitmentProcess() {
	    RecruitmentProcessDto dto = new RecruitmentProcessDto();
	    RecruitmentProcess rp = new RecruitmentProcess();
	    rp.setRecruitmentProcessType("interview");
	    rp.setRecruitmentProcessName("Technical Interview");
	    rp.setInterviewer(null);
	    rp.setLevel(1);
	    rp.setMrf(null);
	    
	    List<RecruitmentProcess> recruitmentProcesses = new ArrayList<>();
	    recruitmentProcesses.add(rp);
	    dto.setRecruitmentProcesses(recruitmentProcesses);
	    dto.setRecruiterManagerId(1L);

	    MRF mrf = new MRF();
	    mrf.setMrfId(1L);

	    when(mrfDao.findById(anyLong())).thenReturn(mrf);
	    Employee employee = new Employee();
	    employee.setEmployeeId(1L);
	    when(employeeDao.getEmployeeById(anyLong())).thenReturn(employee);
	    when(workFlowDao.findWorkFlowByMrf(any())).thenReturn(new WorkFlow());
	}

	@Test
	void testUpdateRecruitmentProcessLevel() {
		RecruitmentProcess recruitmentProcess = new RecruitmentProcess();
		recruitmentProcessService.updateRecruitmentProcessLevel(recruitmentProcess);
		verify(recruitmentProcessDao, times(1)).updateRecruitmentProcess(recruitmentProcess);
	}

	@Test
	public void testDeleteRecruitmentProcessLevel() {
		RecruitmentProcess rp = new RecruitmentProcess();
		rp.setRecruitmentProcessId(1L);
		rp.setMrf(new MRF());

		when(recruitmentProcessDao.findById(anyLong())).thenReturn(rp);
		when(recruitmentProcessDao.findRecruitmentProcessByMrfId(anyLong())).thenReturn(new ArrayList<>());
	}

	@Test
	public void testGetRecruitmentProcessLevels() {
		Long mrfId = 1L;
		List<RecruitmentProcess> recruitmentProcesses = new ArrayList<>();

		when(recruitmentProcessDao.findRecruitmentProcessByMrfId(mrfId)).thenReturn(recruitmentProcesses);

		List<RecruitmentProcess> result = recruitmentProcessService.getRecruitmentProcessLevels(mrfId);

		assertEquals(recruitmentProcesses, result);
	}

	@Test
	public void testInsertRecruitmentProcessLevel() {
		RecruitmentProcess rp = new RecruitmentProcess();
		rp.setRecruitmentProcessType("interview");
		rp.setMrf(new MRF());

		when(mrfDao.findById(anyLong())).thenReturn(rp.getMrf());
		when(employeeDao.getEmployeeById(anyLong())).thenReturn(new Employee());
		when(recruitmentProcessDao.findRecruitmentProcessByMrfId(anyLong())).thenReturn(new ArrayList<>());
	}
}