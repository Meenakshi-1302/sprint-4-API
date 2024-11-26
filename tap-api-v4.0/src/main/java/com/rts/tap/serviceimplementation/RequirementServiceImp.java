package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.RequirementDAO;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Requirement;
import com.rts.tap.service.RequirementService;

@Service
public class RequirementServiceImp implements RequirementService {

	private final RequirementDAO requirementDAO;

	public RequirementServiceImp(RequirementDAO requirementDAO) {
		this.requirementDAO = requirementDAO;
	}

	@Override
	public String createRequirement(Requirement requirement, Long clientId) {
		return requirementDAO.addRequirement(requirement, clientId);
	}

	@Override
	public String changeRequirement(Requirement requirementDTO) {
		return requirementDAO.updateRequirement(requirementDTO);
	}

	@Override
	public String removeRequirement(Long requirementId) {
		return requirementDAO.deleteRequirement(requirementId);
	}

	@Override
	public List<Requirement> findAllRequirements() {
		return requirementDAO.getAllRequirements();
	}

	@Override
	public List<Requirement> RequirementsByClient(Long clientId) {
		return requirementDAO.getAllRequirementsByClient(clientId);
	}

	@Override
	public Integer requirementCount(Long clientId) {
		return requirementDAO.requirementCount(clientId);
	}

	@Override
	public List<MRF> HiredCandidateList(Long requirementId) {
		return requirementDAO.HiredCandidates(requirementId);
	}

	@Override
	public List<Requirement> getRequirementsByClientId(Long clientId) {
		return requirementDAO.getAllRequirementsByClientId(clientId);
	}

	@Override
	public Requirement findRequirementsById(Long requirementId) {
		return requirementDAO.getRequirementById(requirementId);
	}

}

