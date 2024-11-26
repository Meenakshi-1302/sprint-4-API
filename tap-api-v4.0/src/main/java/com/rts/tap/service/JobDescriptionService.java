package com.rts.tap.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;

public interface JobDescriptionService {
	JobDescription addJobDescription(String jobTitle, String jobParameter, MultipartFile rolesAndResponsibilities)
			throws IOException;

	MrfJd addJobDescriptionAssignToMrf(String jobTitle, String jobParameter, MultipartFile rolesAndResponsibilities)
			throws IOException;

	
	JobDescription editJobDescription(Long jobDescriptionId, String jobTitle, String jobParameter,
			MultipartFile rolesAndResponsibilities);

	List<JobDescription> getAllJobDescriptions();

	JobDescription getByJobDescriptionId(Long jobDescriptionId);

	JobDescription getByJobTitle(String JobTitle);

	List<String> getAllJobTitles();
}