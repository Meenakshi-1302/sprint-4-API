package com.rts.tap.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dto.MRFRecruiterDto;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.dto.RecruitingManagerAddCandidateDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruiters;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFVendor;

import jakarta.mail.MessagingException;

/**
 * author: Jeevarajan, Vashanth version: v2.0 updated at: 12-11-2024
 **/

public interface RecruitingManagerService {

	public Employee fetchRecruitingManagerById(long id);

	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id);

	public MRF getMrfById(long id);

	public String mrfAssignToVendor(MRFVendorDto mrfVendorDto);

	public List<MRFVendorDto> getAllMrfVendorsRecords();

	public String mrfAssignToRecruiter(MRFRecruiterDto mrfRecruiterDto);

	public String reassignMrfToRecruiter(MRFRecruiterDto mrfRecruiterDto);

	public List<MRFRecruiters> getAllAssignedMrfRecruiterRecords();

	public String updateMrfRecruiter(MRFRecruiterDto mrfRecruiterDto, long id);

	public List<Employee> getAllRecruitersByRecruitingManagerID(long id);

	public void updateMrfStage(String mrfStage, Long mrfId);

	public List<MRFRecruiters> getAllRecruitersByMRFRecruitingManagerId(long id);

	public List<MRFVendor> getAllVendorsAssignedForMRFbyMrfId(long mrfId);

	// Adding candidate by recruiting manager
	String addNewCandidate(RecruitingManagerAddCandidateDto recruitingManagerAddCandidateDto,
			MultipartFile candidateResume) throws MessagingException, IOException;

	// GET ALL CANDIDATES BY SOURCEID WHICH HAS THE SOURCE AS REFERRAL
	List<Candidate> getCandidatesBySourceId(long sourceId);
}
