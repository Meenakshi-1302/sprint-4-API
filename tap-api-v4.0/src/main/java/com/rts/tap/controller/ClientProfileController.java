package com.rts.tap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.dto.ClientDTO;
import com.rts.tap.model.Client;
import com.rts.tap.service.ClientProfileService;

@RestController
@CrossOrigin(origins = APIConstants.BASE_URL)
@RequestMapping(path = APIConstants.GET_CLIENT_URL)
public class ClientProfileController {

	private ClientProfileService clientProfileService;

	public ClientProfileController(ClientProfileService clientProfileService) {
		super();
		this.clientProfileService = clientProfileService;
	}
	
	@GetMapping(path = APIConstants.GET_CLIENT_BY_ID)
	public ResponseEntity<Client> getClientDetailsById(@PathVariable("clientId") Long clientId){
		Client response = clientProfileService.getClientById(clientId);
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = APIConstants.GET_CLIENT_BY_EMAIL)
	public ResponseEntity<Client> getClientDetailsByEmail(@PathVariable("clientEmail") String clientEmail){
		Client response = clientProfileService.getClientByEmail(clientEmail);
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping(path = APIConstants.UPDATE_CLIENT_PROFILE_BY_ID)
	public ResponseEntity<String> updateClientDetails(@PathVariable("clientId") Long clientId, @RequestBody Client updateClient){
		String response = clientProfileService.updateClientById(clientId, updateClient.getClientPosition(), updateClient.getClientMobile());
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping(path = APIConstants.UPDATE_CLIENT_PROFILE_BY_EMAIL)
	public ResponseEntity<String> updateClientDetails(@PathVariable("clientEmail") String clientEmail, @RequestBody Client updateClient){
		String response = clientProfileService.updateClientByEmail(clientEmail, updateClient.getClientPosition(), updateClient.getClientMobile());
		return ResponseEntity.ok(response);
	}
	
	
}
