package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.ApproverLevelDao;
import com.rts.tap.model.ApproverLevel;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ApproverLevelDaoImpl implements ApproverLevelDao {

	EntityManager entityManager;

	public ApproverLevelDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void saveApproverLevel(List<ApproverLevel> approverLevel) {
		for (ApproverLevel approverLevels : approverLevel) {
			entityManager.persist(approverLevels);
		}
	}

	@Override
	public void updateApproverLevel(ApproverLevel approverLevel) {
		entityManager.merge(approverLevel);
	}

	@Override
	public ApproverLevel findApproverLevelById(long approverLevelId) {
		return entityManager.find(ApproverLevel.class, approverLevelId);
	}

	@Override
	public void deleteApproverLevel(long approverLevelId) {
		ApproverLevel approverLevel = entityManager.find(ApproverLevel.class, approverLevelId);
		if (approverLevel != null) {
			entityManager.remove(approverLevel);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApproverLevel> getApproverLevelByMrfId(Long mrfId) {
		String hql = "Select al from ApproverLevel al where al.mrf.mrfId = :mrfId";
		return entityManager.createQuery(hql).setParameter("mrfId", mrfId).getResultList();
	}

	@Override
	public ApproverLevel getApproverLevelByMrfIdAndLevel(Long mrfId, int level) {
		String hql = "Select al from ApproverLevel al where al.mrf.mrfId = :mrfId and al.level = :level";
		return (ApproverLevel) entityManager.createQuery(hql).setParameter("mrfId", mrfId).setParameter("level", level)
				.getSingleResult();
	}

	@Override
	public void saveSingleApproverLevel(ApproverLevel approverLevel) {
		entityManager.persist(approverLevel);
	}

}
