package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.AssessmentDao;
import com.rts.tap.model.Assessment;
import com.rts.tap.service.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentDao assessmentDao;

    public AssessmentServiceImpl(AssessmentDao assessmentDao) {
        this.assessmentDao = assessmentDao;
    }

    @Override
    public Assessment createAssessment(Assessment assessment) {
        return assessmentDao.save(assessment);
    }

    @Override
    public Assessment getAssessmentById(Long id) {
        return assessmentDao.findById(id);
    }

    @Override
    public List<Assessment> getAllAssessments() {
        return assessmentDao.findAll();
    }

    @Override
    public void deleteAssessment(Long id) {
        assessmentDao.delete(id);
    }
    
    @Override
    public Assessment update(Assessment assessment) {
    	return assessmentDao.update(assessment);
    }
    
    @Override
    public List<Assessment> getAssessmentByMrfId(Long id) {
        return assessmentDao.findAssessmentByMrfId(id);
    }
}


