package com.rts.tap.emailservice;
 
import com.rts.tap.dto.VendorDto;

import jakarta.mail.MessagingException;
 
public interface VendorMailService {
	public String sendVendorCredentials(VendorDto vendorDto) throws MessagingException;
 
}