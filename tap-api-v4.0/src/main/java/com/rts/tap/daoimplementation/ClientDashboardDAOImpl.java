package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.ClientDashboardDAO;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class ClientDashboardDAOImpl implements ClientDashboardDAO {

	private EntityManager entityManager;

	public ClientDashboardDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Long countHiredCandidatesByClientId(Long clientId) {
		String hql = "SELECT COUNT(c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mrr "
				+ "JOIN mrr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m " + "JOIN m.requirement r "
				+ "WHERE r.client.clientId = :clientId " + "AND mc.Status = 'Hired'";
		Query query = entityManager.createQuery(hql);
		query.setParameter("clientId", clientId);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long countShortlistedCandidatesByClientId(Long clientId) {
		String hql = "SELECT COUNT(c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m " + "JOIN m.requirement r "
				+ "WHERE r.client.clientId = :clientId " + "AND mc.Status = 'Shortlisted'";
		Query query = entityManager.createQuery(hql);
		query.setParameter("clientId", clientId);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long HiredCandidates(Long requirementId) {
		String hql = "SELECT COUNT (c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mrr "
				+ "JOIN mrr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE m.requirement.requirementId = :requirementId AND mc.Status = 'Hired'";

		Query query = entityManager.createQuery(hql);
		query.setParameter("requirementId", requirementId);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long ShortListedCandidates(Long requirementId) {

		String hql = "SELECT COUNT(c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mrr "
				+ "JOIN mrr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE m.requirement.requirementId = :requirementId AND mc.Status = 'Shortlisted'";

		Query query = entityManager.createQuery(hql);
		query.setParameter("requirementId", requirementId);
		return (Long) query.getSingleResult();

	}

	@Override
	public List<Candidate> hiredPeople(Long clientId) {
		// First check if we have any MRFCandidates with 'Hired' status
		String checkMRFCandidate = "SELECT mc FROM MRFCandidate mc WHERE mc.Status = 'Hired'";
		List<MRFCandidate> mrfCandidates = entityManager.createQuery(checkMRFCandidate, MRFCandidate.class)
				.getResultList();
		System.out.println("MRFCandidates with Hired status: " + mrfCandidates.size());

		// Check if candidates are properly mapped
		if (!mrfCandidates.isEmpty()) {
			MRFCandidate firstMrf = mrfCandidates.get(0);
			System.out.println("Candidates in first MRFCandidate: "
					+ (firstMrf.getCandidate() != null ? firstMrf.getCandidate().size() : "null"));
		}

		// Original query with logging
		String hql = "SELECT DISTINCT c FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m " + "JOIN m.requirement r "
				+ "WHERE r.client.clientId = :clientId " + "AND mc.Status = 'Hired'";

		List<Candidate> results = entityManager.createQuery(hql, Candidate.class).setParameter("clientId", clientId)
				.getResultList();

		System.out.println("Final results size: " + results.size());

		return results;
	}

	@Override
	public List<Candidate> shortListedPeople(Long clientId) {
		String checkMRFCandidate = "SELECT mc FROM MRFCandidate mc WHERE mc.Status = 'Shortlisted'";
		List<MRFCandidate> mrfCandidates = entityManager.createQuery(checkMRFCandidate, MRFCandidate.class)
				.getResultList();
		System.out.println("MRFCandidates with Shortlisted status: " + mrfCandidates.size());

		if (!mrfCandidates.isEmpty()) {
			MRFCandidate firstMrf = mrfCandidates.get(0);
			System.out.println("Candidates in first MRFCandidate: "
					+ (firstMrf.getCandidate() != null ? firstMrf.getCandidate().size() : "null"));
		}

		String hql = "SELECT DISTINCT c FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m " + "JOIN m.requirement r "
				+ "WHERE r.client.clientId = :clientId " + "AND mc.Status = 'Shortlisted'";

		List<Candidate> results = entityManager.createQuery(hql, Candidate.class).setParameter("clientId", clientId)
				.getResultList();

		return results;
	}

	@Override
	public List<Candidate> shortListedByRequirment(Long requirementId) {
		String checkMRFCandidate = "SELECT mc FROM MRFCandidate mc WHERE mc.Status = 'Shortlisted'";
		List<MRFCandidate> mrfCandidates = entityManager.createQuery(checkMRFCandidate, MRFCandidate.class)
				.getResultList();
		System.out.println("MRFCandidates with Shortlisted status: " + mrfCandidates.size());

		if (!mrfCandidates.isEmpty()) {
			MRFCandidate firstMrf = mrfCandidates.get(0);
			System.out.println("Candidates in first MRFCandidate: "
					+ (firstMrf.getCandidate() != null ? firstMrf.getCandidate().size() : "null"));
		}

		String hql = "SELECT DISTINCT c FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE m.requirement.requirementId = :requirementId " + "AND mc.Status = 'Shortlisted'";

		List<Candidate> results = entityManager.createQuery(hql, Candidate.class)
				.setParameter("requirementId", requirementId).getResultList();

		return results;
	}

	@Override
	public List<Candidate> hiredByRequirement(Long requirementId) {
		String checkMRFCandidate = "SELECT mc FROM MRFCandidate mc WHERE mc.Status = 'Hired'";
		List<MRFCandidate> mrfCandidates = entityManager.createQuery(checkMRFCandidate, MRFCandidate.class)
				.getResultList();
		System.out.println("MRFCandidates with Hired status: " + mrfCandidates.size());

		if (!mrfCandidates.isEmpty()) {
			MRFCandidate firstMrf = mrfCandidates.get(0);
			System.out.println("Candidates in first MRFCandidate: "
					+ (firstMrf.getCandidate() != null ? firstMrf.getCandidate().size() : "null"));
		}

		String hql = "SELECT DISTINCT c FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE m.requirement.requirementId = :requirementId " + "AND mc.Status = 'Hired'";

		List<Candidate> results = entityManager.createQuery(hql, Candidate.class)
				.setParameter("requirementId", requirementId).getResultList();

		return results;
	}

}
