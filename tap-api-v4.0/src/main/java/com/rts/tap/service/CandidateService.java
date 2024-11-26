package com.rts.tap.service;

import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.RecruitProcessDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;
import com.rts.tap.model.RecruitmentProcess;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface CandidateService<T> {
	void save(Candidate candidate);

	/*
	 * Commented by team-D Author- Meenkashi Priyadharshini Ananthan
	 */
//    void bulkSave(MultipartFile csvFile) throws Exception;
	List<Candidate> findAll();

	Candidate findById(Long id);

	void update(Candidate candidate);

	void delete(Long id);

//	public Integer uploadCandidates(MultipartFile file, Long vendorId) throws IOException;

	List<MrfJd> appliedJobsByCandidateId(Long candidateId);

	Long appliedJobsCount(Long candidateId);

	Long assessmentCounts(Long candidateId);

	Long interviewCounts(Long candidateId);

	public List<RecruitProcessDto> interviewLevels(Long mrfJdId);

	Object getRecruitmentProcessById(Long recruitmentProcessId, Long candidateId);

	public String applyJob(CandidateDto candidateDto, MultipartFile candidateResume)
			throws MessagingException, IOException;

	public Candidate updateCandidatePartial(Long candidateId, String firstName, String lastName, Integer experience,
			String skill, String location, MultipartFile candidateResume, MultipartFile candidateProfileImage);

	// Team -D offer acceptance
	public String updateOffer(Long offerId, Long candidateId);

}
