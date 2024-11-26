package com.rts.tap.controller;
 
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;
import com.rts.tap.service.JobDescriptionService;
 
 
/**
* author: Gokul Anand M
* Sprint : 3
* Created at : 15 / 11 / 2024
* Updated at : 16 / 11 / 2024
**/
 
 
@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(MessageConstants.ORGIN)
public class JobDescriptionController {
    JobDescriptionService jobDescriptionService;
 
    public JobDescriptionController(JobDescriptionService jobDescriptionService) {
		super();
		this.jobDescriptionService = jobDescriptionService;
	}
 
    @PostMapping(APIConstants.ADD_JOB_DESCRIPTION)
    public ResponseEntity<String> addJobDescription(
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("jobParameter") String jobParameter,
            @RequestParam("rolesAndResponsibilities") MultipartFile rolesAndResponsibilities) throws IOException {
 
        JobDescription savedJobDescription = jobDescriptionService.addJobDescription(jobTitle, jobParameter, rolesAndResponsibilities);
 
        return ResponseEntity.ok().body(MessageConstants.JOB_DESCRIPTION_ADDED_SUCCESS);
    }
    
    @PostMapping(APIConstants.ADD_JOB_DESCRIPTION_MRFJD)
    public ResponseEntity<MrfJd> addJobDescriptionAssignToMrf(
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("jobParameter") String jobParameter,
            @RequestParam("rolesAndResponsibilities") MultipartFile rolesAndResponsibilities) throws IOException {
 
        MrfJd mrfJd = jobDescriptionService.addJobDescriptionAssignToMrf(jobTitle, jobParameter, rolesAndResponsibilities);
 
        return ResponseEntity.ok().body(mrfJd);
    }
    
    @PutMapping(APIConstants.EDIT_JOB_DESCRIPTION)
    public ResponseEntity<String> editJobDescription(
            @PathVariable("id") Long jobDescriptionId,
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("jobParameter") String jobParameter,
            @RequestParam(value = "rolesAndResponsibilities") MultipartFile rolesAndResponsibilities) {
 
        JobDescription updatedJobDescription = jobDescriptionService.editJobDescription(jobDescriptionId, jobTitle, jobParameter, rolesAndResponsibilities);
        return ResponseEntity.ok().body(MessageConstants.JOB_DESCRIPTION_UPDATE_SUCCESS);
    }
 
    @GetMapping(APIConstants.GET_ALL_JOB_DESCRIPTIONS)
    public ResponseEntity<List<JobDescription>> getAllJobDescriptions() {
        List<JobDescription> jobDescriptions = jobDescriptionService.getAllJobDescriptions();
        return ResponseEntity.ok(jobDescriptions);
    }
    @GetMapping(APIConstants.GET_JOB_DESCRIPTION_BY_ID)
    public ResponseEntity<?> getByJobDescriptionId(@PathVariable("id") Long jobDescriptionId) {
        JobDescription jobDescription = jobDescriptionService.getByJobDescriptionId(jobDescriptionId);
        if (jobDescription != null) {
            return ResponseEntity.ok(jobDescription);
        } else {
            return ResponseEntity.ok(MessageConstants.JOB_DESCRIPTION_NOT_FOUND);
        }
    }
    @GetMapping(APIConstants.GET_JOB_DESCRIPTION_BY_JOBTITLE)
    public ResponseEntity<?> getByJobTitle(@PathVariable("jobTitle") String jobTitle) {
    	try {
    		JobDescription jobDescription = jobDescriptionService.getByJobTitle(jobTitle);
    		if (jobDescription != null) {
                return ResponseEntity.ok(jobDescription);
            } else {
                return ResponseEntity.ok(MessageConstants.JOB_DESCRIPTION_NOT_FOUND);
            }
    	} catch(Exception e) {
            return ResponseEntity.ok(MessageConstants.JOB_DESCRIPTION_NOT_FOUND);
 
    	}
        
    }
    @GetMapping(APIConstants.GET_ALL_JOB_TITLES)
    public ResponseEntity<List<String>> getAllJobTitles() {
        List<String> jobTitles = jobDescriptionService.getAllJobTitles();
        return ResponseEntity.ok(jobTitles);
    }
 
}