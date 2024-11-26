package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.SubRequirementDAO;
import com.rts.tap.model.SubRequirements;
import com.rts.tap.service.SubRequirementService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SubRequirementServiceImpl implements SubRequirementService {

	private SubRequirementDAO dao;

	public SubRequirementServiceImpl(SubRequirementDAO dao) {
		this.dao = dao;
	}

	@Override
	public String addSubRequirement(SubRequirements subRequirements) {
		if (subRequirements == null) {
			return "Subrequirement not found";
		} else {
			dao.addSubRequirement(subRequirements);
			return "Sub Requirement added successfully";
		}
	}

	@Override
	public List<SubRequirements> viewAllSubRequirements() {
		return dao.viewAllSubRequirements();
	}

}
