package com.rts.tap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.emailservice.ThirdPartyEmailService;
import com.rts.tap.model.ThirdPartyCredentitals;
import com.rts.tap.service.ThirdPartyService;

import jakarta.persistence.NoResultException;

@RestController
@CrossOrigin(origins = APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(path = APIConstants.PASSWORD_RECOVERY)
public class ThirdPartyCredentialsController {

	private ThirdPartyService thirdPartyService;
	private ThirdPartyEmailService thirdPartyEmailService;

	public ThirdPartyCredentialsController(ThirdPartyService thirdPartyService,
			ThirdPartyEmailService thirdPartyEmailService) {
		super();
		this.thirdPartyService = thirdPartyService;
		this.thirdPartyEmailService = thirdPartyEmailService;
	}

	@PostMapping(path = APIConstants.FORGET_PASSWORD_EMAIL_EXISTS)
	public ResponseEntity<String> emailExists(@RequestParam String email){
			thirdPartyService.emailExists(email);
			return ResponseEntity.ok(MessageConstants.EMAIL_EXIST);
	}
	
	@PostMapping(path = APIConstants.FORGET_PASSWORD_SEND_OTP)
	private ResponseEntity<String> sendOtp(@RequestParam String email) {
		try {
			thirdPartyEmailService.sendOtp(email);
			return ResponseEntity.ok(MessageConstants.FORGOTPWD_OTP_SUCCESS);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("This Email is not registered. Please check the email and try again!");
		}
	}

	@PostMapping(path = APIConstants.FORGET_PASSWORD_VERIFY_OTP)
	public ResponseEntity<String> verifyOtp(@RequestParam String otp, @RequestParam String email) {
		if (email == null || email.isEmpty()) {
			return ResponseEntity.badRequest().body("Email cannot be null or empty.");
		}
		String response = thirdPartyService.verifyOtp(otp, email);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping(path = APIConstants.FORGET_PASSWORD_UPDATE)
	public ResponseEntity<String> updateForgotPassword(@RequestBody ThirdPartyCredentitals credentials) {
		String response = thirdPartyService.updateForgotPassword(credentials.getEmail(),
				credentials.getPassword());
		return ResponseEntity.ok(response);
	}
	
//	@PutMapping(path = APIConstants.THIRD_PARTY_RESET_PASSWORD)
//	public ResponseEntity<String> resetPassword(@RequestParam String email,@RequestParam String oldPassword , @RequestParam String newPassword ){
//		try {
//			String response = thirdPartyService.resetPassword(email, oldPassword, newPassword);
//			return ResponseEntity.ok(response);
//		}
//		catch(IllegalArgumentException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
	
	@PutMapping(path = APIConstants.THIRD_PARTY_RESET_PASSWORD)
	public ResponseEntity<String> resetClientPassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
	    try {
	        System.out.println("Received reset password request for email: " + email);
	        String response = thirdPartyService.resetPassword(email, oldPassword, newPassword);
	        return ResponseEntity.ok(response);
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error during password reset: " + e.getMessage());
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

}
