package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.Candidate;

public interface DashboardService {

	public Long HiredCandidatesByClientId(Long clientId);

	public Long ShortlistedCandidatesByClientId(Long clientId);

	public Long HiredCandidateList(Long requirementId);

	public Long ShortListedCandidateList(Long requirementId);

	public List<Candidate> hiredPeopleData(Long clientId);

	public List<Candidate> shortListedPeople(Long clientId);

	public List<Candidate> shortListedByRequirment(Long requirementId);

	public List<Candidate> hiredByRequirement(Long requirementId);

}
