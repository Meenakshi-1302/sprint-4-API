package com.rts.tap.service;

import com.rts.tap.dto.ClientDTO;

public interface ClientResetPasswordService {
	public String addClient(ClientDTO client);
	public String resetPasswordByClientEmail(String clientEmail, String oldPassword, String newPassword);
}
