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

import com.rts.tap.dao.InterviewDao;
import com.rts.tap.model.Interview;

class InterviewServiceImplTest {

    @Mock
    private InterviewDao interviewDao;

    @InjectMocks
    private InterviewServiceImpl interviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCandidatesByRecruitmentProcessId() {
        Long recruitmentProcessId = 1L;
        List<Interview> expectedInterviews = new ArrayList<>();
        Interview interview = new Interview();
        interview.setInterviewId(1L);
        expectedInterviews.add(interview);

        when(interviewDao.getCandidatesByRecruitmentProcessId(recruitmentProcessId)).thenReturn(expectedInterviews);

        List<Interview> actualInterviews = interviewService.getCandidatesByRecruitmentProcessId(recruitmentProcessId);

        assertEquals(expectedInterviews.size(), actualInterviews.size());
        assertEquals(expectedInterviews.get(0).getInterviewId(), actualInterviews.get(0).getInterviewId());
        verify(interviewDao, times(1)).getCandidatesByRecruitmentProcessId(recruitmentProcessId);
    }

    @Test
    void testGetCandidatesRecruitmentProcessByCandidateIdAndMrfId() {
        Long candidateId = 1L;
        Long mrfId = 1L;
        List<Interview> expectedInterviews = new ArrayList<>();
        Interview interview = new Interview();
        interview.setInterviewId(2L);
        expectedInterviews.add(interview);

        when(interviewDao.getCandidatesRecruitmentProcessByCandidateIdAndMrfId(candidateId, mrfId)).thenReturn(expectedInterviews);

        List<Interview> actualInterviews = interviewService.getCandidatesRecruitmentProcessByCandidateIdAndMrfId(candidateId, mrfId);

        assertEquals(expectedInterviews.size(), actualInterviews.size());
        assertEquals(expectedInterviews.get(0).getInterviewId(), actualInterviews.get(0).getInterviewId());
        verify(interviewDao, times(1)).getCandidatesRecruitmentProcessByCandidateIdAndMrfId(candidateId, mrfId);
    }
}