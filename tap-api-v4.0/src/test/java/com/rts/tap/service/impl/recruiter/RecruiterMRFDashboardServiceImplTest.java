package com.rts.tap.service.impl.recruiter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.rts.tap.dao.RecruiterMRFDashboardDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;
import com.rts.tap.serviceimplementation.RecruiterMRFDashboardServiceImpl;

class RecruiterMRFDashboardServiceImplTest {

    @Mock
    private RecruiterMRFDashboardDao recruiterMRFDashboardDao;

    @InjectMocks
    private RecruiterMRFDashboardServiceImpl recruiterMRFDashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCandidateByMrfId() {
        long mrfId = 1L;
        List<Candidate> expectedCandidates = new ArrayList<>();
        expectedCandidates.add(new Candidate()); // Add actual candidates as needed

        when(recruiterMRFDashboardDao.getCandidateByMrfId(mrfId)).thenReturn(expectedCandidates);

        List<Candidate> actualCandidates = recruiterMRFDashboardService.getCandidateByMrfId(mrfId);

        assertEquals(expectedCandidates, actualCandidates);
        verify(recruiterMRFDashboardDao, times(1)).getCandidateByMrfId(mrfId);
    }

    @Test
    void testGetOfferByMrfId() {
        long mrfId = 1L;
        List<Offer> expectedOffers = new ArrayList<>();
        expectedOffers.add(new Offer()); // Add actual offers as needed

        when(recruiterMRFDashboardDao.getOfferByMrfId(mrfId)).thenReturn(expectedOffers);

        List<Offer> actualOffers = recruiterMRFDashboardService.getOfferByMrfId(mrfId);

        assertEquals(expectedOffers, actualOffers);
        verify(recruiterMRFDashboardDao, times(1)).getOfferByMrfId(mrfId);
    }


    @Test
    void testGetRemainingDays_ClosureDatePassed() {
        long mrfId = 1L;
        Date[] contractDates = new Date[2];
        contractDates[0] = new Date(System.currentTimeMillis() - 10000000); // Start date
        contractDates[1] = new Date(System.currentTimeMillis() - 86400000); // Closure date (1 day ago)

        when(recruiterMRFDashboardDao.getContractDates(mrfId)).thenReturn(contractDates);

        Long remainingDays = recruiterMRFDashboardService.getRemainingDays(mrfId);

        assertEquals(0L, remainingDays); // Verify that remaining days are 0
        verify(recruiterMRFDashboardDao, times(1)).getContractDates(mrfId);
    }

    @Test
    void testGetRemainingDays_NoContractDates() {
        long mrfId = 2L;

        when(recruiterMRFDashboardDao.getContractDates(mrfId)).thenReturn(null);

        Long remainingDays = recruiterMRFDashboardService.getRemainingDays(mrfId);

        assertEquals(0L, remainingDays); // Verify that remaining days are 0
        verify(recruiterMRFDashboardDao, times(1)).getContractDates(mrfId);
    }
}