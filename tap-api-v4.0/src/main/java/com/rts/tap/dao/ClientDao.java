package com.rts.tap.dao;

import com.rts.tap.model.Client;
import com.rts.tap.model.ClientOrganization;

import java.util.List;

public interface ClientDao {

	Client saveClient(Client client);

	Client updateClient(Long id, Client client);

	void deleteClient(Long id);

	List<Client> viewAllClients();

	Client viewClientById(Long id);

	ClientOrganization saveClientOrganization(ClientOrganization clientOrganization);

	Client viewClientByMrfId(Long mrfId);

	Long getClientIdByEmail(String email);

	// Team E - Karthikeyan R K
	Client getClientById(Long clientId);

	Client doLogin(String email, String password);

}
