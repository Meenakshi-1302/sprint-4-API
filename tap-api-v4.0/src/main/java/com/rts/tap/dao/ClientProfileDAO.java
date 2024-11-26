package com.rts.tap.dao;

import com.rts.tap.model.Client;

public interface ClientProfileDAO {

	public Client getClientById(Long clientId);
	public Client getClientByEmail(String clientEmail);
	
	public Client updateClientById(Long clientId, String clientPosition, String clientMobile);
	public Client updateClientByEmail(String clientEmail, String clientPosition, String clientMobile);
}
