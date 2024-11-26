package com.rts.tap.service;

import com.rts.tap.model.Assessment;

import java.util.List;

public interface AssessmentService {
    Assessment createAssessment(Assessment assessment);
    Assessment getAssessmentById(Long id);
    List<Assessment> getAllAssessments();
    void deleteAssessment(Long id);
    Assessment update(Assessment assessment);
    List<Assessment> getAssessmentByMrfId(Long id);
}


