package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.ClientForgotPasswordDAO;
import com.rts.tap.model.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

@Repository
public class ClientForgotPasswordDAOImpl implements ClientForgotPasswordDAO {

	private EntityManager entityManager;

	public ClientForgotPasswordDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public boolean emailExists(String clientEmail) {
		String hql = "select c from Client c join c.thirdPartyCredentials t where t.email = :clientEmail";
		Query q = entityManager.createQuery(hql);
		q.setParameter("clientEmail", clientEmail);
		
		Client client = (Client) q.getSingleResult();
		if(client == null) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public String updatePassword(String clientEmail, String newPassword) {
		String hql = "update ThirdPartyCredentials t set t.password = :newPassword where t.email = :clientEmail";
		Query q = entityManager.createQuery(hql);
        q.setParameter("clientEmail", clientEmail);
        q.setParameter("newPassword", newPassword);
        
        int updatedCount = q.executeUpdate();
        return updatedCount > 0 ? "Password Updated Successfully" : "Email does not exist!";
	}

	@Override
	public Client findClientByEmail(String clientEmail) {
		String hql = "From Client c JOIN c.thirdPartyCredentials t WHERE t.email = :clientEmail";
		Query q = entityManager.createQuery(hql,Client.class);
		q.setParameter("clientEmail", clientEmail);
		try {
			return (Client) q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
		
	}

	@Override
	public Client verifyOtp(String otp, String clientEmail) {
		String hql = "select c from Client c JOIN c.thirdPartyCredentials t where c.otp = :otp and t.email = :clientEmail";
		Query q = entityManager.createQuery(hql, Client.class);
		q.setParameter("otp", otp);
		q.setParameter("clientEmail", clientEmail);
		try {
			return (Client) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
