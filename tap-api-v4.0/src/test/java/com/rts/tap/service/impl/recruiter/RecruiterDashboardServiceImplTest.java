package com.rts.tap.service.impl.recruiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
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

import com.rts.tap.dao.RecruiterDashboardDao;
import com.rts.tap.model.MRF;
import com.rts.tap.serviceimplementation.RecruiterDashboardServiceImpl;

public class RecruiterDashboardServiceImplTest {

    @Mock
    private RecruiterDashboardDao recruiterDao;

    @InjectMocks
    private RecruiterDashboardServiceImpl recruiterDashboardService;

    private MRF mrf;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mrf = new MRF();
        

//         mrf.setId(1L);
//         mrf.setTitle("Sample MRF");
    }

    @Test
    public void testTotalMrfAssignedToRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 5L;

        when(recruiterDao.TotalMrfAssignedToRecruiter(employeeId)).thenReturn(expectedCount);

        Long resultCount = recruiterDashboardService.TotalMrfAssignedToRecruiter(employeeId);

        assertNotNull(resultCount);
        assertEquals(expectedCount, resultCount);
        verify(recruiterDao, times(1)).TotalMrfAssignedToRecruiter(employeeId);
    }

    @Test
    public void testGetMrfListAssignedToRecruiter() {
        Long employeeId = 1L;
        List<MRF> expectedMrfList = new ArrayList<>();
        expectedMrfList.add(mrf);

        when(recruiterDao.getMrfListAssignedToRecruiter(employeeId)).thenReturn(expectedMrfList);

        List<MRF> result = recruiterDashboardService.getMrfListAssignedToRecruiter(employeeId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mrf, result.get(0));
        verify(recruiterDao, times(1)).getMrfListAssignedToRecruiter(employeeId);
    }

    @Test
    public void testResolvedMrfAssignedToRecruiter() {
        Long employeeId = 1L;
        Long expectedResolvedCount = 3L;

        when(recruiterDao.ResolvedMrfAssignedToRecruiter(employeeId)).thenReturn(expectedResolvedCount);

        Long resultCount = recruiterDashboardService.ResolvedMrfAssignedToRecruiter(employeeId);

        assertNotNull(resultCount);
        assertEquals(expectedResolvedCount, resultCount);
        verify(recruiterDao, times(1)).ResolvedMrfAssignedToRecruiter(employeeId);
    }

    @Test
    public void testPendingMrfAssignedToRecruiter() {
        Long employeeId = 1L;
        Long expectedPendingCount = 2L;

        when(recruiterDao.PendingMrfAssignedToRecruiter(employeeId)).thenReturn(expectedPendingCount);

        Long resultCount = recruiterDashboardService.PendingMrfAssignedToRecruiter(employeeId);

        assertNotNull(resultCount);
        assertEquals(expectedPendingCount, resultCount);
        verify(recruiterDao, times(1)).PendingMrfAssignedToRecruiter(employeeId);
    }

    @Test
    public void testTotalCandidatesOfRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 10L;

        when(recruiterDao.TotalCandidatesofRecruiter(employeeId)).thenReturn(expectedCount);

        Long resultCount = recruiterDashboardService.TotalCandidatesofRecruiter(employeeId);

        assertNotNull(resultCount);
        assertEquals(expectedCount, resultCount);
        verify(recruiterDao, times(1)).TotalCandidatesofRecruiter(employeeId);
    }

    @Test
    public void testHiredCandidatesOfRecruiter() {
        Long employeeId = 1L;
        Long expectedHiredCount = 4L;

        when(recruiterDao.HiredCandidatesofRecruiter(employeeId)).thenReturn(expectedHiredCount);

        Long resultCount = recruiterDashboardService.HiredCandidatesofRecruiter(employeeId);

        assertNotNull(resultCount);
        assertEquals(expectedHiredCount, resultCount);
        verify(recruiterDao, times(1)).HiredCandidatesofRecruiter(employeeId);
    }

    @Test
    public void testPendingCandidatesOfRecruiter() {
        Long employeeId = 1L;
        Long expectedPendingCount = 6L;

        when(recruiterDao.PendingCandidatesofRecruiter(employeeId)).thenReturn(expectedPendingCount);

        Long resultCount = recruiterDashboardService.PendingCandidatesofRecruiter(employeeId);

        assertNotNull(resultCount);
        assertEquals(expectedPendingCount, resultCount);
        verify(recruiterDao, times(1)).PendingCandidatesofRecruiter(employeeId);
    }
}