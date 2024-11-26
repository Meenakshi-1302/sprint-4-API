package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.ClientProfileDAO;
import com.rts.tap.model.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class ClientProfileDAOImpl implements ClientProfileDAO {

	private EntityManager entityManager;

	public ClientProfileDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Client getClientById(Long clientId) {
		return entityManager.find(Client.class, clientId);
	}

	@Override
	public Client getClientByEmail(String clientEmail) {
		String hql = "From Client where clientEmail = :clientEmail";
		Query q = entityManager.createQuery(hql);
		q.setParameter("clientEmail", clientEmail);
		return (Client) q.getSingleResult();
	}

	@Override
	public Client updateClientById(Long clientId, String clientPosition, String clientMobile) {
		Client client = getClientById(clientId);
		
		client.setClientPosition(clientPosition);
		client.setClientMobile(clientMobile);
		
		return entityManager.merge(client);
	}

	@Override
	public Client updateClientByEmail(String clientEmail, String clientPosition, String clientMobile) {
		Client client = getClientByEmail(clientEmail);
	
		client.setClientPosition(clientPosition);
		client.setClientMobile(clientMobile);
		
		return entityManager.merge(client);
	}

	
}
