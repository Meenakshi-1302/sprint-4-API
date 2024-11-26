package com.rts.tap.service.impl.recruiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.ApproverLevelDao;
import com.rts.tap.dao.EmployeeDao;
import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.WorkFlowDao;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.WorkFlow;
import com.rts.tap.serviceimplementation.ApproverLevelSeviceImpl;

public class ApproverLevelSeviceImplTest {

    @Mock
    private ApproverLevelDao approverLevelDao;

    @Mock
    private WorkFlowDao workflowDao;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private MRFDao mrfDao;

    @InjectMocks
    private ApproverLevelSeviceImpl approverLevelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveApproverLevel() {
        ApproverLevel approverLevel = new ApproverLevel();
        MRF mrf = new MRF();
        mrf.setMrfId(1L);
        approverLevel.setMrf(mrf);
        List<ApproverLevel> approverLevels = Arrays.asList(approverLevel);

        when(mrfDao.findById(1L)).thenReturn(mrf);
        Employee buHead = new Employee();
        when(employeeDao.getEmployeeById(anyLong())).thenReturn(buHead);

        approverLevelService.saveApproverLevel(approverLevels);

        verify(approverLevelDao, times(1)).saveApproverLevel(approverLevels);
        verify(workflowDao, times(1)).addWorkFlow(any(WorkFlow.class));
    }

    @Test
    public void testUpdateApproverLevel() {
        ApproverLevel approverLevel = new ApproverLevel();
        approverLevelService.updateApproverLevel(approverLevel);
        verify(approverLevelDao, times(1)).updateApproverLevel(approverLevel);
    }

    @Test
    public void testDeleteApproverLevel() {
        ApproverLevel approverLevel = new ApproverLevel();
        MRF mrf = new MRF();
        mrf.setMrfId(1L);
        approverLevel.setMrf(mrf);
        approverLevel.setLevel(1);
        when(approverLevelDao.findApproverLevelById(anyLong())).thenReturn(approverLevel);
        when(approverLevelDao.getApproverLevelByMrfId(anyLong())).thenReturn(Arrays.asList(approverLevel));
        WorkFlow workFlow = new WorkFlow();
        workFlow.setCount(1);
        when(workflowDao.findWorkFlowByMrf(any(MRF.class))).thenReturn(workFlow);

        approverLevelService.deleteApproverLevel(1L);

        verify(approverLevelDao, times(1)).deleteApproverLevel(1L);
        verify(workflowDao, times(1)).updateWorkFlow(any(WorkFlow.class));
    }

    @Test
    public void testGetApproverLevelByMrfId() {
        List<ApproverLevel> approverLevels = Arrays.asList(new ApproverLevel());
        when(approverLevelDao.getApproverLevelByMrfId(anyLong())).thenReturn(approverLevels);

        List<ApproverLevel> result = approverLevelService.getApproverLevelByMrfId(1L);

        assertEquals(approverLevels, result);
    }

    @Test
    public void testGetWorkFlowByMrfId() {
        WorkFlow workFlow = new WorkFlow();
        when(workflowDao.getWorkFlowApproverLevelByMrfId(anyLong())).thenReturn(workFlow);

        WorkFlow result = approverLevelService.getWorkFlowByMrfId(1L);

        assertEquals(workFlow, result);
    }

    @Test
    public void testGetEmployeeByEmployeeId() {
        Employee employee = new Employee();
        when(workflowDao.getEmployeeByEmployeeId(anyLong())).thenReturn(employee);

        Employee result = approverLevelService.getEmployeeByEmployeeId(1L);

        assertEquals(employee, result);
    }
}
