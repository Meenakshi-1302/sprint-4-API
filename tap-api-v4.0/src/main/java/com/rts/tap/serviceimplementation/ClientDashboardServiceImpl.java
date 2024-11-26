package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.ClientDashboardDAO;
import com.rts.tap.model.Candidate;
import com.rts.tap.service.DashboardService;

@Service
public class ClientDashboardServiceImpl implements DashboardService {

	private ClientDashboardDAO clientDAO;

	public ClientDashboardServiceImpl(ClientDashboardDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	@Override
	public Long HiredCandidatesByClientId(Long clientId) {
		return clientDAO.countHiredCandidatesByClientId(clientId);
	}

	@Override
	public Long ShortlistedCandidatesByClientId(Long clientId) {
		return clientDAO.countShortlistedCandidatesByClientId(clientId);
	}

	@Override
	public Long HiredCandidateList(Long requirementId) {
		return clientDAO.HiredCandidates(requirementId);
	}

	@Override
	public Long ShortListedCandidateList(Long requirementId) {
		return clientDAO.ShortListedCandidates(requirementId);
	}

	@Override
	public List<Candidate> hiredPeopleData(Long clientId) {
		return clientDAO.hiredPeople(clientId);
	}

	@Override
	public List<Candidate> shortListedPeople(Long clientId) {
		return clientDAO.shortListedPeople(clientId);
	}

	@Override
	public List<Candidate> shortListedByRequirment(Long requirementId) {
		return clientDAO.shortListedByRequirment(requirementId);
	}

	@Override
	public List<Candidate> hiredByRequirement(Long requirementId) {
		return clientDAO.hiredByRequirement(requirementId);
	}

}
