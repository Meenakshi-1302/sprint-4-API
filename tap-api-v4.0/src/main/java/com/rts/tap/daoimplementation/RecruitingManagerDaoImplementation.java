package com.rts.tap.daoimplementation;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.MRFDao;
import com.rts.tap.dao.RecruitingManagerDao;
import com.rts.tap.dao.VendorDao;
import com.rts.tap.dto.MRFRecruiterDto;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruiters;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFVendor;
import com.rts.tap.model.Vendor;
import com.rts.tap.model.VendorPerformance;
import com.rts.tap.model.VendorPerformanceHistory;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * author: Jeevarajan Rajarajacholan, Vashanth version: v2.0 updated at:
 * 12-11-2024
 **/

@Repository
@Transactional
public class RecruitingManagerDaoImplementation implements RecruitingManagerDao {

	EntityManager entityManager;
	VendorDao vendorDao;
	MRFDao mrfDao;

	public RecruitingManagerDaoImplementation(EntityManager entityManager, VendorDao vendorDao, MRFDao mrfDao) {
		super();
		this.entityManager = entityManager;
		this.vendorDao = vendorDao;
		this.mrfDao = mrfDao;
	}

	/**
	 * this method will accept recruiting manager id and perform fetch operation and
	 * return Employee as object
	 * 
	 * @param(recruiting manager's id) - long
	 * @return Employee - object
	 **/
	@Override
	public Employee getEmployeeById(long id) {
		return entityManager.find(Employee.class, id);
	}

	/**
	 * this method will accept recruiting manager id and perform fetch operation and
	 * return all Mrf's assigned for Recruiting manager
	 * 
	 * @param(recruiting manager's id) - long
	 * @return list of MrfRecruiting Manager's - object
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public List<MRFRecruitingManager> getAllMrfsAssignedForRM(long id) {
		String hql = "SELECT mrfs FROM MRFRecruitingManager mrfs WHERE recruitingManager.id = :id";
		Query query = entityManager.createQuery(hql).setParameter("id", id);
		List<MRFRecruitingManager> results = query.getResultList();
		return results != null ? results : Collections.emptyList();
	}

	/**
	 * this method will accept Mrf's id and perform fetch operation and return MRF
	 * as object
	 * 
	 * @param(Mrf's id) - long
	 * @return MRF - object
	 **/
	@Override
	public MRF getMrfById(long id) {
		return entityManager.find(MRF.class, id);
	}

	/**
	 * this method will accept MrfVendor DTo and perform add operation and return
	 * MRF as object
	 * 
	 * @param(Mrf's id) - long
	 * @return MRF - object
	 **/
	@Override
	public String assignMrfToVendor(MRFVendorDto mrfVendorDto) {
		String mrfStatus = "";
		try {
			MRF mrf = getMrfById(mrfVendorDto.getMrfId());
			Vendor vendor = vendorDao.findById(mrfVendorDto.getVendorId());
			Employee rm = getEmployeeById(mrfVendorDto.getRecrutingManagerId());
			MRFVendor mrfVendor = new MRFVendor();
			mrf.getMrfStatus().setMrfStage("Assigned");
			mrfVendor.setMrf(mrf);
			mrfVendor.setRecruitingManager(rm);
			mrfVendor.setVendor(vendor);
			mrfVendor.setAssignedCount(mrfVendorDto.getAssignedCount());
			mrfVendor.setAssignedDate(new Date());
			mrfVendor.setVendorAssignedStatus(MessageConstants.RECRUITING_MANAGER_MRF_STATUS_ASSIGNED);
			entityManager.persist(mrfVendor);
			VendorPerformanceHistory vendorPerformanceHistory = new VendorPerformanceHistory();
			vendorPerformanceHistory.setVendor(vendor);
			vendorPerformanceHistory.setMrf(mrf);
			VendorPerformance vendorPerformance = new VendorPerformance();
			vendorPerformance.setVendor(vendor);
			entityManager.persist(vendorPerformance);
			entityManager.persist(vendorPerformanceHistory);
			mrfStatus = MessageConstants.RECRUITING_MANAGER_ASSIGNED_MRF_SUCCESS;
		} catch (Exception e) {
			System.err.println(e);
			mrfStatus = MessageConstants.RECRUITING_MANAGER_ASSIGNED_MRF_FAILED;
		}
		return mrfStatus;
	}

	/**
	 * this method will perform fetch operation of all Mrf assigned to vendors and
	 * return MRFVendor as object
	 * 
	 * @return list of MRFVendor - object
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public List<MRFVendor> getAllMrfsVendors() {
		String hql = "SELECT mrfVen FROM MRFVendor mrfVen";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MRFRecruiters> getAllRecruitersAssigned() {
		String hql = "SELECT recruiters FROM MRFRecruiters recruiters";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	public MRFRecruitingManager getMrfRecruitingManagerById(long id) {
		return entityManager.find(MRFRecruitingManager.class, id);
	}

	@Override
	public String assignMrfToRecruiter(MRFRecruiterDto mrfRecruiterDto) {
		String mrfStatus = "";
		try {
			MRFRecruitingManager mrfrm = getMrfRecruitingManagerById(mrfRecruiterDto.getMrfRecruitingManagerId());
			Employee recruiter = getEmployeeById(mrfRecruiterDto.getRecruiterId());
			mrfrm.getMrf().getMrfStatus().setMrfStage("Assigned");
			MRFRecruiters mrfrecruiter = new MRFRecruiters();
			mrfrecruiter.setRecruiterId(recruiter);
			mrfrecruiter.setMrfRecruitingManager(mrfrm);
			mrfrecruiter.setCreatedAt(mrfRecruiterDto.getCreatedAt());
			mrfrecruiter.setUpdatedAt(mrfRecruiterDto.getUpdatedAt());
			mrfrecruiter.setClosedDate(mrfRecruiterDto.getClosedDate());
			mrfrecruiter.setAssignedCount(mrfRecruiterDto.getAssignedCount());
			mrfrecruiter.setRecruiterAssignedStatus(MessageConstants.RECRUITER_MRF_STATUS_ASSIGNED);
			entityManager.persist(mrfrecruiter);
//			vendorPerformanceHistory.getAssignedDate(LocalDateTime.now());
			mrfStatus = MessageConstants.RECRUITER_MRF_STATUS_ASSIGNED;
		} catch (Exception e) {
			System.err.println(e);
			mrfStatus = MessageConstants.RECRUITER_ASSIGNED_MRF_FAILED;
		}
		return mrfStatus;
	}

	@Override
	public String updateMrfRecruiter(MRFRecruiterDto mrfRecruiterDto, long id) {
		String mrfStatus = "";
		try {
			MRFRecruiters mrfrecruiter = entityManager.find(MRFRecruiters.class, id);

			if (mrfrecruiter != null) {
				// Update the fields
				mrfrecruiter.setRecruiterAssignedStatus(mrfRecruiterDto.getRecruiterAssignedStatus());
				mrfrecruiter.setUpdatedAt(new Date()); // Set the updatedAt field

				// Set other fields (if necessary)
				mrfrecruiter.setClosedDate(mrfRecruiterDto.getClosedDate());

				// Persist the changes
				entityManager.merge(mrfrecruiter);

				mrfStatus = MessageConstants.RECRUITER_MRF_UPDATED;
			} else {
				mrfStatus = MessageConstants.RECRUITER_MRF_NOT_FOUND;
			}
		} catch (Exception e) {
			System.err.println(e);
			mrfStatus = MessageConstants.RECRUITER_MRF_UPDATE_FAILED;
		}
		return mrfStatus;
	}

	@Override
	public String reassignMrfToRecruiter(MRFRecruiterDto mrfRecruiterDto) {
		String mrfStatus = "";
		try {
			Employee newRecruiter = getEmployeeById(mrfRecruiterDto.getRecruiterId());

			// Find the existing MRFRecruiters entry
			MRFRecruiters existingMrfrecruiter = findMrfRecruiterByMrfId(mrfRecruiterDto.getMrfRecruitingManagerId());

			if (existingMrfrecruiter != null) {
				existingMrfrecruiter.setRecruiterId(newRecruiter);
				existingMrfrecruiter.setUpdatedAt(mrfRecruiterDto.getUpdatedAt());
				existingMrfrecruiter.setClosedDate(mrfRecruiterDto.getClosedDate());
				existingMrfrecruiter.setRecruiterAssignedStatus(MessageConstants.RECRUITER_MRF_STATUS_REASSIGNED);

				entityManager.merge(existingMrfrecruiter);
				mrfStatus = MessageConstants.RECRUITER_MRF_STATUS_REASSIGNED;
			} else {
				mrfStatus = MessageConstants.RECRUITER_ASSIGNED_MRF_FAILED;
			}
		} catch (Exception e) {
			System.err.println(e);
			mrfStatus = MessageConstants.RECRUITER_ASSIGNED_MRF_FAILED;
		}
		return mrfStatus;
	}

	@Override
	public MRFRecruiters findMrfRecruiterByMrfId(long mrfRecruitingManagerId) {
		try {
			return entityManager.createQuery(
					"SELECT mr FROM MRFRecruiters mr WHERE mr.mrfRecruitingManager.id = :mrfRecruitingManagerId",
					MRFRecruiters.class).setParameter("mrfRecruitingManagerId", mrfRecruitingManagerId)
					.getSingleResult();
		} catch (NoResultException e) {
			return null; // No MRFRecruiters found for the given ID
		} catch (Exception e) {
			System.err.println(e);
			return null; // Handle other exceptions
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllRecruitersByRecruitingManagerId(long id) {
		String hql = "SELECT recruiters FROM Employee recruiters WHERE recruiters.managerId = :id";
		Query query = entityManager.createQuery(hql).setParameter("id", id);
		List<Employee> results = query.getResultList();
		return results != null ? results : Collections.emptyList();
	}

	@Override
	public void updateMrfStage(String mrfStage, Long mrfId) {
		MRF mrf = mrfDao.findById(mrfId);
		mrf.getMrfStatus().setMrfStage(mrfStage);
		entityManager.merge(mrf);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MRFRecruiters> getAssignedRecruitersByMrfRecruitingManagerId(long id) {
		String hql = "SELECT recruiters FROM MRFRecruiters recruiters WHERE recruiters.mrfRecruitingManager.mrfRecruitingManagerId = :id";
		Query query = entityManager.createQuery(hql).setParameter("id", id);
		List<MRFRecruiters> results = query.getResultList();
		return results != null ? results : Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MRFVendor> getAllVendorsAssignedForMrf(long mrfId) {
		String hql = "SELECT vendors FROM MRFVendor vendors WHERE vendors.mrf.mrfId = :mrfId";
		Query query = entityManager.createQuery(hql).setParameter("mrfId", mrfId);
		List<MRFVendor> results = query.getResultList();
		return results != null ? results : Collections.emptyList();

	}

	// AUTHOR: S PRINCE AROCKIA SAMUEL
	// TO ADD CANDIDATE
	@Override
	public String addCandidate(Candidate candidate) throws MessagingException {
		if (candidate != null) {
			entityManager.persist(candidate); // Saves the candidate to the database
			return MessageConstants.CANDIDATE_ADDED_SUCCESS; // Return success message
		} else {
			return MessageConstants.CANDIDATE_ADDED_FAILURE; // Handle candidate being null
		}
	}

	// TO GET ALL THE CANDIDATES BY SOURCEID WHICH HAS THE SOURCE AS REFERRAL
	@Override
	public List<Candidate> findCandidatesBySourceIdAndSource(long sourceId, String source) {
		String jpql = "SELECT c FROM Candidate c WHERE c.sourceId = :sourceId AND c.source = :source";
		TypedQuery<Candidate> query = entityManager.createQuery(jpql, Candidate.class);
		query.setParameter("sourceId", sourceId);
		query.setParameter("source", source);

		return query.getResultList();
	}	
}
