package com.rts.tap.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.JobPosting;
import com.rts.tap.service.JobPostingService;
 
@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL) // The base URL for this controller
	public class JobPostingController {
 
	    @Autowired
	    private JobPostingService jobPostingService;
 
	    @GetMapping(APIConstants.JOB_POSTING_URL)
	    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
	        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
	        return ResponseEntity.ok(jobPostings); // Return list of job postings with HTTP 200 OK status
	    }
	}
 
