package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.AssessmentDao;
import com.rts.tap.model.Assessment;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AssessmentDaoImpl implements AssessmentDao {

	private EntityManager entityManager;

	public AssessmentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Assessment save(Assessment assessment) {
		entityManager.persist(assessment);
		return assessment;
	}

	@Override
	public Assessment findById(Long id) {
		return entityManager.find(Assessment.class, id);
	}

	public List<Assessment> findAll() {
		return entityManager.createQuery("SELECT c FROM Assessment c", Assessment.class).getResultList();
	}

	@Override
	public void delete(Long id) {
		Assessment assessment = entityManager.find(Assessment.class, id);
		if (assessment != null) {
			entityManager.remove(assessment);
		}
	}
	
	public Assessment update(Assessment assessment) {
		return entityManager.merge(assessment);
	}
	
	 public List<Assessment> findAssessmentByMrfId(Long id) {
	        String hql = "SELECT a FROM Assessment a WHERE a.recruitmentProcess.mrf.mrfId = :mrfId ORDER By a DESC";
	        return entityManager.createQuery(hql, Assessment.class)
	                            .setParameter("mrfId", id)
	                            .getResultList();
	    }
}
