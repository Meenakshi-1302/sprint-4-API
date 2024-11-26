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

import com.rts.tap.dao.CandidateDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.serviceimplementation.CandidateServiceImpl;

public class CandidateServiceImplTest {
    
    @Mock
    private CandidateDao candidateDao;
    
    @InjectMocks
    private CandidateServiceImpl candidateService;
    
    private Candidate candidate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        candidate = new Candidate();
        candidate.setCandidateId(1L);
        candidate.setFirstName("Nandha");
        candidate.setLastName("kumaran");
        // Set other required fields...
    }

    @Test
    public void testSaveCandidate() {
        doNothing().when(candidateDao).save(any(Candidate.class));

        candidateService.save(candidate);

        verify(candidateDao, times(1)).save(candidate);
    }

    @Test
    public void testFindAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate);
        when(candidateDao.findAll()).thenReturn(candidates);

        List<Candidate> result = candidateService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(candidateDao, times(1)).findAll();
    }

    @Test
    public void testFindCandidateById() {
        when(candidateDao.findById(anyLong())).thenReturn(candidate);

        Candidate found = candidateService.findById(1L);

        assertNotNull(found);
        assertEquals(candidate.getCandidateId(), found.getCandidateId());
        verify(candidateDao, times(1)).findById(1L);
    }

    @Test
    public void testUpdateCandidate() {
        doNothing().when(candidateDao).update(any(Candidate.class));

        candidateService.update(candidate);

        verify(candidateDao, times(1)).update(candidate);
    }

    @Test
    public void testDeleteCandidate() {
        doNothing().when(candidateDao).delete(anyLong());

        candidateService.delete(1L);

        verify(candidateDao, times(1)).delete(1L);
    }
}