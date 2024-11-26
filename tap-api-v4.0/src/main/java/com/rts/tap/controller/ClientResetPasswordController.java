package com.rts.tap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.dao.ClientResetPasswordDAO;
import com.rts.tap.dto.ClientDTO;
import com.rts.tap.service.ClientResetPasswordService;

@RestController
@CrossOrigin(origins = APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(path = APIConstants.GET_CLIENT_URL)
public class ClientResetPasswordController {

	private ClientResetPasswordService clientResetPasswordService;


	
	public ClientResetPasswordController(ClientResetPasswordService clientResetPasswordService,
			ClientResetPasswordDAO clientResetPasswordDAO) {
		super();
		this.clientResetPasswordService = clientResetPasswordService;
	}

	@PostMapping(path = APIConstants.CLIENT_ADD)
	public ResponseEntity<String> addClient(@RequestBody ClientDTO client){
		return ResponseEntity.ok(clientResetPasswordService.addClient(client));
	}
//	
//	@PutMapping(path = APIConstants.CLIENT_RESET_PASSWORD_UPDATE)
//	public ResponseEntity<String> resetClientPassword(@RequestBody ClientDTO clientResetPasswordDTO){
//		try {
//			clientResetPasswordService.resetPasswordByClientEmail(clientResetPasswordDTO);
//			return ResponseEntity.ok(MessageConstants.CLIENT_PASSWORD_RESET_SUCCESS);
//		}
//		catch(IllegalArgumentException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
	
	@PutMapping(path = APIConstants.CLIENT_RESET_PASSWORD_UPDATE)
	public ResponseEntity<String> resetClientPassword1(@RequestParam String clientEmail,@RequestParam String oldPassword , @RequestParam String newPassword ){
		try {
			
			String response = clientResetPasswordService.resetPasswordByClientEmail(clientEmail,oldPassword, newPassword);
			return ResponseEntity.ok(response);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
