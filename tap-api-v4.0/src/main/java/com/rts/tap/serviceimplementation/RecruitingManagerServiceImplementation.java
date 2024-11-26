package com.rts.tap.serviceimplementation;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.RecruitingManagerDao;
import com.rts.tap.dto.MRFRecruiterDto;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.dto.RecruitingManagerAddCandidateDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruiters;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.service.RecruitingManagerService;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;


/** 
 * author: Jeevarajan Rajarajacholan, Vashanth
 * version: v2.0
 * updated at: 12-11-2024
**/


@Service
@Transactional
public class RecruitingManagerServiceImplementation implements RecruitingManagerService {

	RecruitingManagerDao recruitingManagerDao;

	public RecruitingManagerServiceImplementation(RecruitingManagerDao recruitingManagerDao) {
		super();
		this.recruitingManagerDao = recruitingManagerDao;
	}

	@Override
	public Employee fetchRecruitingManagerById(long id) {
		return recruitingManagerDao.getEmployeeById(id);
	}

	@Override
	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id) {
		return recruitingManagerDao.getAllMrfsAssignedForRM(id);
	}

	@Override
	public MRF getMrfById(long id) {
		return recruitingManagerDao.getMrfById(id);
	}

	@Override
	public String mrfAssignToVendor(MRFVendorDto mrfVendorDto) {
		return recruitingManagerDao.assignMrfToVendor(mrfVendorDto);
	}
	
	@Override
	public String mrfAssignToRecruiter(MRFRecruiterDto mrfRecruiterDto) {
		    Date now = new Date();
	        mrfRecruiterDto.setCreatedAt(now);  // Set the current date for creation
	        mrfRecruiterDto.setUpdatedAt(now);
	        
		return recruitingManagerDao.assignMrfToRecruiter(mrfRecruiterDto);
	}

	@Override
	public List<MRFVendorDto> getAllMrfVendorsRecords() {
		return recruitingManagerDao.getAllMrfsVendors().stream().map(mrfVendor -> {
			MRFVendorDto mrfVendorDto = new MRFVendorDto();
			mrfVendorDto.setMrfId(mrfVendor.getMrf().getMrfId());
			mrfVendorDto.setRecrutingManagerId(mrfVendor.getRecruitingManager().getEmployeeId());
			mrfVendorDto.setVendorId(mrfVendor.getVendor().getVendorId());
			mrfVendorDto.setAssignedCount(mrfVendor.getAssignedCount());
			mrfVendorDto.setAchievedCount(mrfVendor.getAchievedCount());			
			System.out.println(mrfVendor.getVendorAssignedStatus());
			mrfVendorDto.setVendorAssignedStatus(mrfVendor.getVendorAssignedStatus());
			return mrfVendorDto;
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<MRFRecruiters> getAllAssignedMrfRecruiterRecords() {
		return recruitingManagerDao.getAllRecruitersAssigned();
		
	}
	
	@Override
	 public String updateMrfRecruiter(MRFRecruiterDto mrfRecruiterDto,long id) {
	        return recruitingManagerDao.updateMrfRecruiter(mrfRecruiterDto,id);
	    }

	@Override
	public String reassignMrfToRecruiter(MRFRecruiterDto mrfRecruiterDto) {
		  Date now = new Date();
	      mrfRecruiterDto.setUpdatedAt(now);	      
	      return recruitingManagerDao.reassignMrfToRecruiter(mrfRecruiterDto);
	}
	
	@Override
	public List<Employee> getAllRecruitersByRecruitingManagerID(long id){
		return recruitingManagerDao.getAllRecruitersByRecruitingManagerId(id);
	}
	

	@Override
	public void updateMrfStage(String mrfStage, Long mrfId) {
		recruitingManagerDao.updateMrfStage(mrfStage, mrfId);
	}
	
	@Override
	public List<MRFRecruiters>getAllRecruitersByMRFRecruitingManagerId(long id){
		return recruitingManagerDao.getAssignedRecruitersByMrfRecruitingManagerId(id);
	}
	
	@Override
	public List<MRFVendor> getAllVendorsAssignedForMRFbyMrfId(long mrfId){
		return recruitingManagerDao.getAllVendorsAssignedForMrf(mrfId);		
	}	
	
	
	@Override
    public String addNewCandidate(RecruitingManagerAddCandidateDto recruitingManagerAddCandidateDto, MultipartFile candidateResume)
            throws MessagingException, IOException {
        byte[] resume = candidateResume.getBytes();
 
        Candidate candidate = new Candidate();
        candidate.setFirstName(recruitingManagerAddCandidateDto.getFirstName());
        candidate.setLastName(recruitingManagerAddCandidateDto.getLastName());
        candidate.setMobileNumber(recruitingManagerAddCandidateDto.getMobileNumber());
        candidate.setEmail(recruitingManagerAddCandidateDto.getEmail());
        candidate.setExperience(recruitingManagerAddCandidateDto.getExperience());
        candidate.setResume(recruitingManagerAddCandidateDto.getResume());
        candidate.setSource("REFERRAL");
        candidate.setSourceId(recruitingManagerAddCandidateDto.getSourceId());
        candidate.setSkill(recruitingManagerAddCandidateDto.getSkill());
        candidate.setLocation(recruitingManagerAddCandidateDto.getLocation());
        candidate.setPanNumber(recruitingManagerAddCandidateDto.getPanNumber());
        candidate.setStatus(recruitingManagerAddCandidateDto.getStatus());
        candidate.setIsPasswordChanged(recruitingManagerAddCandidateDto.getIsPasswordChanged());
        candidate.setCandidateResume(resume);
        candidate.setPassword("Prince"); // Placeholder password, consider a secure way of setting a user's password
 
        return recruitingManagerDao.addCandidate(candidate);
    }
	
 
	@Override
	public List<Candidate> getCandidatesBySourceId(long sourceId) {
	    return recruitingManagerDao.findCandidatesBySourceIdAndSource(sourceId, "REFERRAL");
	}
}
