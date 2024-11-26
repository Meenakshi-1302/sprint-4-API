package com.rts.tap.service;

import com.rts.tap.model.Client;
import com.rts.tap.model.ThirdPartyCredentitals;
import com.rts.tap.model.Vendor;

public interface ThirdPartyService {

	public boolean emailExists(String email);

	public ThirdPartyCredentitals findbyEmail(String email);

	public Client findClientByThirdPartyId(Long id);
	
	public String verifyOtp(String email, String otp);
	
	public String updateForgotPassword(String email, String newPassword);

	public Vendor findVendorByThirdPartyId(Long id);
	
	public String resetPassword(String email, String newPassword, String oldPassword);
}
