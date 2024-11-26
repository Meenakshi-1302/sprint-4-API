package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.ClientOrganizationProfileDAO;
import com.rts.tap.model.ClientOrganization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class ClientOrganizationProfileDAOImpl implements ClientOrganizationProfileDAO{

	private EntityManager entityManager;

	public ClientOrganizationProfileDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public ClientOrganization findClientOrganizationByClientId(Long clientId) {
		String hql = "SELECT co FROM ClientOrganization co, Client c WHERE c.clientOrganization.clientOrganizationId = co.clientOrganizationId AND c.clientId = :clientId"; 
		Query q = entityManager.createQuery(hql, ClientOrganization.class);
		q.setParameter("clientId", clientId);
		return (ClientOrganization) q.getSingleResult();
	}

	@Override
	public ClientOrganization updateOrganizationLogo(ClientOrganization clientOrganization) {
		return entityManager.merge(clientOrganization);
	}
	
}
