package com.rts.tap.daoimplementation;
 
import java.util.List;
import org.springframework.stereotype.Repository;
import com.rts.tap.dao.RecruiterDashboardDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
 
@Repository
public class RecruiterDashboardDaoImpl implements RecruiterDashboardDao {
 
	private EntityManager entityManager;
 
	public RecruiterDashboardDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
 
	@Override
	public Long TotalMrfAssignedToRecruiter(Long employeeId) {
		String hql = "SELECT COUNT(mr) FROM MRFRecruiters mr " + "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE mr.recruiterId.employeeId = :employeeId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		return (Long) query.getSingleResult();
	}
 
	@SuppressWarnings("unchecked")
	public List<MRF> getMrfListAssignedToRecruiter(Long employeeId) {
		String hql = "SELECT mrf FROM MRFRecruiters mr " + "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE mr.recruiterId.employeeId = :employeeId";
		Query query = entityManager.createQuery(hql, MRF.class);
		query.setParameter("employeeId", employeeId);
 
		return query.getResultList();
	}
 
	public Long ResolvedMrfAssignedToRecruiter(Long employeeId) {
		String hql = "SELECT COUNT(m) FROM MRFRecruiters mr " + "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE mr.recruiterId.employeeId = :employeeId " + "AND mr.recruiterAssignedStatus = 'Resolved'";
 
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		return (Long) query.getSingleResult();
	}
 
	public Long PendingMrfAssignedToRecruiter(Long employeeId) {
		String hql = "SELECT COUNT(m) FROM MRFRecruiters mr " + "JOIN mr.mrfRecruitingManager mrm " + "JOIN mrm.mrf m "
				+ "WHERE mr.recruiterId.employeeId = :employeeId " + "AND mr.recruiterAssignedStatus = 'Pending'";
 
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		return (Long) query.getSingleResult();
	}
 
	@Override
	public Long TotalCandidatesofRecruiter(Long employeeId) {
		String hql = "SELECT COUNT(c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "WHERE mr.recruiterId.employeeId = :employeeId";
 
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		return (Long) query.getSingleResult();
	}
 
	@Override
	public Long HiredCandidatesofRecruiter(Long employeeId) {
		String hql = "SELECT COUNT(c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "WHERE mr.recruiterId.employeeId = :employeeId " + "AND c.status = 'Joined'";
 
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		return (Long) query.getSingleResult();
	}
 
	@Override
	public Long PendingCandidatesofRecruiter(Long employeeId) {
		String hql = "SELECT COUNT(c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "WHERE mr.recruiterId.employeeId = :employeeId " + "AND c.status != 'Selected' "  ;
 
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		return (Long) query.getSingleResult();
	}
	@Override
	public Long RejectedCandidatesofRecruiter(Long employeeId) {
		String hql = "SELECT COUNT(c) FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "WHERE mr.recruiterId.employeeId = :employeeId " + "AND c.status = 'Rejected'";
 
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		return (Long) query.getSingleResult();
	}
 
	@Override
	public List<Candidate> getCandidateByEmployeeId(Long employeeId) {
		String hql = "SELECT c FROM MRFCandidate mc " + "JOIN mc.candidate c " + "JOIN mc.mrfRecruiter mr "
				+ "WHERE mr.recruiterId.employeeId = :employeeId";
		List<Candidate> results = entityManager.createQuery(hql, Candidate.class).setParameter("employeeId", employeeId)
				.getResultList();
		return results;
	}
 
	@Override
	public List<Offer> getOfferByEmployeeId(Long employeeId) {
		String hql = "SELECT o " + "FROM Offer o " + "JOIN o.mrf m " + "WHERE m.mrfId IN ("
				+ "   SELECT DISTINCT mrm.mrf.mrfId " + "   FROM MRFRecruiters mr "
				+ "   JOIN mr.mrfRecruitingManager mrm " + "   WHERE mr.recruiterId.employeeId = :employeeId" + ")";
 
		List<Offer> results = entityManager.createQuery(hql, Offer.class).setParameter("employeeId", employeeId)
				.getResultList();
		return results;
	}
 
 
	@SuppressWarnings({ "unchecked" })
	public List<Object[]> getResolvedMRFCountByMonth(Long employeeId, int year) {
		String hql = "SELECT MONTH(c.contractStartDate) AS month, COUNT(m.mrfId) AS resolvedCount, m.mrfId "
				+ "FROM MRFRecruiters mr " + "JOIN mr.mrfRecruitingManager.mrf m " + "JOIN m.mrfCriteria c " + 
				"WHERE mr.recruiterId.employeeId = :employeeId " + "AND mr.recruiterAssignedStatus = 'Resolved' "
				+ "AND YEAR(c.contractStartDate) = :year " + "GROUP BY MONTH(c.contractStartDate), m.mrfId "
				+ "ORDER BY month";
 
		return entityManager.createQuery(hql).setParameter("employeeId", employeeId).setParameter("year", year)
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getMonthwiseHiredCountForResolvedMRF(Long employeeId, int year) {
	    String hql = "SELECT MONTH(c.assignedAt) AS month, COUNT(c.candidateId) AS hiredCount " +
	                 "FROM Candidate c " +
	                 "JOIN MRFRecruiters mr ON c.sourceId = mr.mrfRecruitingManager.mrf.mrfId " +
	                 "WHERE c.status = 'hired' " +
	                 "AND mr.recruiterId.employeeId = :employeeId " +
	                 "AND mr.recruiterAssignedStatus = 'resolved' " +
	                 "AND YEAR(c.assignedAt) = :year " +  // Add condition for the year
	                 "GROUP BY MONTH(c.assignedAt) " +
	                 "ORDER BY MONTH(c.assignedAt)";
	    return entityManager.createQuery(hql)
	                        .setParameter("employeeId", employeeId)
	                        .setParameter("year", year)  // Pass the year parameter
	                        .getResultList();
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPendingMRFCountByMonth(Long employeeId, int year) {
		 String hql = "SELECT MONTH(c.contractStartDate) AS month, COUNT(DISTINCT m.mrfId) AS totalMRFCount " +
                 "FROM MRFRecruiters mr " +
                 "JOIN mr.mrfRecruitingManager.mrf m " +
                 "JOIN m.mrfCriteria c " +
                 "WHERE mr.recruiterId.employeeId = :employeeId " +
                 "AND YEAR(c.contractStartDate) = :year " +
                 "GROUP BY MONTH(c.contractStartDate) " +
                 "ORDER BY month";
 
 
		return entityManager.createQuery(hql).setParameter("employeeId", employeeId).setParameter("year", year)
				.getResultList();
	}
 
}