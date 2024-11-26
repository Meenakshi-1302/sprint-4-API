
package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.dao.MRFDao;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFAgreement;
import com.rts.tap.model.MRFCriteria;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.MRFStatus;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.model.Requirement;
import com.rts.tap.utils.DateUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MRFDaoImpl implements MRFDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public MRF mrfSave(MRF mrf) {

		if (mrf.getRequirement().getRequirementId() == null) {
			throw new IllegalArgumentException("Requirement ID cannot be null.");
		}

		if (mrf.getBusinessUnitHead().getEmployeeId() == null) {
			throw new IllegalArgumentException("BusinessUnitHead Employee ID cannot be null.");
		}

		if (mrf.getClientPartner().getEmployeeId() == null) {
			throw new IllegalArgumentException("ClientPartner Employee ID cannot be null.");
		}

		Requirement requirement = entityManager.find(Requirement.class, mrf.getRequirement().getRequirementId());
		if (requirement == null)
			throw new IllegalArgumentException(
					"Requirement not found with ID: " + mrf.getRequirement().getRequirementId());

		Employee businessUnitHead = entityManager.find(Employee.class, mrf.getBusinessUnitHead().getEmployeeId());
		if (businessUnitHead == null)
			throw new IllegalArgumentException(
					"BusinessUnitHead not found with ID: " + mrf.getBusinessUnitHead().getEmployeeId());

		Employee clientPartner = entityManager.find(Employee.class, mrf.getClientPartner().getEmployeeId());
		if (clientPartner == null)
			throw new IllegalArgumentException(
					"ClientPartner not found with ID: " + mrf.getClientPartner().getEmployeeId());

		mrf.setRequirement(requirement);
		mrf.setBusinessUnitHead(businessUnitHead);
		mrf.setClientPartner(clientPartner);
		mrf.setCreatedAt(DateUtils.getCurrentDate());
		mrf.setUpdatedAt(DateUtils.getCurrentDate());

		entityManager.persist(mrf);
		return mrf;
	}

	@Override
	public MRF mrfUpdate(Long mrfId, MRF updatedMRF) {
		// Step 1: Find the existing MRF entity based on the provided mrfId
		MRF existingMRF = entityManager.find(MRF.class, mrfId);
		if (existingMRF == null) {
			throw new IllegalArgumentException("MRF not found with ID: " + mrfId);
		}

		// Step 2: Update fields of the existing MRF object
		existingMRF.setMrfDepartmentName(updatedMRF.getMrfDepartmentName());

		// If the requirement is present, update it
		if (updatedMRF.getRequirement() != null) {
			Requirement requirement = entityManager.find(Requirement.class,
					updatedMRF.getRequirement().getRequirementId());
			if (requirement != null) {
				existingMRF.setRequirement(requirement);
			}
		}

		// Update MRF fields
		if (updatedMRF.getMrfRequiredTechnology() != null) {
			existingMRF.setMrfRequiredTechnology(updatedMRF.getMrfRequiredTechnology());
		}

		if (updatedMRF.getProbableDesignation() != null) {
			existingMRF.setProbableDesignation(updatedMRF.getProbableDesignation());
		}

		if (updatedMRF.getRequiredResourceCount() >= 0) {
			existingMRF.setRequiredResourceCount(updatedMRF.getRequiredResourceCount());
		}

		if (updatedMRF.getRequiredSkills() != null) {
			existingMRF.setRequiredSkills(updatedMRF.getRequiredSkills());
		}

		// Step 3: Update MRFCriteria if it exists
		MRFCriteria existingCriteria = existingMRF.getMrfCriteria();
		if (existingCriteria != null) {
			// Update the fields from updatedMRF if they are not null
			existingCriteria.setEmploymentMode(updatedMRF.getMrfCriteria().getEmploymentMode());
			existingCriteria.setEducationalQualification(updatedMRF.getMrfCriteria().getEducationalQualification());
			existingCriteria.setYearsOfExperience(updatedMRF.getMrfCriteria().getYearsOfExperience());
			existingCriteria.setMinimumCTC(updatedMRF.getMrfCriteria().getMinimumCTC());
			existingCriteria.setMaximumCTC(updatedMRF.getMrfCriteria().getMaximumCTC());
			existingCriteria.setContractStartDate(updatedMRF.getMrfCriteria().getContractStartDate());
			existingCriteria.setClosureDate(updatedMRF.getMrfCriteria().getClosureDate());
			existingCriteria.setJobLocation(updatedMRF.getMrfCriteria().getJobLocation());

			// Persist the updated criteria
			entityManager.merge(existingCriteria);
		}

		// Step 4: Update MRFStatus if it exists
		MRFStatus existingStatus = existingMRF.getMrfStatus();
		if (existingStatus != null) {
			// Update the fields from updatedMRF if they are not null
			existingStatus.setMrfApprovalStatus(updatedMRF.getMrfStatus().getMrfApprovalStatus());
			existingStatus.setDescriptionForChanges(updatedMRF.getMrfStatus().getDescriptionForChanges());
			existingStatus.setRequirementFilled(updatedMRF.getMrfStatus().getRequirementFilled());
			existingStatus.setMrfStage(updatedMRF.getMrfStatus().getMrfStage());
			existingStatus.setMrfType(updatedMRF.getMrfStatus().getMrfType());

			// Persist the updated status
			entityManager.merge(existingStatus);
		}

		// Step 5: Update MRFAgreement if it exists
		MRFAgreement existingAgreement = existingMRF.getMrfAgreement();
		if (existingAgreement != null) {
			// Update the fields from updatedMRF if they are not null
			existingAgreement.setServiceLevelAgreement(updatedMRF.getMrfAgreement().getServiceLevelAgreement());
			existingAgreement.setBillingCycle(updatedMRF.getMrfAgreement().getBillingCycle());
			existingAgreement.setProposedBudget(updatedMRF.getMrfAgreement().getProposedBudget());
			existingAgreement.setNegotiatedPricePoint(updatedMRF.getMrfAgreement().getNegotiatedPricePoint());

			// Persist the updated agreement
			entityManager.merge(existingAgreement);
		}

		// Step 6: Update the timestamp
		existingMRF.setUpdatedAt(DateUtils.getCurrentDate());

		// Step 7: Merge the updated MRF object into the database
		entityManager.merge(existingMRF);

		return existingMRF;
	}



	@Override
	public String mrfDelete(Long mrfId) {
	    // Find the MRF entity by ID
	    MRF mrf = entityManager.find(MRF.class, mrfId);
	    if (mrf != null) {
	        // Get the ids of the related entities before deletion
	        Long criteriaId = mrf.getMrfCriteria() != null ? mrf.getMrfCriteria().getMrfCriteriaId() : null;
	        Long statusId = mrf.getMrfStatus() != null ? mrf.getMrfStatus().getMrfStatusId() : null;
	        Long agreementId = mrf.getMrfAgreement() != null ? mrf.getMrfAgreement().getMrfAgreementId() : null;
 
	        // Delete the related entities (if they exist)
	        if (criteriaId != null) {
	            MRFCriteria criteria = entityManager.find(MRFCriteria.class, criteriaId);
	            if (criteria != null) {
	                entityManager.remove(criteria);
	            }
	        }
	        if (statusId != null) {
	            MRFStatus status = entityManager.find(MRFStatus.class, statusId);
	            if (status != null) {
	                entityManager.remove(status);
	            }
	        }
	        if (agreementId != null) {
	            MRFAgreement agreement = entityManager.find(MRFAgreement.class, agreementId);
	            if (agreement != null) {
	                entityManager.remove(agreement);
	            }
	        }
 
	        // Remove the main MRF entity
	        entityManager.remove(mrf);
	        
	        return "MRF Record and Related Entities Deleted";
	    }
	    return "Record not Found";
	}
	
	@Override
    public int getRequirementFilledCount(Long requirementId) {
        String hql = "SELECT SUM(ms.requirementFilled) " +
                     "FROM MRF m " +
                     "JOIN m.mrfStatus ms " +
                     "WHERE m.requirement.requirementId = :requirementId";
 
        Query query = entityManager.createQuery(hql);
        query.setParameter("requirementId", requirementId);
 
        try {
            Long result = (Long) query.getSingleResult();
            return (result != null) ? result.intValue() : 0;  
        } catch (NoResultException e) {
            return 0;  
        }
    }

	@Override
	public MRF getMrf(long mrfId) {
		return entityManager.find(MRF.class, mrfId);
	}

	@Override
	public List<MRF> getAllMRF() {
		TypedQuery<MRF> query = entityManager.createQuery("SELECT m FROM MRF m", MRF.class);
		return query.getResultList();
	}

	@Override
	public MRF mrfCriteriaSave(MRFCriteria mrfCriteria) {
		entityManager.persist(mrfCriteria);
		return null;
	}

	@Override
	public MRF mrfAgreementSave(MRFAgreement mrfAgreement) {
		entityManager.persist(mrfAgreement);
		return null;
	}

	@Override
	public MRF mrfStatusSave(MRFStatus mrfStatus) {
		entityManager.persist(mrfStatus);
		return null;
	}

	@Override
	public MRF findById(Long mrfId) {
		return entityManager.find(MRF.class, mrfId);
	}
	
	@Override
	public MRFRecruitingManager saveAssignedMRFToRecruitingManager(MRFRecruitingManager mrfRecruitingManager) {
			entityManager.persist(mrfRecruitingManager);
			return mrfRecruitingManager;
	 
		}
	 
		@Override
		public List<Employee> getAllRecruitingManager() {
			TypedQuery<Employee> query = entityManager.createQuery(
					"SELECT e FROM Employee e JOIN e.role r WHERE r.roleName = 'Recruiting Manager'", Employee.class);
			return query.getResultList();
		}
	 
		@Override
		public List<MRFRecruitingManager> findAssignedMRFs() {
			
			String jpql = "SELECT rm FROM MRFRecruitingManager rm " + "JOIN FETCH rm.mrf m "
					+ "JOIN FETCH rm.recruitingManager r " + "WHERE rm.recruitingManagerAssignedStatus = 'Assigned'";
	 
			TypedQuery<MRFRecruitingManager> query = entityManager.createQuery(jpql, MRFRecruitingManager.class);
			return query.getResultList();
		}
	 
		@Override
		public List<OfferApproval> findOfferApprovalsByEmployeeId(Long employeeId) {
			TypedQuery<OfferApproval> query = entityManager.createQuery(
					"SELECT oa FROM OfferApproval oa JOIN oa.approverLevel al WHERE al.employee.employeeId = :employeeId",
					OfferApproval.class);
			query.setParameter("employeeId", employeeId);
			return query.getResultList();
		}
}
