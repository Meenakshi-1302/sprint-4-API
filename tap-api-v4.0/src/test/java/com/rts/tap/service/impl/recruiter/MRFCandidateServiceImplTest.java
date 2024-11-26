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

import com.rts.tap.dao.MRFCandidateDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.serviceimplementation.MRFCandidateServiceImpl;

public class MRFCandidateServiceImplTest {
    
    @Mock
    private MRFCandidateDao mrfCandidateDao;
    
    @InjectMocks
    private MRFCandidateServiceImpl mrfCandidateService;
    
    private MRFCandidate mrfCandidate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mrfCandidate = new MRFCandidate();
        mrfCandidate.setMrfCandidateId(1L);
        mrfCandidate.setStatus("Pending");
    }

    @Test
    public void testGetAllCandidates() {
        List<MRFCandidate> candidates = new ArrayList<>();
        candidates.add(mrfCandidate);
        when(mrfCandidateDao.getAllCandidates()).thenReturn(candidates);

        List<MRFCandidate> result = mrfCandidateService.getAllCandidates();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mrfCandidateDao, times(1)).getAllCandidates();
    }

    @Test
    public void testGetCandidateById() {
        when(mrfCandidateDao.getCandidateById(anyLong())).thenReturn(mrfCandidate);

        MRFCandidate foundCandidate = mrfCandidateService.getCandidateById(1L);

        assertNotNull(foundCandidate);
        assertEquals(mrfCandidate.getMrfCandidateId(), foundCandidate.getMrfCandidateId());
        verify(mrfCandidateDao, times(1)).getCandidateById(1L);
    }

    @Test
    public void testSaveCandidate() {
        when(mrfCandidateDao.saveCandidate(any(MRFCandidate.class))).thenReturn(mrfCandidate);

        MRFCandidate savedCandidate = mrfCandidateService.saveCandidate(mrfCandidate);

        assertNotNull(savedCandidate);
        assertEquals(mrfCandidate.getMrfCandidateId(), savedCandidate.getMrfCandidateId());
        verify(mrfCandidateDao, times(1)).saveCandidate(mrfCandidate);
    }

    @Test
    public void testUpdateCandidate() {
        when(mrfCandidateDao.getCandidateById(anyLong())).thenReturn(mrfCandidate);
        when(mrfCandidateDao.saveCandidate(any(MRFCandidate.class))).thenReturn(mrfCandidate);

        MRFCandidate updatedCandidate = mrfCandidateService.updateCandidate(1L, mrfCandidate);

        assertNotNull(updatedCandidate);
        verify(mrfCandidateDao, times(1)).getCandidateById(1L);
        verify(mrfCandidateDao, times(1)).saveCandidate(mrfCandidate);
    }

    @Test
    public void testDeleteCandidate() {
        doNothing().when(mrfCandidateDao).deleteCandidate(anyLong());

        mrfCandidateService.deleteCandidate(1L);

        verify(mrfCandidateDao, times(1)).deleteCandidate(1L);
    }

    @Test
    public void testGetCandidateByScoreId() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate()); 
        when(mrfCandidateDao.getRemainingCandidates(anyLong())).thenReturn(candidates);

        List<Candidate> result = mrfCandidateService.getCandidateByScoreId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mrfCandidateDao, times(1)).getRemainingCandidates(1L);
    }
}