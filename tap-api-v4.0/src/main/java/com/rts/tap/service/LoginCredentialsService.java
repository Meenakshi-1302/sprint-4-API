package com.rts.tap.service;

import com.rts.tap.dto.LoginResponse;
import com.rts.tap.model.LoginCredentials;


public interface LoginCredentialsService {
	
    LoginCredentials create(LoginCredentials loginCredentials);
    
	LoginResponse authenticate(String email, String password);
	boolean verifyOtp(String email, String otp);
	void generateAndSendOtp(String email);
	LoginCredentials getLoginCredentialsById(Long userId);

	String updatePassword(String email, String password);
    
}