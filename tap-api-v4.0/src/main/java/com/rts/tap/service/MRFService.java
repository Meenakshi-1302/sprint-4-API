package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.model.OfferApproval;

public interface MRFService {

	MRF addMrf(MRF mrf);

	MRF updateMrf(Long mrfId, MRF mrf);

	String deleteMrfById(long mrfId);

	MRF getMrfById(long mrfId);

	List<MRF> getAllMrf();
	
	int getRequirementFilledCount(Long requirementId);
	
	MRFRecruitingManager assignMrfToRecruitingManager(MRFRecruitingManager mrfRecruitingManager);
	 
	 
		List<Employee> getRecruitingManagersByRoleName();
	 
		List<MRFRecruitingManager> getAssignedMRFs();
	 
		List<OfferApproval> getOfferApprovalsByEmployeeId(Long employeeId);

}
