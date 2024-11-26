package com.rts.tap.serviceimplementation;

import org.springframework.stereotype.Service;

import com.rts.tap.dao.ClientProfileDAO;
import com.rts.tap.model.Client;
import com.rts.tap.service.ClientProfileService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientProfileServiceImpl implements ClientProfileService{

	private ClientProfileDAO clientProfileDAO;

	public ClientProfileServiceImpl(ClientProfileDAO clientProfileDAO) {
		super();
		this.clientProfileDAO = clientProfileDAO;
	}

	@Override
	public Client getClientById(Long clientId) {
		return clientProfileDAO.getClientById(clientId);
	}

	@Override
	public Client getClientByEmail(String clientEmail) {
		return clientProfileDAO.getClientByEmail(clientEmail);
	}

	@Override
	public String updateClientById(Long clientId, String clientPosition, String clientMobile) {
		Client client = clientProfileDAO.getClientById(clientId);
		
		if (client == null) {
			return "Client not found"; 
		} 
		if (clientPosition != null) {
			client.setClientPosition(clientPosition); 
		} 
		if (clientMobile != null) {
			client.setClientMobile(clientMobile); 
		} 
		
		Client updatedClient = clientProfileDAO.updateClientById(clientId, client.getClientPosition(), client.getClientMobile()); 
		return updatedClient != null ? "Client details updated successfully!" : "Failure in updating client details";
	}

	@Override
	public String updateClientByEmail(String clientEmail, String clientPosition, String clientMobile) {
		Client client = clientProfileDAO.getClientByEmail(clientEmail);
		
		if (client == null) { 
			return "Client not found"; 
		} 
		if (clientPosition != null) { 
			client.setClientPosition(clientPosition); 
		} 
		if (clientMobile != null) { 
			client.setClientMobile(clientMobile); 
		} 
		
		Client updatedClient = clientProfileDAO.updateClientByEmail(clientEmail, client.getClientPosition(), client.getClientMobile());
		return updatedClient != null ? "Client details updated successfully!" : "Failure in updating client details";
		}
	
}
