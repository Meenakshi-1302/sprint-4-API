package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.RecruitmentProcessDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.RecruitmentProcess;

import jakarta.persistence.EntityManager;

@Repository
public class RecruitmentProcessDaoImpl implements RecruitmentProcessDao {
	
	EntityManager eManager;

	public RecruitmentProcessDaoImpl(EntityManager eManager) {
		super();
		this.eManager = eManager;
	}

	@Override
	public void setRecruitmentLevel(RecruitmentProcess recruitmentProcess) {
		eManager.persist(recruitmentProcess);
	}

	@Override
	public void updateRecruitmentProcess(RecruitmentProcess recruitmentProcess) {
		eManager.merge(recruitmentProcess);
	}

	@Override
	public RecruitmentProcess findById(Long recruitmentProcessId) {
		return eManager.find(RecruitmentProcess.class, recruitmentProcessId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RecruitmentProcess> findRecruitmentProcessByMrfId(Long mrfId) {
		String hql = "Select rp from RecruitmentProcess rp where rp.mrf.mrfId = :mrfId";
		return eManager.createQuery(hql).setParameter("mrfId", mrfId).getResultList();
	}

	@Override
	public void deleteRecruitmentProcessById(Long recruitmentProcessId) {
		RecruitmentProcess recruitmentProcess = eManager.find(RecruitmentProcess.class, recruitmentProcessId);
		eManager.remove(recruitmentProcess);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidate> findCandidateByRpId(Long rpId) {
		String hql = "Select s.candidate from Score s where s.assessment.recruitmentProcess.recruitmentProcessId = :recruitmentProcessId";
		return eManager.createQuery(hql).setParameter("recruitmentProcessId", rpId).getResultList();
	}
}
