package com.rts.tap.serviceimplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.RecruiterDashboardDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;

class RecruiterDashboardServiceImplTest {

    @Mock
    private RecruiterDashboardDao recruiterDao;

    @InjectMocks
    private RecruiterDashboardServiceImpl recruiterDashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTotalMrfAssignedToRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 5L;

        when(recruiterDao.TotalMrfAssignedToRecruiter(employeeId)).thenReturn(expectedCount);

        Long actualCount = recruiterDashboardService.TotalMrfAssignedToRecruiter(employeeId);
        assertEquals(expectedCount, actualCount);
        verify(recruiterDao, times(1)).TotalMrfAssignedToRecruiter(employeeId);
    }

    @Test
    void testGetMrfListAssignedToRecruiter() {
        Long employeeId = 1L;
        List<MRF> expectedMrfList = new ArrayList<>();
        MRF mrf = new MRF();
        mrf.setMrfId(1L);
        expectedMrfList.add(mrf);

        when(recruiterDao.getMrfListAssignedToRecruiter(employeeId)).thenReturn(expectedMrfList);

        List<MRF> actualMrfList = recruiterDashboardService.getMrfListAssignedToRecruiter(employeeId);
        assertEquals(expectedMrfList.size(), actualMrfList.size());
        assertEquals(expectedMrfList.get(0).getMrfId(), actualMrfList.get(0).getMrfId());
        verify(recruiterDao, times(1)).getMrfListAssignedToRecruiter(employeeId);
    }

    @Test
    void testResolvedMrfAssignedToRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 3L;

        when(recruiterDao.ResolvedMrfAssignedToRecruiter(employeeId)).thenReturn(expectedCount);

        Long actualCount = recruiterDashboardService.ResolvedMrfAssignedToRecruiter(employeeId);
        assertEquals(expectedCount, actualCount);
        verify(recruiterDao, times(1)).ResolvedMrfAssignedToRecruiter(employeeId);
    }

    @Test
    void testPendingMrfAssignedToRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 2L;

        when(recruiterDao.PendingMrfAssignedToRecruiter(employeeId)).thenReturn(expectedCount);

        Long actualCount = recruiterDashboardService.PendingMrfAssignedToRecruiter(employeeId);
        assertEquals(expectedCount, actualCount);
        verify(recruiterDao, times(1)).PendingMrfAssignedToRecruiter(employeeId);
    }

    @Test
    void testTotalCandidatesofRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 10L;

        when(recruiterDao.TotalCandidatesofRecruiter(employeeId)).thenReturn(expectedCount);

        Long actualCount = recruiterDashboardService.TotalCandidatesofRecruiter(employeeId);
        assertEquals(expectedCount, actualCount);
        verify(recruiterDao, times(1)).TotalCandidatesofRecruiter(employeeId);
    }

    @Test
    void testHiredCandidatesofRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 6L;

        when(recruiterDao.HiredCandidatesofRecruiter(employeeId)).thenReturn(expectedCount);

        Long actualCount = recruiterDashboardService.HiredCandidatesofRecruiter(employeeId);
        assertEquals(expectedCount, actualCount);
        verify(recruiterDao, times(1)).HiredCandidatesofRecruiter(employeeId);
    }

    @Test
    void testPendingCandidatesofRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 4L;

        when(recruiterDao.PendingCandidatesofRecruiter(employeeId)).thenReturn(expectedCount);

        Long actualCount = recruiterDashboardService.PendingCandidatesofRecruiter(employeeId);
        assertEquals(expectedCount, actualCount);
        verify(recruiterDao, times(1)).PendingCandidatesofRecruiter(employeeId);
    }

    @Test
    void testGetCandidateByEmployeeId() {
        Long employeeId = 1L;
        List<Candidate> expectedCandidates = new ArrayList<>();
        Candidate candidate = new Candidate();
        candidate.setCandidateId(1L);
        expectedCandidates.add(candidate);

        when(recruiterDao.getCandidateByEmployeeId(employeeId)).thenReturn(expectedCandidates);

        List<Candidate> actualCandidates = recruiterDashboardService.getCandidateByEmployeeId(employeeId);
        assertEquals(expectedCandidates.size(), actualCandidates.size());
        assertEquals(expectedCandidates.get(0).getCandidateId(), actualCandidates.get(0).getCandidateId());
        verify(recruiterDao, times(1)).getCandidateByEmployeeId(employeeId);
    }

    @Test
    void testGetOfferByEmployeeId() {
        Long employeeId = 1L;
        List<Offer> expectedOffers = new ArrayList<>();
        Offer offer = new Offer();
        offer.setOfferId(1L);
        expectedOffers.add(offer);

        when(recruiterDao.getOfferByEmployeeId(employeeId)).thenReturn(expectedOffers);

        List<Offer> actualOffers = recruiterDashboardService.getOfferByEmployeeId(employeeId);
        assertEquals(expectedOffers.size(), actualOffers.size());
        assertEquals(expectedOffers.get(0).getOfferId(), actualOffers.get(0).getOfferId());
        verify(recruiterDao, times(1)).getOfferByEmployeeId(employeeId);
    }

    @Test
    void testGetResolvedMRFCountByMonth() {
        Long employeeId = 1L;
        int year = 2023;
        List<Object[]> expectedCount = new ArrayList<>();
        expectedCount.add(new Object[] { 1, 2 });
        when(recruiterDao.getResolvedMRFCountByMonth(employeeId, year)).thenReturn(expectedCount);

        List<Object[]> actualCount = recruiterDashboardService.getResolvedMRFCountByMonth(employeeId, year);
        assertEquals(expectedCount.size(), actualCount.size());
        verify(recruiterDao, times(1)).getResolvedMRFCountByMonth(employeeId, year);
    }

    @Test
    void testGetMonthwiseHiredCountForResolvedMRF() {
        Long employeeId = 1L;
        int year = 2023;
        List<Object[]> expectedCount = new ArrayList<>();
        expectedCount.add(new Object[] { 1, 3 });
        when(recruiterDao.getMonthwiseHiredCountForResolvedMRF(employeeId, year)).thenReturn(expectedCount);

        List<Object[]> actualCount = recruiterDashboardService.getMonthwiseHiredCountForResolvedMRF(employeeId, year);
        assertEquals(expectedCount.size(), actualCount.size());
        verify(recruiterDao, times(1)).getMonthwiseHiredCountForResolvedMRF(employeeId, year);
    }

    @Test
    void testRejectedCandidatesofRecruiter() {
        Long employeeId = 1L;
        Long expectedCount = 1L;

        when(recruiterDao.RejectedCandidatesofRecruiter(employeeId)).thenReturn(expectedCount);

        Long actualCount = recruiterDashboardService.RejectedCandidatesofRecruiter(employeeId);
        assertEquals(expectedCount, actualCount);
        verify(recruiterDao, times(1)).RejectedCandidatesofRecruiter(employeeId);
    }

    @Test
    void testGetPendingMRFCountByMonth() {
        Long employeeId = 1L;
        int year = 2023;
        List<Object[]> expectedCount = new ArrayList<>();
        expectedCount.add(new Object[] { 1, 5 });
        when(recruiterDao.getPendingMRFCountByMonth(employeeId, year)).thenReturn(expectedCount);

        List<Object[]> actualCount = recruiterDashboardService.getPendingMRFCountByMonth(employeeId, year);
        assertEquals(expectedCount.size(), actualCount.size());
        verify(recruiterDao, times(1)).getPendingMRFCountByMonth(employeeId, year);
    }
}