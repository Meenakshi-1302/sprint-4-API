package com.rts.tap.serviceimplementation;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.VendorDao;
import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.dto.VendorRemainingDaysDTO;
import com.rts.tap.exception.VendorNotFoundException;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.VendorService;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

/**
 * author: Jeevarajan, Vashanth version: v1.0 updated at: 04-11-2024
 **/

@Service
@Transactional
public class VendorServiceImplementation implements VendorService {

	private final VendorDao vendorDao;

	private EntityManager entityManager;

	public VendorServiceImplementation(VendorDao vendorDao, EntityManager entityManager) {
		super();
		this.vendorDao = vendorDao;
		this.entityManager = entityManager;
	}

	@Override
	public Vendor addNewVendor(VendorDto vendor) throws MessagingException {
		vendor.setUsername(generateVendorUsername(vendor.getOrganizationName()));
		vendor.setPassword(generateStrongPassword());
		return vendorDao.save(vendor);
	}

	@Override
	public VendorDto getVendorById(Long id) {
		Vendor vendor = vendorDao.findById(id);
		if (vendor == null) {
			throw new VendorNotFoundException(MessageConstants.VENDOR_NOT_FOUND);
		}
		return vendorDtoConvertion(vendor);
	}

	@Override
	public List<VendorDto> getAllVendors() {
		return vendorDao.findAllVendor().stream().map(vendor -> {
			return vendorDtoConvertion(vendor);
		}).collect(Collectors.toList());
	}

	@Override
	public Vendor updateVendor(Long id, VendorDto vendor) {
		Vendor existingVendor = vendorDao.findById(id);
		if (existingVendor == null) {
			throw new VendorNotFoundException(MessageConstants.VENDOR_NOT_FOUND);
		}
		return vendorDao.updateVendor(id, vendor);
	}

	@Override
	public String deleteVendor(Long id) {
		try {
			vendorDao.deleteById(id);
			return MessageConstants.VENDOR_DELETED_SUCCESS;
		} catch (Exception e) {
			return MessageConstants.VENDOR_DELETED_FAILED;
		}
	}
	

	// this method will convert Vendor entity to vendor DTO
	public VendorDto vendorDtoConvertion(Vendor vendor) {
		VendorDto vendorObject = new VendorDto();
		vendorObject.setVendorId(vendor.getVendorId());
		vendorObject.setOrganizationName(vendor.getOrganizationName());
		vendorObject.setUsername(vendor.getThirdPartyCredentitals().getUsername());
		vendorObject.setContactName(vendor.getContactName());
		vendorObject.setContactNumber(vendor.getContactNumber());
		vendorObject.setAddress(vendor.getAddress());
		vendorObject.setEmail(vendor.getThirdPartyCredentitals().getEmail());
		vendorObject.setPassword(vendor.getThirdPartyCredentitals().getPassword());
		vendorObject.setWebsiteUrl(vendor.getWebsiteUrl());
		vendorObject.setTaxIdentifyNumber(vendor.getTaxIdentifyNumber());
		vendorObject.setIsPasswordChanged(vendor.getIsPasswordChanged());
		vendorObject.setRole(vendor.getThirdPartyCredentitals().getRole().getRole());
		return vendorObject;
	}

	@Override
	public String generateVendorUsername(String name) {
		String prefix = name.length() >= 3 ? name.substring(0, 3).toLowerCase() : name.toLowerCase();
		Random random = new Random();
		StringBuilder digits = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			digits.append(random.nextInt(10)); // Generates a digit between 0 and 9
		}
		String username = prefix + digits.toString();
		return username;
	}

	@Override
	public String generateStrongPassword() {
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(12);
		for (int i = 0; i < 12; i++) {
			int index = random.nextInt(MessageConstants.CHARACTERS.length());
			password.append(MessageConstants.CHARACTERS.charAt(index));
		}
		return password.toString();
	}

	@Override
	public VendorDto dologin(VendorDto vendorDto) {		
		Vendor vendor = vendorDao.login(vendorDto);
		return vendorDtoConvertion(vendor);
	}

	@Override
	public String addNewCandidate(CandidateDto candidateDto, MultipartFile candidateResume)
			throws MessagingException, IOException {
		byte[] Resume = candidateResume.getBytes();

//		if (candidateResume != null && !candidateResume.getContentType().equals("application/pdf")) {
//			return "Only PDF files are allowed";
//		}

//		long maxSizeInBytes = 2 * 1024 * 1024; // 2MB
//		if (candidateResume != null && candidateResume.getSize() > maxSizeInBytes) {
//			return "File size must be less than or equal to 2MB";
//		}

		Candidate candidate = new Candidate();
		candidate.setFirstName(candidateDto.getFirstName());
		candidate.setLastName(candidateDto.getLastName());
		candidate.setMobileNumber(candidateDto.getMobileNumber());
		candidate.setEmail(candidateDto.getEmail());
		candidate.setExperience(candidateDto.getExperience());
		candidate.setResume(candidateDto.getResume());
		candidate.setSource("VENDOR");
		candidate.setSourceId(candidateDto.getSourceId());
		candidate.setSkill(candidateDto.getSkill());
		candidate.setLocation(candidateDto.getLocation());
		candidate.setPanNumber(candidateDto.getPanNumber());
		candidate.setStatus(candidateDto.getStatus());
//		candidate.setAssignedAt(candidateDto.getAssignedAt());
//		candidate.setVendorMRF(candidateDto.getVendorMRF());
		candidate.setIsPasswordChanged(candidateDto.getIsPasswordChanged());
		candidate.setCandidateResume(Resume);
		candidate.setPassword("shakeel");

		// Pass the candidate entity and the resume file to the DAO
		return vendorDao.addCandidate(candidate);
	}

	@Override
	public List<Date> getCandidateCountBySourceAndYear(Long sourceId) {
		// Fetch data from DAO
		List<Date> results = vendorDao.getAssignedDatesBySourceAndYear(sourceId);

		return results;
	}

	@Override
	public void saveAllCandidates(List<CandidateDto> candidateDTOs) {
		List<Candidate> candidates = candidateDTOs.stream().map(this::convertToEntity) // Convert each DTO to an entity
				.collect(Collectors.toList());

		vendorDao.saveAll(candidates); // Persist the list of candidates
	}

	// Convert DTO to Entity
	private Candidate convertToEntity(CandidateDto candidateDTO) {
		Candidate candidate = new Candidate();
		candidate.setFirstName(candidateDTO.getFirstName());
		candidate.setLastName(candidateDTO.getLastName());
		candidate.setMobileNumber(candidateDTO.getMobileNumber());
		candidate.setEmail(candidateDTO.getEmail());
		candidate.setExperience(candidateDTO.getExperience());
		candidate.setResume(candidateDTO.getResume());
		candidate.setSource(candidateDTO.getSource());
		candidate.setSourceId(candidateDTO.getSourceId());
		candidate.setSkill(candidateDTO.getSkill());
		candidate.setLocation(candidateDTO.getLocation());
		candidate.setPanNumber(candidateDTO.getPanNumber());
		candidate.setStatus(candidateDTO.getStatus());

		// Handle file uploads (convert MultipartFile to byte[] if needed)
//		try {
//			if (candidateDTO.getCandidateResume() != null) {
//				candidate.setCandidateResume(candidateDTO.getCandidateResume().getBytes());
//			}
//		} catch (IOException e) {
//			// Handle file conversion error if needed
//		}

		// candidate.setAssignedAt(candidateDTO.getAssignedAt());

		// Handle relationships, e.g., MRFCandidate
		// candidate.setMrfCandidate(someMrfCandidate); // Set if required, fetch the
		// MRFCandidate as needed

		return candidate;
	}

	// Code by Team-D - Nagarjun N S

	@Override
	public Long getCountOfAssignedMrfByVendorId(Long vendorId) {
		return vendorDao.getCountOfAssignedMrfByVendorId(vendorId);
	}

	@Override
	public Long getCountOfCompletedMrfByVendorId(Long vendorId) {
		return vendorDao.getCountOfCompletedMrfByVendorId(vendorId);
	}

	@Override
	public Long getCountOfAllMrfByVendorId(Long vendorId) {
		return vendorDao.getCountOfAllMrfByVendorId(vendorId);
	}

// 	@Override
// 	public List<MrfDetailsForVendorDTO> getAllMrfDetailsByVendorId(Long vendorId, Long mrfId) {
// 		List<MRFVendor> mrfVendors = vendorDao.getAllMrfByVendorId(vendorId);
	//
// 		List<MrfDetailsForVendorDTO> mrfDetailsList = new ArrayList<>();
	//
// 		for (MRFVendor mrfVendor : mrfVendors) {
// 			MRF mrf = mrfVendor.getMrf();
// 			Employee recruitingManager = mrfVendor.getRecruitingManager();
	//
// 			MrfDetailsForVendorDTO dto = new MrfDetailsForVendorDTO();
// 			dto.setMrfId(mrf.getMrfId());
// 			dto.setMrfDepartmentName(mrf.getMrfDepartmentName());
// 			dto.setMrfRequiredTechnology(mrf.getMrfRequiredTechnology());
// 			dto.setProbableDesignation(mrf.getProbableDesignation());
// 			dto.setRequiredResourceCount(mrf.getRequiredResourceCount());
// 			dto.setCreatedAt(mrf.getCreatedAt());
// 			dto.setUpdateAt(mrf.getUpdatedAt());
// 			dto.setMrfCriteria(mrf.getMrfCriteria());
// 			dto.setRequiredSkills(mrf.getRequiredSkills());
// 			dto.setJobDescriptionId(mrf.getJobDescriptionId());
// 			dto.setRecruitingManager(recruitingManager.getManager());
	//
// 			mrfDetailsList.add(dto);
// 		}
// 		return mrfDetailsList;
// 	}

	@Override
	public List<MRFVendor> findAllMRFVendorDetailsByVendorId(Long vendorId) {
		return vendorDao.findAllMRFVendorDetailsByVendorId(vendorId);
	}

	@Override
	public MRF getMRFDetailsByVendorIdAndMrfId(Long vendorId, Long mrfId) {
		return vendorDao.getMRFDetailsByVendorIdAndMrfId(vendorId, mrfId);
	}

	@Override
	public Candidate getCandidateByVendorAndMrfId(Long vendorId, Long mrfId) {
		if (vendorId == null || mrfId == null || vendorId == 0 || mrfId == 0) {
			throw new IllegalArgumentException("Vendor Id and MRF Id are needed");
		} else {
			return vendorDao.getCandidateByVendorAndMrfId(vendorId, mrfId);
		}
	}

	@Override
	public Long getCountOfCandidateByVendorAndMrfId(Long vendorId, Long mrfId) {
		if (vendorId == null || mrfId == null || vendorId == 0 || mrfId == 0) {
			throw new IllegalArgumentException("Vendor Id and MRF Id are needed");
		} else {
			return vendorDao.getCountOfCandidateByVendorAndMrfId(vendorId, mrfId);
		}
	}

	@Override
	public Long getCountOfCandidateByVendorId(Long vendorId) {
		if (vendorId == null || vendorId == 0) {
			throw new IllegalArgumentException("Vendor ID is needed");
		} else {
			return vendorDao.getCountOfCandidateByVendorId(vendorId);
		}
	}

	@Override
	public List<Candidate> getHiredAndJoinedCandidatesAssignedByVendorId(Long vendorId) {
		if (vendorId == null || vendorId == 0) {
			throw new IllegalArgumentException("Vendor ID is needed");
		} else {
			return vendorDao.getHiredAndJoinedCandidatesAssignedByVendorId(vendorId);
		}
	}

	@Override
	public Long getCountOfHiredCandidateByVendorId(Long vendorId) {
		if (vendorId == null || vendorId == 0) {
			throw new IllegalArgumentException("Vendor ID is needed");
		} else {
			return vendorDao.getCountOfHiredCandidateByVendorId(vendorId);
		}
	}

	@Override
	public Long getCountOfJoinedCandidateByVendorId(Long vendorId) {
		if (vendorId == null || vendorId == 0) {
			throw new IllegalArgumentException("Vendor ID is needed");
		} else {
			return vendorDao.getCountOfJoinedCandidateByVendorId(vendorId);
		}
	}

// 	@Override
// 	public List<MrfDetailsForVendorDTO> getMRFDetailsByVendorIdAndMrfId(Long vendorId, Long mrfId) {
// 		List<MRFVendor> mrfVendors = vendorDao.getMRFDetailsByVendorIdAndMrfId(vendorId, mrfId);
	//
// 		List<MrfDetailsForVendorDTO> mrfDetailsList = new ArrayList<>();
	//
// 		for (MRFVendor mrfVendor : mrfVendors) {
// 			MRF mrf = mrfVendor.getMrf();
// 			Employee recruitingManager = mrfVendor.getRecruitingManager();
	//
// 			MrfDetailsForVendorDTO dto = new MrfDetailsForVendorDTO();
// 			dto.setMrfId(mrf.getMrfId());
// 			dto.setMrfDepartmentName(mrf.getMrfDepartmentName());
// 			dto.setMrfRequiredTechnology(mrf.getMrfRequiredTechnology());
// 			dto.setProbableDesignation(mrf.getProbableDesignation());
// 			dto.setRequiredResourceCount(mrf.getRequiredResourceCount());
// 			dto.setCreatedAt(mrf.getCreatedAt());
// 			dto.setUpdateAt(mrf.getUpdatedAt());
// 			dto.setMrfCriteria(mrf.getMrfCriteria());
// 			dto.setRequiredSkills(mrf.getRequiredSkills());
// 			dto.setJobDescriptionId(mrf.getJobDescriptionId());
// 			dto.setRecruitingManager(recruitingManager.getManager());
	//
// 			mrfDetailsList.add(dto);
// 		}
// 		return mrfDetailsList;
// 	}

	@Override
	public List<MRFVendor> getAllMrfAssignedForVendor(Long vendorId) {
		if (vendorId == null || vendorId == 0) {
			throw new IllegalArgumentException("Vendor ID is needed");
		} else {
			return vendorDao.getAllMrfAssignedForVendor(vendorId);
		}
	}

	@Override
	public String updateMRFStatus(Long vendorId, Long mrfId, String status) {
		return vendorDao.updateMRFStatus(vendorId, mrfId, status);
	}

// 	@Override
// 	public List<MrfDetailsForVendorDTO> getAllMrfDetailsAssignedForVendor(Long vendorId) {
// 		List<MRFVendor> mrfVendors = vendorDao.getAllMrfAssignedForVendor(vendorId);
// 		List<MrfDetailsForVendorDTO> mrfDetailsList = new ArrayList<>();
	//
// 		for (MRFVendor mrfVendor : mrfVendors) {
// 			MRF mrf = mrfVendor.getMrf();
// 			Employee recruitingManager = mrfVendor.getRecruitingManager();
// 			MrfDetailsForVendorDTO dto = new MrfDetailsForVendorDTO();
// 			dto.setMrf(mrf);
// 			dto.setMrfId(mrf.getMrfId());
// 			dto.setMrfDepartmentName(mrf.getMrfDepartmentName());
// 			dto.setMrfRequiredTechnology(mrf.getMrfRequiredTechnology());
// 			dto.setProbableDesignation(mrf.getProbableDesignation());
// 			dto.setRequiredResourceCount(mrf.getRequiredResourceCount());
// 			dto.setCreatedAt(mrf.getCreatedAt());
// 			dto.setUpdateAt(mrf.getUpdatedAt());
// 			dto.setMrfCriteria(mrf.getMrfCriteria());
// 			dto.setRequiredSkills(mrf.getRequiredSkills());
// 			dto.setJobDescriptionId(mrf.getJobDescriptionId());
// 			dto.setRecruitingManager(recruitingManager.getManager());
// 			mrfDetailsList.add(dto);
// 		}
// 		return mrfDetailsList;
// 	}

	@Override
	public List<Candidate> getAllCandidatesAssignedByVendorAndMrfId(Long vendorId, Long mrfId) {
		return vendorDao.getAllCandidatesAssignedByVendorAndMrfId(vendorId, mrfId);
	}

	@Override
	public List<VendorRemainingDaysDTO> getRemainingDays(Long vendorId) {
		List<MRFVendor> mrfVendors = vendorDao.getRemainingDays(vendorId);
		List<VendorRemainingDaysDTO> mrfDetailsList = new ArrayList<>();
		for (MRFVendor mrfVendor : mrfVendors) {
			VendorRemainingDaysDTO dto = new VendorRemainingDaysDTO();
			dto.setMrfId(mrfVendor.getMrf().getMrfId());
			dto.setProbableDesignation(mrfVendor.getMrf().getProbableDesignation());
			dto.setMrf(mrfVendor.getMrf());

			LocalDate closureDate = mrfVendor.getMrf().getMrfCriteria().getClosureDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate assignedDate = mrfVendor.getAssignedDate().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), closureDate);
			dto.setRemainingDays(remainingDays);

			mrfDetailsList.add(dto);
		}
		return mrfDetailsList;
	}

// 	@Override
// 	public List<MRFVendor> getAllMrfAssignedByVendorId(Long vendorId) {
// 		List<MRFVendor> mrfvendors = vendorDao.getAllMrfAssignedByVendorId(vendorId);
// 		List<MRFVendorDto> mrfVendordto = new ArrayList<>();
	//
// 		for (MRFVendor mrfVendor : mrfvendors) {
// 			MRFVendorDto dto = new MRFVendorDto();
// 			dto.setMrfVendorId(mrfVendor.getMrfVendorId());
// 			dto.setMrfId(mrfVendor.getMrf().getMrfId());
// 			dto.setMrfDepartmentName(mrfVendor.getMrf().getMrfDepartmentName());
// 			dto.setRecruitingManagerId(mrfVendor.getRecruitingManager().getEmployeeId());
// 			dto.setRecruitingManagerName(mrfVendor.getRecruitingManager().getEmployeeName());
// 			dto.setVendorId(mrfVendor.getVendor().getVendorId());
// 			dto.setVendorName(mrfVendor.getVendor().getOrganizationName());
// 			dto.setAssignedDate(mrfVendor.getAssignedDate());
// 			dto.setVendorAssignedStatus(mrfVendor.getVendorAssignedStatus());
// 			
// 			MrfDTO mrfdetails = new MrfDTO();
// 			mrfdetails.setMrfId(mrfVendor.getMrf().getMrfId());
// 			mrfdetails.setBusinessUnitHead(mrfVendor.getMrf().getBusinessUnitHead());
// 			mrfdetails.setClientPartner(mrfVendor.getMrf().getClientPartner());
// 			mrfdetails.setCreatedAt(mrfVendor.getMrf().getCreatedAt());
// 			mrfdetails.setJobDescriptionId(mrfVendor.getMrf().getJobDescriptionId());
// 			mrfdetails.setMrfAgreement(mrfVendor.getMrf().getMrfAgreement());
// 			mrfdetails.setMrfCriteria(mrfVendor.getMrf().getMrfCriteria());
// 			mrfdetails.setMrfDepartmentName(mrfVendor.getMrf().getMrfDepartmentName());
// 			mrfdetails.setMrfRequiredTechnology(mrfVendor.getMrf().getMrfRequiredTechnology());
// 			mrfdetails.setMrfStatus(mrfVendor.getMrf().getMrfStatus());
// 			mrfdetails.setProbableDesignation(mrfVendor.getMrf().getProbableDesignation());
// 			mrfdetails.setRequiredResourceCount(mrfVendor.getMrf().getRequiredResourceCount());
// 			mrfdetails.setRequirement(mrfVendor.getMrf().getRequirement());
// 			mrfdetails.setUpdatedAt(mrfVendor.getMrf().getUpdatedAt());
// 			
// 			dto.setMrfdetails(mrfdetails);
// 			
// 			mrfVendordto.add(dto);
// 		}
// 		return mrfvendors;
// 	}

	@Override
	public String resetPassword(String email, String oldPassword, String newPassword) {
		Vendor vendor = vendorDao.findByEmail(email);

		if (vendor == null) {
			throw new IllegalArgumentException("Vendor not found for ID: " + email);
		}

		if (oldPassword.equals(newPassword)) {
			throw new IllegalArgumentException("Old password and new password should not be the same");
		}

		if (!oldPassword.equals(vendor.getThirdPartyCredentitals().getPassword())) {
			throw new IllegalArgumentException("Old password is incorrect");
		}

		vendor.getThirdPartyCredentitals().setPassword(newPassword);
		entityManager.merge(vendor);
		return "Password updated successfully";
	}

	@Override
	public List<Candidate> getAllCandidatesByVendorId(Long vendorId) {
		return vendorDao.getAllCandidatesByVendorId(vendorId);
	}

	@Override
	public Long getVendorIdByEmail(String email) {
		return vendorDao.getVendorIdByEmail(email);
	}
}
