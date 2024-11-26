package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rts.tap.dao.ClientDao;
import com.rts.tap.model.Client;
import com.rts.tap.model.ClientOrganization;
import com.rts.tap.model.ThirdPartyCredentitals;
import com.rts.tap.model.ThirdPartyRole;
import com.rts.tap.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Client saveClient(Client client) {
		ThirdPartyCredentitals credentials = new ThirdPartyCredentitals();

		if (client.getThirdPartyCredentitals() == null) {

			credentials.setEmail(client.getClientEmail());
			credentials.setPassword("null");
		}
		ThirdPartyRole role;
		try {
			String hql = "select r from ThirdPartyRole r where role = :client";
			Query query = entityManager.createQuery(hql).setParameter("client", "Client");
			role = (ThirdPartyRole) query.getSingleResult();
		} catch (NoResultException e) {

			role = new ThirdPartyRole();
			role.setRole("Client");
			entityManager.persist(role);
		}

		credentials.setRole(role);

		entityManager.persist(credentials);

		client.setThirdPartyCredentitals(credentials);

		entityManager.persist(client);

		return client;
	}

	@Override
	public ClientOrganization saveClientOrganization(ClientOrganization clientOrganization) {
		entityManager.persist(clientOrganization);
		return clientOrganization;
	}

	@Override
	public Client updateClient(Long id, Client client) {
//		client.setClientId(id);

		if (client.getThirdPartyCredentitals() != null) {
			Long thirdPartyCredentialsId = client.getThirdPartyCredentitals().getThirdPartyCredentialsId();
			String newPassword = client.getPassword();
			String hql = "UPDATE ThirdPartyCredentitals tpc SET tpc.password = :password WHERE tpc.thirdPartyCredentialsId = :thirdPartyCredentialsId";

			int updatedRows = entityManager.createQuery(hql).setParameter("password", newPassword)
					.setParameter("thirdPartyCredentialsId", thirdPartyCredentialsId).executeUpdate();

			if (updatedRows == 0) {
				throw new EntityNotFoundException("No ThirdPartyCredentials found with ID: " + thirdPartyCredentialsId);
			}
		}

		return entityManager.merge(client);
	}

	@Override
	public void deleteClient(Long id) {
		Client client = entityManager.find(Client.class, id);
		if (client != null) {
			entityManager.remove(client);
		}
	}

	@Override
	public List<Client> viewAllClients() {
		TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
		return query.getResultList();
	}

	@Override
	public Client viewClientById(Long id) {
		return entityManager.find(Client.class, id);
	}

	// Code by Velan
	@Override
	public Client viewClientByMrfId(Long mrfId) {
		String hql = "select m.requirement.client from MRF m where m.mrfId = :mrfId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("mrfId", mrfId);
		return (Client) query.getSingleResult();
	}

	@Override
	public Long getClientIdByEmail(String email) {
		String hql = "select c.clientId from Client c where c.clientEmail  = :email";
		Query q = entityManager.createQuery(hql);
		q.setParameter("email", email);
		return (Long) q.getSingleResult();
	}

	@Override
	public Client doLogin(String email, String password) {
		String clientEmail = email;

		if (clientEmail != null) {
			// Fetch the client based on the email only
			String emailLoginQuery = "SELECT v FROM Client v WHERE v.thirdPartyCredentitals.email=:email";
			Query query = entityManager.createQuery(emailLoginQuery).setParameter("email", clientEmail);

			try {
				Client client = (Client) query.getSingleResult();

				// We assume 'client.password' stores the encoded password
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				if (passwordEncoder.matches(password, client.getPassword())) {
					return client;
				} else {
					return null;
				}
			} catch (NoResultException e) {
				return null;
			}
		}
		return null;
	}

	// Team - E - Karthikeyan R K
	@Override
	public Client getClientById(Long clientId) {
		return entityManager.find(Client.class, clientId);
	}
}
