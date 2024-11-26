package com.rts.tap.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Assessment;
import com.rts.tap.service.AssessmentService;

@CrossOrigin(origins=APIConstants.FRONT_END_URL)
@RestController
@RequestMapping(path=APIConstants.BASE_ASSESSMENT_URL)
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping(path = APIConstants.SAVE_ASSESSMENT_URL)
    public Assessment createAssessment(@RequestBody Assessment assessment) {
        return assessmentService.createAssessment(assessment);
    }

    @GetMapping(path = APIConstants.GET_ALL_ASSESSMENT_URL)
    public Assessment getAssessment(@PathVariable Long id) {
        return assessmentService.getAssessmentById(id);
    }

    @GetMapping(path = APIConstants.GET_BY_ID_ASSESSMENT_URL)
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @DeleteMapping(path = APIConstants.DELETE_ASSESSMENT_URL)
    public void deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
    }
    
    @PutMapping(path = APIConstants.UPDATE_ASSESSMENT_URL)
    public Assessment updateAssessment(@PathVariable Long id, @RequestBody Assessment assessment) {
    	assessment.setAssessmentId(id);
    	return assessmentService.update(assessment);
    }
    
    @GetMapping(path = APIConstants.GET_ASSESSMENT_BY_MRF_ID_URL)
    public List<Assessment> getAssessmentByMrfId(@PathVariable Long id) {
        return assessmentService.getAssessmentByMrfId(id);
    }
}
