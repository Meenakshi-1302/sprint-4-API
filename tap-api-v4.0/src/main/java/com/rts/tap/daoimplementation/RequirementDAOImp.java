package com.rts.tap.daoimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.RequirementDAO;
import com.rts.tap.model.Client;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Requirement;
import com.rts.tap.model.SubRequirements;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RequirementDAOImp implements RequirementDAO {

	@Autowired
	private EntityManager entityManager;

//	@Override
//	public String addRequirement(Requirement requirementDTO, Long clientId) {
//		if (requirementDTO == null) {
//			return MessageConstants.ADD_REQUIREMENT_FAILURE;
//		}
//
//		if (clientId == null) {
//			return "Client ID cannot be null";
//		}
//
//		Requirement requirement = requirementDTO;
//		Client client = entityManager.find(Client.class, clientId);
//
////		if (client != null) {
//			requirement.setClient(client);
//			entityManager.persist(requirement);
//			return MessageConstants.ADD_REQUIREMENT_SUCCESS;
////		} else {
////			return "Client not found";
////		}
//	}

	@Override
	public String addRequirement(Requirement requirementDTO, Long clientId) {
		if (requirementDTO == null) {
			return MessageConstants.ADD_REQUIREMENT_FAILURE;
		}

		if (clientId == null) {
			return "Client ID cannot be null";
		}

		// Find the client
		Client client = entityManager.find(Client.class, clientId);
		if(client != null) {
			requirementDTO.setClient(client);

		} else {
			return "Client Not Found";
		}

		if (requirementDTO.getSubrequirement() != null) {
			for (SubRequirements subRequirement : requirementDTO.getSubrequirement()) {
				if (subRequirement.getRole() == null) { 
					return "subrequirements required";
				} else {
					entityManager.persist(subRequirement);
				}
			}
		}

		// Now persist the requirement
		entityManager.persist(requirementDTO);
		return MessageConstants.ADD_REQUIREMENT_SUCCESS;
	}

//	@Override
//	public Requirement toEntity(Requirement requirementDTO) {
//		Requirement requirement = new Requirement();
//
//		Long clientId = requirementDTO.getClientId();
//
//		Client client = entityManager.find(Client.class, clientId);
//
//		requirement.setRequiredResourceCount(requirementDTO.getRequiredResourceCount());
//		requirement.setProbableDesignation(requirementDTO.getProbableDesignation());
//		requirement.setRequiredSkills(requirementDTO.getRequiredSkills());
//		requirement.setRequiredExperience(requirementDTO.getRequiredExperience());
//		requirement.setJobLocation(requirementDTO.getJobLocation());
//		requirement.setTimeline(requirementDTO.getTimeline());
//		requirement.setMinimumBudget(requirementDTO.getMinimumBudget());
//		requirement.setMaximumBudget(requirementDTO.getMaximumBudget());
//		requirement.setClient(client);
//		return requirement;
//	}

	@Override
	public String updateRequirement(Requirement requirementDTO) {
		if (requirementDTO != null && requirementDTO.getRequirementId() != null) {
			Requirement existingRequirement = entityManager.find(Requirement.class, requirementDTO.getRequirementId());

			if (existingRequirement != null) {
//				existingRequirement.setRequiredResourceCount();
//				existingRequirement.setProbableDesignation(requirementDTO.getProbableDesignation());
//				existingRequirement.setRequiredSkills(requirementDTO.getRequiredSkills());
//				existingRequirement.setRequiredExperience(requirementDTO.getRequiredExperience());
//				existingRequirement.setJobLocation(requirementDTO.getJobLocation());
//				existingRequirement.setTimeline(requirementDTO.getTimeline());
//				existingRequirement.setMinimumBudget(requirementDTO.getMinimumBudget());
//				existingRequirement.setMaximumBudget(requirementDTO.getMaximumBudget());
//				entityManager.merge(requirementDTO);

				if (requirementDTO.getClient().getClientId() != null) {
					Client client = entityManager.find(Client.class, requirementDTO.getClient().getClientId());
					existingRequirement.setClient(client);
				}

				entityManager.merge(existingRequirement);
				return MessageConstants.UPDATE_SUCCESS;
			} else {
				return "Requirement not found";
			}
		}
		return MessageConstants.UPDATE_FAILURE;
	}

	@Override
	public String deleteRequirement(Long requirementId) {
		Requirement requirement = entityManager.find(Requirement.class, requirementId);
		if (requirement != null) {
			entityManager.remove(requirement);
			return MessageConstants.DELETE_SUCCESS;
		}
		return MessageConstants.DELETE_FAILURE;
	}

	@Override
	public List<Requirement> getAllRequirements() {
		List<Requirement> requirements = entityManager.createQuery("SELECT r FROM Requirement r", Requirement.class)
				.getResultList();

//		List<RequirementDTO> requirementDTOs = new ArrayList<>();
//		for (Requirement requirement : requirements) {
//			RequirementDTO dto = toDTO(requirement);
//			requirementDTOs.add(dto);
//		}

		return requirements;
	}

//	@Override
//	public RequirementDTO toDTO(Requirement requirement) {
//		RequirementDTO dto = new RequirementDTO();
//		dto.setRequirementId(requirement.getRequirementId());
//		dto.setRequiredResourceCount(requirement.getRequiredResourceCount());
//		dto.setProbableDesignation(requirement.getProbableDesignation());
//		dto.setRequiredSkills(requirement.getRequiredSkills());
//		dto.setRequiredExperience(requirement.getRequiredExperience());
//		dto.setJobLocation(requirement.getJobLocation());
//		dto.setTimeline(requirement.getTimeline());
//		dto.setMinimumBudget(requirement.getMinimumBudget());
//		dto.setMaximumBudget(requirement.getMaximumBudget());
//
//		if (requirement.getClient() != null) {
//			dto.setClientId(requirement.getClient().getClientId());
//		}
//
//		return dto;
//	}

	@Override
	public List<Requirement> getAllRequirementsByClient(Long clientId) {
		if (clientId == null) {
			return new ArrayList<>(); // Return an empty list if clientId is null
		}

		List<Requirement> requirements = entityManager
				.createQuery("SELECT r FROM Requirement r WHERE r.client.clientId = :clientId", Requirement.class)
				.setParameter("clientId", clientId) // Bind the clientId parameter
				.getResultList();

		List<Requirement> requirementDTOs = new ArrayList<>();
		for (Requirement requirement : requirements) {
			Requirement dto = requirement;
			requirementDTOs.add(dto);
		}

		return requirementDTOs;
	}

	@Override
	public Integer requirementCount(Long clientId) {
		if (clientId == null) {
			return 0;
		}

		Long count = (Long) entityManager
				.createQuery("SELECT COUNT(r) FROM Requirement r WHERE r.client.clientId = :clientId", Long.class)
				.setParameter("clientId", clientId).getSingleResult();

		return count.intValue();
	}

	@Override
	public List<MRF> HiredCandidates(Long mrfId) {
		String hql = "SELECT mc FROM MRF mc WHERE mc.mrfId = :mrfId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("mrfId", mrfId);

		List<MRF> hiredCandidates = query.getResultList();
		return hiredCandidates;
	}

	@Override
	public List<Requirement> getAllRequirementsByClientId(Long clientId) {
		Client client = entityManager.find(Client.class, clientId);
		if (client != null) {
			List<Requirement> requirements = entityManager
					.createQuery("SELECT r FROM Requirement r WHERE r.client.clientId = :clientId", Requirement.class)
					.setParameter("clientId", clientId).getResultList();

//			List<RequirementDTO> requirementDTOs = new ArrayList<>();
//			for (Requirement requirement : requirements) {
//				RequirementDTO dto = toDTO(requirement);
//				requirementDTOs.add(dto);
//			}

			return requirements;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Requirement getRequirementById(Long requirementId) {
		Requirement requirement = entityManager.find(Requirement.class, requirementId);
		if(requirement != null) {
			return requirement;
		}
		else {
			return null;
		}
		
		
	}

}
