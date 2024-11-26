package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.MRFRecruiters;
import com.rts.tap.model.MRFRecruitingManager;

public interface ClientDashboardDAO {

	public Long countHiredCandidatesByClientId(Long clientId);

	public Long countShortlistedCandidatesByClientId(Long clientId);

	public Long HiredCandidates(Long requirementId);

	public Long ShortListedCandidates(Long requirementId);

	public List<Candidate> hiredPeople(Long clientId);

	public List<Candidate> shortListedPeople(Long clientId);
	
	public List<Candidate> shortListedByRequirment(Long requirementId);
	
	public List<Candidate> hiredByRequirement(Long requirementId);

}
