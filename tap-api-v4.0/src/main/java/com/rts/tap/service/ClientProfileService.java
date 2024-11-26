package com.rts.tap.service;

import com.rts.tap.model.Client;

public interface ClientProfileService {
	public Client getClientById(Long clientId);
	public Client getClientByEmail(String clientEmail);
	
	public String updateClientById(Long clientId, String clientPosition, String clientMobile);
	public String updateClientByEmail(String clientEmail, String clientPosition, String clientMobile);
}
