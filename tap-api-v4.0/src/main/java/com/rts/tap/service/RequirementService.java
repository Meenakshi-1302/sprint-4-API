package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.MRF;
import com.rts.tap.model.Requirement;

public interface RequirementService {

	public String createRequirement(Requirement requirement, Long clientId);

	public String removeRequirement(Long requirementId);

	public List<Requirement> findAllRequirements();

	public String changeRequirement(Requirement requirementDTO);

	public List<Requirement> RequirementsByClient(Long clientId);

	public Integer requirementCount(Long clientId);

	public List<MRF> HiredCandidateList(Long requirementId);

	public List<Requirement> getRequirementsByClientId(Long clientId);

	public Requirement findRequirementsById(Long requirementId);

//	public Requirement getById(Long requirementId);
//
//	public Requirement getByClientId(Long clientId);

}

