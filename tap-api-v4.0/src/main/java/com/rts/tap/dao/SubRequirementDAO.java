package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.SubRequirements;

public interface SubRequirementDAO {

	public void addSubRequirement(SubRequirements subRequirements);
	public List<SubRequirements> viewAllSubRequirements();
}
