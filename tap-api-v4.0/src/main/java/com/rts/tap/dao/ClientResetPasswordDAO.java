package com.rts.tap.dao;

import com.rts.tap.model.Client;

public interface ClientResetPasswordDAO {

	public String addClient(Client client);
	
	public String resetPasswordByClientEmail(String clientEmail, String newPassword);
	
	public Client findClientByEmail(String clientEmail);

}
