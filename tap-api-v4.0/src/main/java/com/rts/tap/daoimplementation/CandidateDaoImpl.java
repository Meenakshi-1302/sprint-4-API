package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.CandidateDao;
import com.rts.tap.model.Score;
import com.rts.tap.model.Interview;
import com.rts.tap.dto.InterviewDto;
import com.rts.tap.dto.RecruitProcessDto;
import com.rts.tap.dto.ScoreDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MrfJd;
import com.rts.tap.model.Offer;
import com.rts.tap.model.RecruitmentProcess;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;

@Repository
@Transactional
public class CandidateDaoImpl implements CandidateDao {

	private EntityManager entityManager;

	public CandidateDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Candidate candidate) {
		entityManager.persist(candidate);
	}

	public List<Candidate> findAll() {
		return entityManager.createQuery("from Candidate", Candidate.class).getResultList();
	}

	public Candidate findById(Long id) {
		return entityManager.find(Candidate.class, id);
	}

	public void update(Candidate candidate) {
		entityManager.merge(candidate);
	}

	public Candidate findByEmail(String email) {
		if (email != null) {
			String hql = "SELECT c FROM Candidate c where c.email =: email";
			return (Candidate) entityManager.createQuery(hql).setParameter("email", email).getSingleResult();
		}
		return null;
	}

	public void delete(Long id) {
		Candidate candidate = findById(id);
		if (candidate != null) {
			entityManager.remove(candidate);
		}
	}

	@Override
	public List<Candidate> findCandidateByMrfId(long mrfId) {
		String hql = "Select c from Candidate c where c.mrfId = :mrfId";
		return entityManager.createQuery(hql).setParameter("mrfId", mrfId).getResultList();
	}

	@Override
	public List<MrfJd> appliedJobs(Long candidateId) {

		String hql = "SELECT mc.mrfCandidateId " + "FROM MRFCandidate mc " + "JOIN mc.candidate c "
				+ "WHERE c.candidateId = :candidateId";
		List<Long> mrfCandidateIds = entityManager.createQuery(hql, Long.class).setParameter("candidateId", candidateId)
				.getResultList();

		if (mrfCandidateIds.isEmpty()) {
			return Collections.emptyList();
		}

		String hqll = "SELECT DISTINCT m.mrfId " + "FROM MRFCandidate mc " + "JOIN mc.mrfRecruiter mrr "
				+ "JOIN mrr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE mc.mrfCandidateId IN :mrfCandidateIds";
		List<Long> mrfIds = entityManager.createQuery(hqll, Long.class).setParameter("mrfCandidateIds", mrfCandidateIds)
				.getResultList();

		if (mrfIds.isEmpty()) {
			return Collections.emptyList();
		}

		String hq = "SELECT jb " + "FROM MRF mrf " + "JOIN mrf.mrfJd jb " + "WHERE mrf.mrfId IN :mrfIds";
		List<MrfJd> descriptions = entityManager.createQuery(hq, MrfJd.class).setParameter("mrfIds", mrfIds)
				.getResultList();

		return descriptions;
	}

	@Override
	public Long appliedJobsCount(Long candidateId) {

		String hql = "SELECT COUNT(mc) from MRFCandidate mc JOIN mc.candidate c where c.candidateId = :candidateId";

		Long count = (Long) entityManager.createQuery(hql).setParameter("candidateId", candidateId).getSingleResult();

		return count;

	}

	@Override
	public Long assessmentCountByCandidateId(Long candidateId) {

		String hql = "SELECT mc.mrfCandidateId " + "FROM MRFCandidate mc " + "JOIN mc.candidate c "
				+ "WHERE c.candidateId = :candidateId";

		List<Long> mrfCandidateIds = entityManager.createQuery(hql, Long.class).setParameter("candidateId", candidateId)
				.getResultList();

		if (mrfCandidateIds.isEmpty()) {
			return null;
		}

		String hqll = "SELECT DISTINCT m.mrfId " + "FROM MRFCandidate mc " + "JOIN mc.mrfRecruiter mrr "
				+ "JOIN mrr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE mc.mrfCandidateId IN :mrfCandidateIds";

		List<Long> mrfIds = entityManager.createQuery(hqll, Long.class).setParameter("mrfCandidateIds", mrfCandidateIds)
				.getResultList();

		String hq = "SELECT COUNT(rp.recruitmentProcessId) FROM RecruitmentProcess rp where rp.mrf.mrfId IN :mrfIds AND rp.recruitmentProcessType = 'assessment'";

		Long rpIds = (Long) entityManager.createQuery(hq, Long.class).setParameter("mrfIds", mrfIds).getSingleResult();

		return rpIds;
	}

	@Override
	public Long interviewCountByCandidateId(Long candidateId) {

		String hql = "SELECT mc.mrfCandidateId " + "FROM MRFCandidate mc " + "JOIN mc.candidate c "
				+ "WHERE c.candidateId = :candidateId";

		List<Long> mrfCandidateIds = entityManager.createQuery(hql, Long.class).setParameter("candidateId", candidateId)
				.getResultList();

		if (mrfCandidateIds.isEmpty()) {
			return null;
		}

		String hqll = "SELECT DISTINCT m.mrfId " + "FROM MRFCandidate mc " + "JOIN mc.mrfRecruiter mrr "
				+ "JOIN mrr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE mc.mrfCandidateId IN :mrfCandidateIds";

		List<Long> mrfIds = entityManager.createQuery(hqll, Long.class).setParameter("mrfCandidateIds", mrfCandidateIds)
				.getResultList();

		String hq = "SELECT COUNT(rp.recruitmentProcessId) FROM RecruitmentProcess rp where rp.mrf.mrfId IN :mrfIds AND rp.recruitmentProcessType = 'interview'";

		Long rpIds = (Long) entityManager.createQuery(hq, Long.class).setParameter("mrfIds", mrfIds).getSingleResult();

		return rpIds;
	}

	@Override
	public List<RecruitProcessDto> levelsOfInterview(Long mrfJdId) {
		String hql = "FROM RecruitmentProcess rp " + "JOIN rp.mrf mrf " + "JOIN mrf.mrfJd mrfJd "
				+ "WHERE mrfJd.mrfJdId = :mrfJdId";

		List<RecruitmentProcess> recruitmentProcesses = entityManager.createQuery(hql, RecruitmentProcess.class)
				.setParameter("mrfJdId", mrfJdId).getResultList();

		List<RecruitProcessDto> dtos = new ArrayList<>();
		for (RecruitmentProcess rp : recruitmentProcesses) {
			RecruitProcessDto dto = new RecruitProcessDto();
			dto.setRecruitmentProcessId(rp.getRecruitmentProcessId());
			dto.setLevel(rp.getLevel());
			dto.setRecruitmentProcessType(rp.getRecruitmentProcessType());
			dto.setRecruitmentProcessName(rp.getRecruitmentProcessName());
			dto.setReport(rp.getReport());
			dto.setCompletedStatus(rp.getCompletedStatus());
			dto.setMrfId(rp.getMrf().getMrfId());
			dtos.add(dto);
		}

		return dtos;
	}

//	@Override
//	public Object getRecruitmentProcessById(Long recruitmentProcessId) {
//		String hql = "SELECT rp.recruitmentProcessType FROM RecruitmentProcess rp where rp.recruitmentProcessId = :recruitmentProcessId";
//		String type = (String) entityManager.createQuery(hql).setParameter("recruitmentProcessId", recruitmentProcessId)
//				.getSingleResult();
//
//		String hq;
//		if (type.equalsIgnoreCase("assessment")) {
//			hq = "FROM Assessment a where a.recruitmentProcess.recruitmentProcessId = :recruitmentProcessId";
//
//		} else {
//			hq = "FROM Interview i where i.recruitmentProcess.recruitmentProcessId = :recruitmentProcessId ";
//		}
//		return null;
//	}

	@Override
	public Object getRecruitmentProcessById(Long recruitmentProcessId, Long candidateId) {
		String hql = "SELECT rp.recruitmentProcessType FROM RecruitmentProcess rp WHERE rp.recruitmentProcessId = :recruitmentProcessId";

		String type = (String) entityManager.createQuery(hql).setParameter("recruitmentProcessId", recruitmentProcessId)
				.getSingleResult();

		System.out.println(type);

		String hq = null;

		if (type.equalsIgnoreCase("assessment")) {
			hq = "SELECT s FROM Score s JOIN s.assessment a WHERE s.candidate.candidateId = :candidateId AND a.recruitmentProcess.recruitmentProcessId = :recruitmentProcessId ";
		} else {
			hq = "SELECT i FROM Interview i WHERE i.candidate.candidateId = :candidateId AND i.recruitmentProcess.recruitmentProcessId = :recruitmentProcessId";
		}

		if (type.equalsIgnoreCase("assessment")) {
			TypedQuery<Score> query = entityManager.createQuery(hq, Score.class);
			query.setParameter("candidateId", candidateId);
			query.setParameter("recruitmentProcessId", recruitmentProcessId);

			Score score = query.getSingleResult();
			ScoreDto dto = new ScoreDto();

			dto.setScore(score.getScore());
			dto.setRemarks(score.getRemarks());
			dto.setStatus(score.getStatus());
			dto.setPasskey(score.getPasskey());

			if (score.getAssessment() != null) {
				dto.setAssessmentName(score.getAssessment().getAssessmentName());
				dto.setAssessmentLink(score.getAssessment().getAssessmentLink());
				dto.setAssessmentType(score.getAssessment().getAssessmentType());
				dto.setAssessmentStartDate(score.getAssessment().getAssessmentStartDate());
				dto.setAssessmentEndDate(score.getAssessment().getAssessmentEndDate());
				dto.setAssessmentStartTime(score.getAssessment().getAssessmentStartTime());
				dto.setAssessmentEndTime(score.getAssessment().getAssessmentEndTime());
			}

			return dto;

		} else {
			TypedQuery<Interview> query = entityManager.createQuery(hq, Interview.class);
			query.setParameter("candidateId", candidateId);
			query.setParameter("recruitmentProcessId", recruitmentProcessId);

			Interview interview = query.getSingleResult();
			InterviewDto dto = new InterviewDto();

			dto.setInterviewToTime(interview.getInterviewToTime());
			dto.setInterviewTitle(interview.getInterviewTitle());
			dto.setInterviewDate(interview.getInterviewDate());
			dto.setInterviewFromTime(interview.getInterviewFromTime());
			dto.setMeetingUrl(interview.getMeetingUrl());
			dto.setOthers(interview.getOthers());
			dto.setCandidateStatus(interview.getCandidateStatus());

			return dto;

		}
	}

	@Override
	public String applyJob(Candidate candidate) {
//		if(candidate != null && candidateResume != null) {
//			byte[] resumeData;
//			try {
//				resumeData = candidateResume.getBytes();
//			} catch (IOException e) {
//				return MessageConstants.RESUME_UPLOAD_FAILURE;
//			}
//			
//			candidate.setCandidateResume(resumeData);
		if (candidate != null) {
			entityManager.persist(candidate);
			return "Candidate added success";

		}

		else {

			return "Candidate failed to add";
		}
	}

	@Override
	public String updateOffer(Long offerId, Long candidateId) {
		String hql = "update Offer o set o.candidateStatus = 'ACCEPTED' where offerId = :offerId and o.candidate.candidateId = :candidateId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("offerId", offerId);
		q.setParameter("candidateId", candidateId);
		int updatedCount = q.executeUpdate();
		if(updatedCount >0) {
			return "Offer accepted successfully";
		}
		else {
			return "Error in accepting the offer";
		}
	}
}
