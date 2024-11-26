package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.SubRequirements;

public interface SubRequirementService {

	public String addSubRequirement(SubRequirements subRequirements);

	public List<SubRequirements> viewAllSubRequirements();

}
