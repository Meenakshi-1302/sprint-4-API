package com.rts.tap.serviceimplementation;
 
import java.io.IOException;
import java.util.List;
 
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.JobDescriptionDAO;
import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;
import com.rts.tap.service.JobDescriptionService;
 
@Service
public class JobDescriptionServiceImpl implements JobDescriptionService{
	JobDescriptionDAO jobDescriptionDao;
 
	public JobDescriptionServiceImpl(JobDescriptionDAO jobDescriptionDao) {
		super();
		this.jobDescriptionDao = jobDescriptionDao;
	}
 
	@Override
    public JobDescription addJobDescription(String jobTitle, String jobParameter, MultipartFile rolesAndResponsibilities) {
        JobDescription jobDescription = new JobDescription();
        jobDescription.setJobTitle(jobTitle);
        jobDescription.setJobParameter(jobParameter);
 
        try {
            byte[] rolesAndResponsibilitiesBytes = rolesAndResponsibilities.getBytes();
            jobDescription.setRolesAndResponsibilities(rolesAndResponsibilitiesBytes);
 
            jobDescriptionDao.saveJobDescription(jobDescription);
            return jobDescription;
        } catch (IOException e) {
            throw new RuntimeException(MessageConstants.JOB_DESCRIPTION_ADDED_SUCCESS);
        } catch (Exception e) {
            throw new RuntimeException(MessageConstants.JOB_DESCRIPTION_ADDED_FAILED);
        }
    }
	@Override
	public JobDescription editJobDescription(Long jobDescriptionId, String jobTitle, String jobParameter, MultipartFile rolesAndResponsibilities) {
 
		JobDescription existingJobDescription = jobDescriptionDao.viewByJobDescriptionId(jobDescriptionId);
	    if (existingJobDescription == null) {
	        throw new RuntimeException(MessageConstants.JOB_DESCRIPTION_NOT_FOUND);
	    }
 
	    existingJobDescription.setJobTitle(jobTitle);
	    existingJobDescription.setJobParameter(jobParameter);
 
	    if (rolesAndResponsibilities != null && !rolesAndResponsibilities.isEmpty()) {
	        try {
	            byte[] rolesAndResponsibilitiesBytes = rolesAndResponsibilities.getBytes();
	            existingJobDescription.setRolesAndResponsibilities(rolesAndResponsibilitiesBytes);
	        } catch (IOException e) {
	            throw new RuntimeException(MessageConstants.JOB_DESCRIPTION_UPDATE_FAILED);
	        }
	    }
 
	    jobDescriptionDao.saveJobDescription(existingJobDescription);
	    return existingJobDescription;
	}
 
	@Override
	public List<JobDescription> getAllJobDescriptions() {
 
		return jobDescriptionDao.viewAllJobDescriptions();
	}
 
	@Override
	public JobDescription getByJobDescriptionId(Long jobDescriptionId) {
		return jobDescriptionDao.viewByJobDescriptionId(jobDescriptionId);
	}
 
	@Override
	public JobDescription getByJobTitle(String JobTitle) {
		return jobDescriptionDao.viewByJobTitle(JobTitle);
	}
 
	@Override
	public List<String> getAllJobTitles() {
		return jobDescriptionDao.viewAllJobTitles();
	}

	@Override
	public MrfJd addJobDescriptionAssignToMrf(String jobTitle, String jobParameter,
			MultipartFile rolesAndResponsibilities) throws IOException {
		MrfJd mrfJd = new MrfJd();
		mrfJd.setJobTitle(jobTitle);
		mrfJd.setJobParameter(jobParameter);
 
        try {
            byte[] rolesAndResponsibilitiesBytes = rolesAndResponsibilities.getBytes();
            mrfJd.setRolesAndResponsibilities(rolesAndResponsibilitiesBytes);
 
            jobDescriptionDao.saveJobDescription(mrfJd);
            return mrfJd;
        } catch (IOException e) {
            throw new RuntimeException(MessageConstants.JOB_DESCRIPTION_ADDED_FAILED);
        } catch (Exception e) {
            throw new RuntimeException(MessageConstants.JOB_DESCRIPTION_ADDED_FAILED);
        }
	}
 
}