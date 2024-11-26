package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.ClientResetPasswordDAO;
import com.rts.tap.model.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

@Repository
public class ClientResetPasswordDAOImpl implements ClientResetPasswordDAO{
	
	private EntityManager entityManager;

	public ClientResetPasswordDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public String resetPasswordByClientEmail(String clientEmail, String newPassword) {
		String hql = "update Client set password =:newPassword where clientEmail = :clientEmail";
		Query q = entityManager.createNativeQuery(hql, Client.class);
		q.setParameter("clientEmail",clientEmail );
		q.setParameter("newPassword", newPassword);
		
		int updatedCount = q.executeUpdate();
		return updatedCount > 0 ? "Password Reset Successfully" : "Password Reset Failure";
	}

	@Override
	public String addClient(Client client) {
		if(client != null) {
			entityManager.merge(client);
			return MessageConstants.CLIENT_ADD_SUCCESS;
		}
		else
			return MessageConstants.CLIENT_ADD_FAILURE;
	}

	@Override
	public Client findClientByEmail(String clientEmail) {
		String hql = "from Client c where c.clientEmail = :clientEmail";
		Query q = entityManager.createQuery(hql, Client.class);
		q.setParameter("clientEmail",clientEmail );
		try {
			return (Client) q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
}
