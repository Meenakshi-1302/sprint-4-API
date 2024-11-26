package com.rts.tap.daoimplementation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.VendorDao;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.emailservice.VendorMailService;
import com.rts.tap.model.ThirdPartyCredentitals;
import com.rts.tap.model.ThirdPartyRole;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.model.Vendor;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

/**
 * author: Jeevarajan Rajarajacholan, Vashanth version: v2.0 updated at:
 * 29-10-2024
 **/

@Repository
@Transactional
public class VendorDaoImplementation implements VendorDao {

	private EntityManager entityManager;
	private VendorMailService mailService;

	public VendorDaoImplementation(EntityManager entityManager, VendorMailService mailService) {
		super();
		this.entityManager = entityManager;
		this.mailService = mailService;
	}

	@Override
	public Vendor save(VendorDto vendor) throws MessagingException {
		Vendor newVendor = new Vendor();
		newVendor.setOrganizationName(vendor.getOrganizationName());
		ThirdPartyCredentitals tpc = new ThirdPartyCredentitals();
		tpc.setUsername(vendor.getUsername());
		tpc.setEmail(vendor.getEmail());
		tpc.setPassword(vendor.getPassword());
		newVendor.setTaxIdentifyNumber(vendor.getTaxIdentifyNumber());
		ThirdPartyRole role;
		try {
			String hql = "select r from ThirdPartyRole r where role = :vendor";
			Query query = entityManager.createQuery(hql).setParameter("vendor", "Vendor");
			role = (ThirdPartyRole) query.getSingleResult();
		} catch (NoResultException e) {
			role = new ThirdPartyRole();
			role.setRole("Vendor");
			entityManager.persist(role);
		}
		tpc.setRole(role);
		entityManager.persist(tpc);
		newVendor.setThirdPartyCredentitals(tpc);
		entityManager.persist(newVendor);
		mailService.sendVendorCredentials(vendor);
		return newVendor;
	}

	@Override
	public Vendor findById(Long id) {
		return entityManager.find(Vendor.class, id);
	}

	@Override
	public List<Vendor> findAllVendor() {
		return entityManager.createQuery("SELECT v FROM Vendor v", Vendor.class).getResultList();
	}

	@Override
	public String deleteById(Long id) {
		Vendor vendor = findById(id);
		if (vendor != null) {
			entityManager.remove(vendor);
			return MessageConstants.VENDOR_DELETED_SUCCESS;
		} else {
			return MessageConstants.VENDOR_DELETED_FAILED;
		}
	}

	@Override
	public Vendor updateVendor(long id, VendorDto vendor) {
		Vendor existingVendor = findById(id);
		if (existingVendor != null) {
			existingVendor.setAddress(vendor.getAddress());
			existingVendor.setContactName(vendor.getContactName());
			existingVendor.setContactNumber(vendor.getContactName());
			ThirdPartyCredentitals cred = entityManager.find(ThirdPartyCredentitals.class,
					existingVendor.getThirdPartyCredentitals().getThirdPartyCredentialsId());
			cred.setPassword(vendor.getPassword());
			cred.setEmail(vendor.getEmail());
			existingVendor.setThirdPartyCredentitals(cred);
			existingVendor.setContactNumber(vendor.getContactNumber());
			existingVendor.getThirdPartyCredentitals().setEmail(vendor.getEmail());
			existingVendor.setIsPasswordChanged(vendor.getIsPasswordChanged());
			existingVendor.setOrganizationName(vendor.getOrganizationName());
			existingVendor.setTaxIdentifyNumber(vendor.getTaxIdentifyNumber());
			existingVendor.setVendorOrganizationLogo(vendor.getVendorOrganizationLogo());
			return entityManager.merge(existingVendor);
		} else {
			return null;
		}
	}

//	@Override
//	public Vendor login(VendorDto vendorDto) {
//		String username = vendorDto.getUsername();
//	}
	@Override
	public Vendor login(VendorDto vendorDto) {
		String username = vendorDto.getUsername();
		String email = vendorDto.getEmail();
		String password = vendorDto.getPassword();
		if (email != null) {
			String emailLoginQuery = "SELECT v FROM Vendor v WHERE v.thirdPartyCredentitals.email=:email AND v.thirdPartyCredentitals.password = :password";
			Query query = entityManager.createQuery(emailLoginQuery).setParameter("email", email)
					.setParameter("password", password);
			return (Vendor) query.getSingleResult();

		} else {
			String usernameLoginQuery = "SELECT v FROM Vendor v WHERE v.thirdPartyCredentitals.username=:username AND v.thirdPartyCredentitals.password = :password";
			Query query = entityManager.createQuery(usernameLoginQuery).setParameter("username", username)
					.setParameter("password", password);
			return (Vendor) query.getSingleResult();
		}
	}

	@Override
	public String addCandidate(Candidate candidate) {
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
			return MessageConstants.CANDIDATE_ADDED_SUCCESS;

		}
//			return MessageConstants.CANDIDATE_ADDED_SUCCESS;
//		} 
		else {

			return MessageConstants.CANDIDATE_ADDED_FAILURE;
		}
	}

	@Override
	public MRFCandidate findMRFCandidateById(long sourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Date> getAssignedDatesBySourceAndYear(Long sourceId) {
		String hql = "SELECT c.assignedAt FROM Candidate c " + "WHERE c.source = 'vendor' AND c.sourceId = :sourceId";

		List<Date> assignedDates = entityManager.createQuery(hql, Date.class).setParameter("sourceId", sourceId)
				.getResultList();

		return assignedDates;
	}

	@Override
	public void saveAll(List<Candidate> candidates) {
		for (int i = 0; i < candidates.size(); i++) {
			entityManager.persist(candidates.get(i)); // Persist each candidate entity
			if (i % 50 == 0) { // Flush and clear after every 50 entities for performance
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

	// Below code are edited by Nagarjun N S for client dashboard, Updated at
	// 09-11-2024

	@Override
	public Long getCountOfAssignedMrfByVendorId(Long vendorId) {
		String hql = "select count(m) from MRFVendor m where m.vendor.vendorId = :vendorId and m.vendorAssignedStatus = 'ASSIGNED'";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		System.out.println((Long) q.getSingleResult());
		return (Long) q.getSingleResult();
	}

	@Override
	public Long getCountOfCompletedMrfByVendorId(Long vendorId) {
		String hql = "select count(m) from MRFVendor m where m.vendor.vendorId = :vendorId and m.vendorAssignedStatus = 'COMPLETED'";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		System.out.println((Long) q.getSingleResult());

		return (Long) q.getSingleResult();
	}

	@Override
	public Long getCountOfAllMrfByVendorId(Long vendorId) {
		String hql = "select count(m) from MRFVendor m where m.vendor.vendorId = :vendorId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return (Long) q.getSingleResult();
	}

	@Override
	public List<MRFVendor> findAllMRFVendorDetailsByVendorId(Long vendorId) {
		String hql = "SELECT m FROM MRFVendor m WHERE m.vendor.vendorId = :vendorId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return (List<MRFVendor>) q.getResultList();
	}

	@Override
	public List<MRFVendor> getAllMrfAssignedForVendor(Long vendorId) {
		String hql = "SELECT m FROM MRFVendor m WHERE m.vendor.vendorId = :vendorId and m.vendorAssignedStatus = 'ASSIGNED'";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return (List<MRFVendor>) q.getResultList();
	}

	@Override
	public String updateMRFStatus(Long vendorId, Long mrfId, String status) {
		String hql = "update MRFVendor m set m.vendorAssignedStatus=:status WHERE m.vendor.vendorId = :vendorId and m.mrf.mrfId = :mrfId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		q.setParameter("mrfId", mrfId);
		q.setParameter("status", status);
		int updatedCount = q.executeUpdate();
		return (updatedCount > 0) ? "Status Updated" : "Status update failed";
	}

	@Override
	public MRF getMRFDetailsByVendorIdAndMrfId(Long vendorId, Long mrfId) {
		String hql = "select m.mrf from MRFVendor m where m.vendor.vendorId = :vendorId and m.mrf.mrfId = :mrfId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		q.setParameter("mrfId", mrfId);
		return (MRF) q.getSingleResult();
	}

	@Override
	public Candidate getCandidateByVendorAndMrfId(Long vendorId, Long mrfId) {
		String hql = "select c from Candidate c join MRFVendor m on c.source='VENDOR' and c.sourceId = m.vendor.vendorId "
				+ "where c.source = 'VENDOR' and c.sourceId = :vendorId and m.mrf.mrfId = :mrfId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		q.setParameter("mrfId", mrfId);
		return (Candidate) q.getSingleResult();
	}

	@Override
	public Long getCountOfCandidateByVendorAndMrfId(Long vendorId, Long mrfId) {
		String hql = "select count(c) from Candidate c join MRFVendor m on c.source='VENDOR' and c.sourceId = m.vendor.vendorId "
				+ "where c.source = 'VENDOR' and c.sourceId = :vendorId and m.mrf.mrfId = :mrfId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		q.setParameter("mrfId", mrfId);
		return (Long) q.getSingleResult();
	}

	@Override
	public Long getCountOfCandidateByVendorId(Long vendorId) {
		String hql = "select count(c) from Candidate c join MRFVendor m on c.source='VENDOR' and c.sourceId = m.vendor.vendorId "
				+ "where c.source = 'VENDOR' and c.sourceId = :vendorId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return (Long) q.getSingleResult();
	}

	@Override
	public List<Candidate> getHiredAndJoinedCandidatesAssignedByVendorId(Long vendorId) {
		String hql = "select c FROM Candidate c JOIN MRFVendor m ON c.sourceId = m.vendor.vendorId "
				+ "WHERE c.source = 'VENDOR' AND c.sourceId = :vendorId AND (c.status = 'HIRED' OR c.status = 'JOINED')";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return q.getResultList();
	}

	@Override
	public Long getCountOfHiredCandidateByVendorId(Long vendorId) {
		String hql = "SELECT COUNT(c) FROM Candidate c JOIN MRFVendor m ON c.sourceId = m.vendor.vendorId "
				+ "WHERE c.source = 'VENDOR' AND c.sourceId = :vendorId AND c.status = 'HIRED'";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return (Long) q.getSingleResult();
	}

	@Override
	public Long getCountOfJoinedCandidateByVendorId(Long vendorId) {
		String hql = "select count(c) from Candidate c join MRFVendor m on c.sourceId = m.vendor.vendorId "
				+ "where c.source = 'VENDOR' AND c.sourceId = :vendorId AND c.status = 'JOINED'";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return (Long) q.getSingleResult();
	}

	@Override
	public List<Candidate> getAllCandidatesAssignedByVendorAndMrfId(Long vendorId, Long mrfId) {
		String hql = "select c from Candidate c join MRFVendor m on c.sourceId = m.vendor.vendorId "
				+ "where c.source = 'VENDOR' and c.sourceId = :vendorId and m.mrf.mrfId = :mrfId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		q.setParameter("mrfId", mrfId);
		return q.getResultList();
	}

	@Override
	public List<MRFVendor> getRemainingDays(Long vendorId) {
		String hql = "SELECT m FROM MRFVendor m WHERE m.vendor.vendorId = :vendorId AND m.vendorAssignedStatus = 'ASSIGNED'";
		Query q = entityManager.createQuery(hql, MRFVendor.class);
		q.setParameter("vendorId", vendorId);
		return q.getResultList();
	}

//		@Override
//		public Long getMrfRemainingDays(Long mrfId) {
//			String hql = "select m, m.assignedDate, m.mrf.mrfCriteria.closureDate from MRFvendor m where m.mrf.mrfId = :mrfId";
//			Query q = entityManager.createQuery(hql);
//			q.setParameter("mrfId", mrfId);
//			return (Long) q.getSingleResult();
//		}

	@Override
	public String resetPassword(String email, String newPassword) {
		String hql = "update Vendor v SET v.password = :newPassword WHERE email = :email";
		Query q = entityManager.createQuery(hql);
		q.setParameter("email", email);
		q.setParameter("newPassword", newPassword);
		int updatedCount = q.executeUpdate();
		return updatedCount > 0 ? "Password updated successfully" : "Password updation failed";
	}

	@Override
	public boolean vendorEmailExists(String email) {
		String hql = "select v Vendor v where v.email = :email";
		Query q = entityManager.createQuery(hql);
		q.setParameter("email", email);
		Vendor v = (Vendor) q.getSingleResult();

		if (v == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public Vendor findByEmail(String email) {
		String hql = "select v from Vendor v where v.email = :email";
		Query q = entityManager.createQuery(hql);
		q.setParameter("email", email);
		return (Vendor) q.getSingleResult();

	}

	@Override
	public List<Candidate> getAllCandidatesByVendorId(Long vendorId) {
		String hql = "select c from Candidate c join MRFVendor m on c.sourceId = m.vendor.vendorId "
				+ "where c.source = 'VENDOR' and c.sourceId = :vendorId";
		Query q = entityManager.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		return q.getResultList();
	}

	@Override
	public Long getVendorIdByEmail(String email) {
		// First query: Get the thirdPartyCredentitals using the email
		String hql = "select tr from Vendor v join v.thirdPartyCredentitals tr where tr.email = :email";
		Query q = entityManager.createQuery(hql);
		q.setParameter("email", email);
		ThirdPartyCredentitals thirdPartyCredentials = (ThirdPartyCredentitals) q.getSingleResult();

		// Second query: Get the vendorId using the thirdPartyCredentials
		String h = "select v.vendorId from Vendor v where v.thirdPartyCredentitals = :thirdPartyCredentials";
		Query qq = entityManager.createQuery(h);
		qq.setParameter("thirdPartyCredentials", thirdPartyCredentials); // Set parameter properly
		return (Long) qq.getSingleResult();
	}

}
