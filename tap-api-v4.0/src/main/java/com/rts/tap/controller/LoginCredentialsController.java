package com.rts.tap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.LoginResponse;
import com.rts.tap.dto.UpdatePasswordDto;
import com.rts.tap.dto.UpdatePasswordRequest;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.service.EmployeeService;
import com.rts.tap.service.LoginCredentialsService;


@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class LoginCredentialsController {
	
    private LoginCredentialsService loginCredentialsService;
    
    private EmployeeService employeeService;

	public LoginCredentialsController(LoginCredentialsService loginCredentialsService,
			EmployeeService employeeService) {
		super();
		this.loginCredentialsService = loginCredentialsService;
		this.employeeService = employeeService;
	}

	@PostMapping(APIConstants.CREATE_LOGIN_URL)
    public ResponseEntity<LoginCredentials> create(@RequestBody LoginCredentials loginCredentials) {
        LoginCredentials created = loginCredentialsService.create(loginCredentials);
        return ResponseEntity.ok(created);
    }
    
    @PostMapping(APIConstants.CHECK_LOGIN_CREDENTIALS_URL)
    public ResponseEntity<LoginResponse> loginUser (@RequestBody LoginCredentials loginCredentials) {
        try {
            String email = loginCredentials.getUserEmail();
            String password = loginCredentials.getPasswordHash();
            
//            if(status.equals("ACTIVE")) {
            	LoginResponse response = loginCredentialsService.authenticate(email, password);

                if (response != null) {
                	if(response.getRole().equals("Admin")) {
                		if(response.getStatus().equals("Invalid credentials")) {
                			return ResponseEntity.ok(response);
                		} else {
                			loginCredentialsService.generateAndSendOtp(email);            		
                			return ResponseEntity.ok(response);                			
                		}
                	}
                	else {
                		return ResponseEntity.ok(response);
                	}
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                }
            
//        else {
//            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(APIConstants.VERIFY_OTP_URL)
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        try {
            boolean isValid = loginCredentialsService.verifyOtp(email, otp);
            if (isValid) {
                return ResponseEntity.ok(MessageConstants.OTP_VERIFY);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageConstants.FAILURE_MESSAGE);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageConstants.FAILURE_MESSAGE);
        }
    }

    @PostMapping(APIConstants.RESEND_OTP_URL)
    public ResponseEntity<String> resendOtp(@RequestParam String email) {
        try {
            loginCredentialsService.generateAndSendOtp(email);
            return ResponseEntity.ok(MessageConstants.OTP_RESENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageConstants.FAILURE_MESSAGE);
        }
    }
    
    @GetMapping(APIConstants.LOGIN_CREDENTIAL_GET_BY_USERID)
    public ResponseEntity<LoginCredentials> getLoginCredentialsById(@PathVariable Long userId) {
        LoginCredentials loginCredentials = loginCredentialsService.getLoginCredentialsById(userId);
        if (loginCredentials != null) {
            return ResponseEntity.ok(loginCredentials);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @PutMapping(APIConstants.UPDATE_PASSWORD_URL)
    public ResponseEntity<UpdatePasswordDto> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
       
    	String email = updatePasswordRequest.getEmail();
        String newPassword = updatePasswordRequest.getNewPassword();
        
        
        String response = loginCredentialsService.updatePassword(email, newPassword);
        
        if (MessageConstants.SUCCESS_MESSAGE.equals(response)) {
            UpdatePasswordDto updatePasswordDto = new UpdatePasswordDto();
            updatePasswordDto.setMessage("Password updated successfully");
            return ResponseEntity.ok(updatePasswordDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UpdatePasswordDto("Failed to update password"));
        }
    }
    
    // Team - D ThirdPartyCredentials updated by Nagarjun N S
    
    
}
