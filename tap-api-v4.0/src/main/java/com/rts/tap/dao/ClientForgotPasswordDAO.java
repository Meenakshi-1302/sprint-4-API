package com.rts.tap.dao;

import com.rts.tap.model.Client;

public interface ClientForgotPasswordDAO {

	public boolean emailExists(String clientEmail);
	public Client findClientByEmail(String clientEmail);
	public Client verifyOtp(String otp, String clientEmail);
	public String updatePassword(String clientEmail, String newPassword);
}
