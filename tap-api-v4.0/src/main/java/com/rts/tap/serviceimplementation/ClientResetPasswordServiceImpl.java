package com.rts.tap.serviceimplementation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.ClientResetPasswordDAO;
import com.rts.tap.dto.ClientDTO;
import com.rts.tap.model.Client;
import com.rts.tap.service.ClientResetPasswordService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientResetPasswordServiceImpl implements ClientResetPasswordService {

	private ClientResetPasswordDAO clientResetPasswordDAO;
	private EntityManager entityManager;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

	public ClientResetPasswordServiceImpl(ClientResetPasswordDAO clientResetPasswordDAO, EntityManager entityManager) {
		super();
		this.clientResetPasswordDAO = clientResetPasswordDAO;
		this.entityManager = entityManager;
	}

	@Override
	public String resetPasswordByClientEmail(String clientEmail, String oldPassword, String newPassword) {
	    Client client = clientResetPasswordDAO.findClientByEmail(clientEmail);

	    if (client == null) {
	        throw new IllegalArgumentException("Client not found for ID: " + clientEmail);
	    }

	    if (oldPassword.equals(newPassword)) {
	        throw new IllegalArgumentException("Old password and new password should not be the same");
	    }

	    if (!bCryptPasswordEncoder.matches(oldPassword, client.getPassword())) {
	        throw new IllegalArgumentException("Old password is incorrect");
	    }

	    client.setPassword(bCryptPasswordEncoder.encode(newPassword));
	    entityManager.merge(client);
	    return "Password updated successfully";
	}


	@Override
	public String addClient(ClientDTO clientDTO) {
		if (clientDTO != null) {
			Client client = new Client();
			client.setClientId(clientDTO.getClientId());
			client.setClientEmail(clientDTO.getClientEmail());
			client.setPassword(bCryptPasswordEncoder.encode(clientDTO.getPassword()));

			clientResetPasswordDAO.addClient(client);
			return MessageConstants.CLIENT_ADD_SUCCESS;
		} else {
			return MessageConstants.CLIENT_ADD_FAILURE;
		}
	}

}
