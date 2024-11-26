package com.rts.tap.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.model.Vendor;

import jakarta.mail.MessagingException;

/**
 * author: Jeevarajan Rajarajacholan version: v1.0 updated at: 04-11-2024
 **/

public interface VendorDao {

	Vendor save(VendorDto vendor) throws MessagingException;

	Vendor updateVendor(long id, VendorDto existingVendor);

	String addCandidate(Candidate candidate) throws MessagingException;

	String deleteById(Long id);

	Vendor findById(Long id);

	List<Vendor> findAllVendor();
	
	Vendor login(VendorDto vendorDto);

	void saveAll(List<Candidate> candidates);

	MRFCandidate findMRFCandidateById(long sourceId);

	List<Date> getAssignedDatesBySourceAndYear(Long sourceId);

	// Team - D - Nagarjun N S
	public Long getCountOfAssignedMrfByVendorId(Long vendorId);

	public Long getCountOfCompletedMrfByVendorId(Long vendorId);

	public Long getCountOfAllMrfByVendorId(Long vendorId);

	public String updateMRFStatus(Long vendorId, Long mrfId, String status);

	public List<MRFVendor> findAllMRFVendorDetailsByVendorId(Long vendorId);// Rectified

	public MRF getMRFDetailsByVendorIdAndMrfId(Long vendorId, Long mrfId); // Rectified

	public Candidate getCandidateByVendorAndMrfId(Long vendorId, Long mrfId); // Rectified

	public Long getCountOfCandidateByVendorAndMrfId(Long vendorId, Long mrfId);// Rectified //optional

	public Long getCountOfCandidateByVendorId(Long vendorId);// rec

	public List<Candidate> getHiredAndJoinedCandidatesAssignedByVendorId(Long vendorId); // rec put filter in frontend

	public Long getCountOfHiredCandidateByVendorId(Long vendorId); // rec

	public Long getCountOfJoinedCandidateByVendorId(Long vendorId); // rec

	public List<MRFVendor> getAllMrfAssignedForVendor(Long vendorId); // rec

	public List<Candidate> getAllCandidatesAssignedByVendorAndMrfId(Long vendorId, Long mrfId); // rec

//		public Long getMrfRemainingDays(Long vendorId);

	public List<MRFVendor> getRemainingDays(Long vendorId);

	public String resetPassword(String email, String newPassword);

	public boolean vendorEmailExists(String email);

	public Vendor findByEmail(String email);

	public List<Candidate> getAllCandidatesByVendorId(Long vendorId);

	public Long getVendorIdByEmail(String email);
}
