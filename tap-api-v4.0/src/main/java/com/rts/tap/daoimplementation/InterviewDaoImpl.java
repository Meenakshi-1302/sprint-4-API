package com.rts.tap.daoimplementation;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.rts.tap.dao.InterviewDao;
import com.rts.tap.model.Interview;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class InterviewDaoImpl implements InterviewDao {
	EntityManager entityManager;

	public InterviewDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interview> getCandidatesByRecruitmentProcessId(Long recruitmentProcessId) {
		String hql = "select i.candidate from Interview i where i.recruitmentProcess.recruitmentProcessId = :recruitmentProcessId";
		return entityManager.createQuery(hql).setParameter("recruitmentProcessId", recruitmentProcessId)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interview> getCandidatesRecruitmentProcessByCandidateIdAndMrfId(Long candidateId, Long mrfId) {
		String hql = "select i from Interview i where i.candidate.candidateId = :candidateId and i.recruitmentProcess.mrf.mrfId = :mrfId";
		return entityManager.createQuery(hql).setParameter("candidateId", candidateId).setParameter("mrfId", mrfId).getResultList();
	}

	@Override
	public List<Interview> getAllInterviews() {
		 TypedQuery<Interview> query = entityManager.createQuery("SELECT i FROM Interview i", Interview.class);
	        return query.getResultList();
	}
}

