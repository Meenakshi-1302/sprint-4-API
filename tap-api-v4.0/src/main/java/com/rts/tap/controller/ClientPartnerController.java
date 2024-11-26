package com.rts.tap.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.ClientDTO;
import com.rts.tap.model.Client;
import com.rts.tap.service.ClientService;

@RestController
@RequestMapping(APIConstants.CLIENTS_PATH)
@CrossOrigin(MessageConstants.ORGIN)
public class ClientPartnerController {

	@Autowired
	private ClientService clientService;

	@PostMapping
	public ResponseEntity<String> saveClient(@RequestParam("client") String clientJson,
			@RequestParam("organizationLogo") MultipartFile organizationLogo) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		Client client = objectMapper.readValue(clientJson, Client.class);

		clientService.saveClient(client, organizationLogo);
		return ResponseEntity.ok(MessageConstants.CLIENT_CREATED_SUCCESS);
	}

	@PutMapping(APIConstants.UPDATE_CLIENT_APPROVED)
	public ResponseEntity<String> updateClient(@PathVariable("id") Long id) {
		clientService.updateClient(id);
		return ResponseEntity.ok(MessageConstants.CLIENT_UPDATED_SUCCESS);
	}

	@GetMapping
	public ResponseEntity<List<Client>> viewAllClients() {
		List<Client> clients = clientService.viewAllClients();
		return ResponseEntity.ok(clients);
	}

	@GetMapping(APIConstants.GET_CLIENTBYID)
	public ResponseEntity<Client> viewClientById(@PathVariable("id") Long id) {
		Client client = clientService.viewClientById(id);
		
		return ResponseEntity.ok(client);
	}
	
	//Code By Velan
	@GetMapping(APIConstants.GET_CLIENT_BY_MRFID)
	public ResponseEntity<Client> viewClientNameById(@PathVariable("mrfId") Long mrfId) {
		Client client = clientService.viewClientById(mrfId);
		return ResponseEntity.ok(client);
	}
	
	@PostMapping(APIConstants.CLIENT_LOGIN)
	public ResponseEntity<Client> doLogin(@RequestBody ClientDTO clientDto){
		Client cl = clientService.doLogin(clientDto);
		return ResponseEntity.ok(cl);
		
	}
	
}