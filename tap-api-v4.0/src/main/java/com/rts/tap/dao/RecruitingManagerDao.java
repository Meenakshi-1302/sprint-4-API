package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.dto.MRFRecruiterDto;
import com.rts.tap.dto.MRFVendorDto;
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

public interface RecruitingManagerDao {

	public Employee getEmployeeById(long id);

	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id);

	public MRF getMrfById(long id);

	public String assignMrfToVendor(MRFVendorDto mrfVendorDto);

	public List<MRFVendor> getAllMrfsVendors();

	public String assignMrfToRecruiter(MRFRecruiterDto mrfRecruiterDto);

	public String reassignMrfToRecruiter(MRFRecruiterDto mrfRecruiterDto);

	public MRFRecruitingManager getMrfRecruitingManagerById(long id);

	public List<MRFRecruiters> getAllRecruitersAssigned();

	public List<Employee> getAllRecruitersByRecruitingManagerId(long id);

	public String updateMrfRecruiter(MRFRecruiterDto mrfRecruiterDto, long id);

	public void updateMrfStage(String mrfStage, Long mrfId);

	public MRFRecruiters findMrfRecruiterByMrfId(long mrfRecruitingManagerId);

	public List<MRFRecruiters> getAssignedRecruitersByMrfRecruitingManagerId(long id);

	public List<MRFVendor> getAllVendorsAssignedForMrf(long mrfId);

	// CANDIDATE ADDITION
	String addCandidate(Candidate candidate) throws MessagingException;

	// GET ALL CANDIDATES BY SOURCEID
	List<Candidate> findCandidatesBySourceIdAndSource(long sourceId, String source);

}
