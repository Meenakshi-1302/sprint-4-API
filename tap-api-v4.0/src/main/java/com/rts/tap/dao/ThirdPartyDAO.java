package com.rts.tap.dao;

import com.rts.tap.model.Client;
import com.rts.tap.model.ThirdPartyCredentitals;
import com.rts.tap.model.Vendor;

public interface ThirdPartyDAO {
	
	Object doLogin(String email, String password);
	
	//Third Party Forgot password
	public boolean emailExists(String email);

	public ThirdPartyCredentitals findbyEmail(String email);

	public Client findClientByThirdPartyId(Long id);
	
	public ThirdPartyCredentitals verifyOtp(String email, String otp);
	
	public String updateForgotPassword(String email, String newPassword);

	public Vendor findVendorByThirdPartyId(Long id);
	
}
