package com.rts.tap.serviceimplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.rts.tap.dao.CandidateDao;
import com.rts.tap.dao.VendorRepo;
import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.RecruitProcessDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.CandidateCsvRepresentation;
import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;
import com.rts.tap.model.RecruitmentProcess;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.CandidateService;
//import com.rts.tap.utils.CSVParserUtils;

import jakarta.mail.MessagingException;

@Service
public class CandidateServiceImpl implements CandidateService {

	private CandidateDao candidateDao;

	private VendorRepo repository;

	public CandidateServiceImpl(CandidateDao candidateDao, VendorRepo repository) {
		super();
		this.candidateDao = candidateDao;
		this.repository = repository;
	}

	@Override
	public void save(Candidate candidate) {
		candidateDao.save(candidate);
	}

	@Override
	public List<Candidate> findAll() {
		return candidateDao.findAll();
	}

	@Override
	public Candidate findById(Long id) {
		return candidateDao.findById(id);
	}

	@Override
	public void update(Candidate candidate) {
		candidateDao.update(candidate);
	}

	@Override
	public void delete(Long id) {
		candidateDao.delete(id);
	}


	

	@Override
	public List<MrfJd> appliedJobsByCandidateId(Long candidateId) {
		return candidateDao.appliedJobs(candidateId);
	}

	@Override
	public Long appliedJobsCount(Long candidateId) {
		return candidateDao.appliedJobsCount(candidateId);
	}

	@Override
	public Long assessmentCounts(Long candidateId) {
		return candidateDao.assessmentCountByCandidateId(candidateId);
	}

	@Override
	public Long interviewCounts(Long candidateId) {
		return candidateDao.interviewCountByCandidateId(candidateId);
	}

	@Override
	public List<RecruitProcessDto> interviewLevels(Long mrfJdId) {
		return candidateDao.levelsOfInterview(mrfJdId);
	}

	@Override
	public Object getRecruitmentProcessById(Long recruitmentProcessId, Long candidateId) {
		return candidateDao.getRecruitmentProcessById(recruitmentProcessId, candidateId);
	}

	@Override
	public String applyJob(CandidateDto candidateDto, MultipartFile candidateResume)
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
		candidate.setSource("");
		candidate.setSourceId(candidateDto.getSourceId());
		candidate.setSkill(candidateDto.getSkill());
		candidate.setLocation(candidateDto.getLocation());
		candidate.setPanNumber(candidateDto.getPanNumber());
		candidate.setStatus(candidateDto.getStatus());
//		candidate.setAssignedAt(candidateDto.getAssignedAt());
//		candidate.setVendorMRF(candidateDto.getVendorMRF());
		candidate.setIsPasswordChanged(candidateDto.getIsPasswordChanged());
		candidate.setCandidateResume(Resume);
		candidate.setPassword("");

		// Pass the candidate entity and the resume file to the DAO
		return candidateDao.applyJob(candidate);
	}
	@Override
	public Candidate updateCandidatePartial(Long candidateId, String firstName, String lastName, Integer experience,
	        String skill, String location, MultipartFile candidateResume, MultipartFile candidateProfileImage) {
	    try {
	        Candidate candidate = candidateDao.findById(candidateId);
	        if (candidate != null) {
	        	if (firstName != null)
					candidate.setFirstName(firstName);
				if (lastName != null)
					candidate.setLastName(lastName);
				if (experience != null)
					candidate.setExperience(experience);
				if (skill != null)
					candidate.setSkill(skill);
				if (location != null)
					candidate.setLocation(location);
	        		
	            if (candidateResume != null && !candidateResume.isEmpty()) {
	                byte[] candidateResumeBytes = candidateResume.getBytes();
	                candidate.setCandidateResume(candidateResumeBytes);
	            }
 
	            if (candidateProfileImage != null && !candidateProfileImage.isEmpty()) {
	                byte[] candidateProfileImageBytes = candidateProfileImage.getBytes();
	                candidate.setCandidateProfileImage(candidateProfileImageBytes);
	            }
 
	            candidateDao.update(candidate);
	            return candidate;
	        }
	        return null;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public String updateOffer(Long offerId, Long candidateId) {
		return candidateDao.updateOffer(offerId, candidateId);
	}

	

}
