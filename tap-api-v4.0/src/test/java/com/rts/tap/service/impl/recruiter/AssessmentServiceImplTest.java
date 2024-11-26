//package com.rts.tap.service.impl.recruiter;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//class AssessmentServiceImplTest {
//
//	@Test
//	void testAssessmentServiceImpl() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCreateAssessment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetAssessmentById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetAllAssessments() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteAssessment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetAssessmentByMrfId() {
//		fail("Not yet implemented");
//	}
//
//}


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

import com.rts.tap.dao.AssessmentDao;
import com.rts.tap.model.Assessment;
import com.rts.tap.serviceimplementation.AssessmentServiceImpl;

public class AssessmentServiceImplTest {
    
    @Mock
    private AssessmentDao assessmentDao;
    
    @InjectMocks
    private AssessmentServiceImpl assessmentService;
    
    private Assessment assessment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        assessment = new Assessment();
        assessment.setAssessmentId(1L);
        assessment.setAssessmentName("Test Assessment");
    }

    @Test
    public void testCreateAssessment() {
        when(assessmentDao.save(any(Assessment.class))).thenReturn(assessment);

        Assessment created = assessmentService.createAssessment(assessment);

        assertNotNull(created);
        assertEquals(assessment.getAssessmentId(), created.getAssessmentId());
        assertEquals(assessment.getAssessmentName(), created.getAssessmentName());
        verify(assessmentDao, times(1)).save(assessment);
    }

    @Test
    public void testGetAssessmentById() {
        when(assessmentDao.findById(anyLong())).thenReturn(assessment);

        Assessment found = assessmentService.getAssessmentById(1L);

        assertNotNull(found);
        assertEquals(assessment.getAssessmentId(), found.getAssessmentId());
        verify(assessmentDao, times(1)).findById(1L);
    }

    @Test
    public void testGetAllAssessments() {
        List<Assessment> assessments = new ArrayList<>();
        assessments.add(assessment);
        when(assessmentDao.findAll()).thenReturn(assessments);

        List<Assessment> result = assessmentService.getAllAssessments();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(assessmentDao, times(1)).findAll();
    }

    @Test
    public void testDeleteAssessment() {
        doNothing().when(assessmentDao).delete(anyLong());

        assessmentService.deleteAssessment(1L);

        verify(assessmentDao, times(1)).delete(1L);
    }

    @Test
    public void testUpdateAssessment() {
        when(assessmentDao.update(any(Assessment.class))).thenReturn(assessment);

        Assessment updated = assessmentService.update(assessment);

        assertNotNull(updated);
        assertEquals(assessment.getAssessmentId(), updated.getAssessmentId());
        verify(assessmentDao, times(1)).update(assessment);
    }

    @Test
    public void testGetAssessmentByMrfId() {
        List<Assessment> assessments = new ArrayList<>();
        assessments.add(assessment);
        when(assessmentDao.findAssessmentByMrfId(anyLong())).thenReturn(assessments);

        List<Assessment> result = assessmentService.getAssessmentByMrfId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(assessmentDao, times(1)).findAssessmentByMrfId(1L);
    }
}