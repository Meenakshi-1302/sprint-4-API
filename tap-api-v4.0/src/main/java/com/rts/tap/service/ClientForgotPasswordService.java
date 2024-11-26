package com.rts.tap.service;

public interface ClientForgotPasswordService {

	public boolean emailExists(String clientEmail);
//	public String sendOtp(String clientEmail);
	public String verifyOtp(String otp, String clientEmail);
	public String updateForgotPassword(String clientEmail, String updatedPassword);
}
