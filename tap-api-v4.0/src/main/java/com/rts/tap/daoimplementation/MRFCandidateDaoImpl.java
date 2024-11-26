package com.rts.tap.daoimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rts.tap.dao.MRFCandidateDao;
import com.rts.tap.dao.ScoreDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.Score;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class MRFCandidateDaoImpl implements MRFCandidateDao {

	private EntityManager entityManager;

	private ScoreDao scoreDao;

	public MRFCandidateDaoImpl(EntityManager entityManager, ScoreDao scoreDao) {
		super();
		this.entityManager = entityManager;
		this.scoreDao = scoreDao;
	}

	public List<MRFCandidate> getAllCandidates() {
		return entityManager.createQuery("from MRFCandidate", MRFCandidate.class).getResultList();
	}

	public MRFCandidate getCandidateById(Long id) {
		return entityManager.find(MRFCandidate.class, id);
	}

	public MRFCandidate saveCandidate(MRFCandidate mrfCandidate) {
		if (mrfCandidate.getMrfCandidateId() == null) {
			entityManager.persist(mrfCandidate);
		} else {
			entityManager.merge(mrfCandidate);
		}
		return mrfCandidate;
	}

	public void deleteCandidate(Long id) {
		MRFCandidate mrfCandidate = getCandidateById(id);
		if (mrfCandidate != null) {
			entityManager.remove(mrfCandidate);
		}
	}

	public List<Candidate> getRemainingCandidates(Long mrfId) {

		String hql = "SELECT mc FROM MRFCandidate mc WHERE mc.mrfRecruiter.mrfRecruitingManager.mrf.mrfId = :mrfId";
		MRFCandidate allCandidates = entityManager.createQuery(hql, MRFCandidate.class).setParameter("mrfId", mrfId)
				.getSingleResult();

		List<Score> scores = scoreDao.findByMrfId(mrfId);
		List<Long> scoredCandidateIds = new ArrayList<>();

		for (Score score : scores) {
			scoredCandidateIds.add(score.getCandidate().getCandidateId());
		}

		List<Candidate> remainingCandidates = new ArrayList<>();
		for (Candidate c : allCandidates.getCandidate()) {
			if (!scoredCandidateIds.contains(c.getCandidateId()) && "Screened".equals(c.getStatus())) {
				remainingCandidates.add(c);
			}
		}

		return remainingCandidates;
	}

	public List<Candidate> getSelectedCandidates(Long mrfId) {

		String hql = "SELECT mc FROM MRFCandidate mc WHERE mc.mrfRecruiter.mrfRecruitingManager.mrf.mrfId = :mrfId";
		MRFCandidate allCandidates = entityManager.createQuery(hql, MRFCandidate.class).setParameter("mrfId", mrfId)
				.getSingleResult();

		List<Candidate> selectedCandidates = new ArrayList<>();
		for (Candidate c : allCandidates.getCandidate()) {
			if ("Selected".equals(c.getStatus())) {
				selectedCandidates.add(c);
			}
		}

		return selectedCandidates;
	}

}
