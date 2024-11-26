package com.rts.tap.serviceimplementation;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.ClientDao;
import com.rts.tap.dto.ClientDTO;
import com.rts.tap.model.Client;
import com.rts.tap.model.ClientOrganization;
import com.rts.tap.service.ClientService;
import com.rts.tap.utils.EmailUtil;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDAO;

	@Autowired
	private EmailUtil emailUtil;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public Client saveClient(Client client, MultipartFile organizationLogo) throws IOException {

		byte[] organizationLogoBytes = organizationLogo.getBytes();
		ClientOrganization clientOrganization = client.getClientOrganization();

		if (clientOrganization != null) {
			clientOrganization.setOrganizationLogo(organizationLogoBytes);

			clientDAO.saveClientOrganization(clientOrganization);
		}
		client.setClientOrganization(clientOrganization);
		

		return clientDAO.saveClient(client);
	}

	@Override
	public Client updateClient(Long id) {
		Client client = clientDAO.viewClientById(id);

		String defaultPassword = generateRandomPassword();
		client.setPassword(passwordEncoder.encode(defaultPassword));
		client.setClientStatus("Approved");

		emailUtil.sendCredentialsEmail(client.getClientEmail(), defaultPassword);

		return clientDAO.updateClient(id, client);
	}

	@Override
	public void deleteClient(Long id) {
		clientDAO.deleteClient(id);
	}

	@Override
	public List<Client> viewAllClients() {
		return clientDAO.viewAllClients();
	}

	@Override
	public Client viewClientById(Long id) {
		return clientDAO.viewClientById(id);
	}

	private String generateRandomPassword() {
		return UUID.randomUUID().toString().substring(0, 12);
	}

	// Code by Velan
	@Override
	public Client viewClientByMrfId(Long mrfId) {
		return clientDAO.viewClientByMrfId(mrfId);
	}

	@Override
	public Long getClientIdByEmail(String email) {
		return clientDAO.getClientIdByEmail(email);
	}

	@Override
	public Client doLogin(ClientDTO clientDTO) {
		String email = clientDTO.getClientEmail();
		String password = clientDTO.getPassword();
		return clientDAO.doLogin(email, password);
	}
}
