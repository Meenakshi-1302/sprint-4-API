package com.rts.tap.serviceimplementation;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.rts.tap.dao.JobPostingDao;
import com.rts.tap.model.JobPosting;
import com.rts.tap.service.JobPostingService;
 
@Service
public class JobPostingServiceImpl implements JobPostingService {
 
	 @Autowired
	    private JobPostingDao jobPostingDao;
 
	    @Override
	    public List<JobPosting> getAllJobPostings() {
	        List<JobPosting> jobPostings = jobPostingDao.getAll();
			return jobPostings;
	    }
 
}