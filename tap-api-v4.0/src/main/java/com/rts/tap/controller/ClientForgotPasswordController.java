package com.rts.tap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.ClientDTO;
import com.rts.tap.emailservice.ClientEmailService;
import com.rts.tap.service.ClientForgotPasswordService;

@RestController
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(path = APIConstants.GET_CLIENT_URL)
public class ClientForgotPasswordController {

	private ClientForgotPasswordService clientForgotPasswordService;
	private ClientEmailService clientEmailService;

	public ClientForgotPasswordController(ClientForgotPasswordService clientForgotPasswordService,
			ClientEmailService clientEmailService) {
		super();
		this.clientForgotPasswordService = clientForgotPasswordService;
		this.clientEmailService = clientEmailService;
	}

	@PostMapping(path = APIConstants.CLIENT_EMAIL_CHECK_URL)
	private ResponseEntity<String> emailExists(@PathVariable String clientEmail) {
		boolean exists = clientForgotPasswordService.emailExists(clientEmail);
		if (exists) {
			return ResponseEntity.ok(MessageConstants.CLIENT_EMAIL_EXISTS);
		} else {
			return ResponseEntity.ok(MessageConstants.CLIENT_EMAIL_NOT_EXISTS);
		}
	}

	@PostMapping(path = APIConstants.CLIENT_FORGOT_PASSWORD_SEND_OTP)
	private ResponseEntity<String> sendOtp(@PathVariable String clientEmail) {
		try {
			clientEmailService.sendOtp(clientEmail);
			return ResponseEntity.ok(MessageConstants.CLIENT_FORGOTPWD_OTP_SUCCESS);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("This Email is not registered. Please check the email and try again!");
		}
	}

	@PostMapping(path = APIConstants.CLIENT_FORGOT_PASSWORD_VERIFY_OTP)
	public ResponseEntity<String> verifyOtp(@RequestParam String otp, @RequestParam String clientEmail) {
		if (clientEmail == null || clientEmail.isEmpty()) {
			return ResponseEntity.badRequest().body("Email cannot be null or empty.");
		}
		String response = clientForgotPasswordService.verifyOtp(otp, clientEmail);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(path = APIConstants.CLIENT_FORGOT_PASSWORD_UPDATE)
	public ResponseEntity<String> updateForgotPassword(@RequestBody ClientDTO clientDTO) {
		String response = clientForgotPasswordService.updateForgotPassword(clientDTO.getClientEmail(),
				clientDTO.getPassword());
		return ResponseEntity.ok(response);
	}
}
