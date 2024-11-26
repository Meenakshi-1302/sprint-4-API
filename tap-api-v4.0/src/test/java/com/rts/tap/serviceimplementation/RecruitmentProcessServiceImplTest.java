package com.rts.tap.serviceimplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.*;
import com.rts.tap.dto.RecruitmentProcessDto;
import com.rts.tap.model.*;

import java.util.ArrayList;
import java.util.List;

class RecruitmentProcessServiceImplTest {

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

    @Mock
    private ClientDao clientDao;

    @InjectMocks
    private RecruitmentProcessServiceImpl recruitmentProcessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetRecruitmentProcess() {
        RecruitmentProcessDto dto = new RecruitmentProcessDto();
        List<RecruitmentProcess> recruitmentProcesses = new ArrayList<>();
        RecruitmentProcess recruitmentProcess = new RecruitmentProcess();
        recruitmentProcess.setRecruitmentProcessType("interview");

        recruitmentProcesses.add(recruitmentProcess);
        dto.setRecruitmentProcesses(recruitmentProcesses);
        MRF mrf = new MRF();
        mrf.setMrfId(1L);
        
        when(mrfDao.findById(anyLong())).thenReturn(mrf);
        when(employeeDao.getEmployeeById(anyLong())).thenReturn(new Employee());
        when(workFlowDao.findWorkFlowForRecruitmentProcessByMrf(any(MRF.class))).thenReturn(new WorkFlow());
    }

    @Test
    void testUpdateRecruitmentProcessLevel() {
        RecruitmentProcess recruitmentProcess = new RecruitmentProcess();
        recruitmentProcess.setRecruitmentProcessType("interview");
        recruitmentProcess.setInterviewer(new ArrayList<>());
        
        WorkFlow workflow = new WorkFlow();
        when(workFlowDao.findWorkFlowForRecruitmentProcessByMrf(any(MRF.class))).thenReturn(workflow);

        recruitmentProcessService.updateRecruitmentProcessLevel(recruitmentProcess);
    }

    @Test
    void testDeleteRecruitmentProcessLevel() {
        Long recruitmentProcessId = 1L;
        RecruitmentProcess recruitmentProcess = new RecruitmentProcess();
        recruitmentProcess.setMrf(new MRF());
        recruitmentProcess.getMrf().setMrfId(1L);

        when(recruitmentProcessDao.findById(recruitmentProcessId)).thenReturn(recruitmentProcess);
        when(recruitmentProcessDao.findRecruitmentProcessByMrfId(anyLong())).thenReturn(new ArrayList<>());
        when(workFlowDao.findWorkFlowForRecruitmentProcessByMrf(any(MRF.class))).thenReturn(new WorkFlow());
    }

    @Test
    void testGetRecruitmentProcessLevels() {
        Long mrfId = 1L;
        List<RecruitmentProcess> expectedList = new ArrayList<>();
        expectedList.add(new RecruitmentProcess());

        when(recruitmentProcessDao.findRecruitmentProcessByMrfId(mrfId)).thenReturn(expectedList);

        List<RecruitmentProcess> actualList = recruitmentProcessService.getRecruitmentProcessLevels(mrfId);
        
        assertEquals(expectedList.size(), actualList.size());
        verify(recruitmentProcessDao, times(1)).findRecruitmentProcessByMrfId(mrfId);
    }

    @Test
    void testInsertRecruitmentProcessLevel() {
        RecruitmentProcess recruitmentProcess = new RecruitmentProcess();
        recruitmentProcess.setRecruitmentProcessType("interview");
        
        MRF mrf = new MRF();
        mrf.setMrfId(1L);
        recruitmentProcess.setMrf(mrf);
        
        when(mrfDao.findById(anyLong())).thenReturn(mrf);
        when(employeeDao.getEmployeeById(anyLong())).thenReturn(new Employee());
    }

    @Test
    void testGetCandidateByRpId() {
        Long rpId = 1L;
        List<Candidate> expectedCandidates = new ArrayList<>();
        expectedCandidates.add(new Candidate());

        when(recruitmentProcessDao.findCandidateByRpId(rpId)).thenReturn(expectedCandidates);

        List<Candidate> actualCandidates = recruitmentProcessService.getCandidateByRpId(rpId);
        
        assertEquals(expectedCandidates.size(), actualCandidates.size());
        verify(recruitmentProcessDao, times(1)).findCandidateByRpId(rpId);
    }
}