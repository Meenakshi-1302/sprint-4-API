package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.MRF;
import com.rts.tap.model.Requirement;

public interface RequirementDAO {

	public String addRequirement(Requirement requirement, Long clientId);

	public String deleteRequirement(Long requirementId);

	public List<Requirement> getAllRequirements();

	public String updateRequirement(Requirement requirementDTO);

	public List<Requirement> getAllRequirementsByClient(Long clientId);

//	public Requirement toEntity(Requirement requirementDTO);
//
//	public Requirement toDTO(Requirement requirement);

	public Integer requirementCount(Long clientId);

	public List<MRF> HiredCandidates(Long requirementId);

	public List<Requirement> getAllRequirementsByClientId(Long clientId);

	public Requirement getRequirementById(Long requirementId);


}
