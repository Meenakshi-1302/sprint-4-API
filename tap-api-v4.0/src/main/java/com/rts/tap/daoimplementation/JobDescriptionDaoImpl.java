package com.rts.tap.daoimplementation;
 
import java.util.List;
 
import org.springframework.stereotype.Repository;
 
import com.rts.tap.dao.JobDescriptionDAO;
import com.rts.tap.model.JobDescription;
import com.rts.tap.model.MrfJd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
 
@Repository
@Transactional
public class JobDescriptionDaoImpl implements JobDescriptionDAO{
	EntityManager entityManager;
 
	public JobDescriptionDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
 
	@Override
	public JobDescription saveJobDescription(JobDescription jobDescription) {
		entityManager.persist(jobDescription);
		return jobDescription;
	}
	@Override
	public JobDescription updateJobDescription(JobDescription jobDescription) {
		entityManager.merge(jobDescription);
		return jobDescription;
	}
 
	@Override
	public List<JobDescription> viewAllJobDescriptions() {
		Query query = entityManager.createQuery("From JobDescription");
		return query.getResultList();
	}
 
	@Override
	public JobDescription viewByJobDescriptionId(Long jobDescriptionId) {
		return entityManager.find(JobDescription.class, jobDescriptionId);
	}
 
	@Override
	public JobDescription viewByJobTitle(String JobTitle) {
		Query query = entityManager.createQuery("From JobDescription where jobTitle =: name");
		query.setParameter("name", JobTitle);
		return (JobDescription) query.getSingleResult();
	}
 
	@Override
	public List<String> viewAllJobTitles() {
		Query query = entityManager.createQuery("Select jobTitle from JobDescription");
		return query.getResultList();
	}

	@Override
	public MrfJd saveJobDescription(MrfJd mrfJd) {
		entityManager.persist(mrfJd);
		return mrfJd;
	}
 
}