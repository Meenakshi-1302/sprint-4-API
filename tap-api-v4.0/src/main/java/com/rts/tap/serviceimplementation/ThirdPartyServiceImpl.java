package com.rts.tap.serviceimplementation;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.ThirdPartyDAO;
import com.rts.tap.model.Client;
import com.rts.tap.model.ThirdPartyCredentitals;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.ThirdPartyService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ThirdPartyServiceImpl implements ThirdPartyService {

	private ThirdPartyDAO thirdPartyDAO;
	private EntityManager entityManager;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public ThirdPartyServiceImpl(ThirdPartyDAO thirdPartyDAO, EntityManager entityManager) {
		super();
		this.thirdPartyDAO = thirdPartyDAO;
		this.entityManager = entityManager;
	}

	@Override
	public boolean emailExists(String email) {
		return thirdPartyDAO.emailExists(email);
	}

	@Override
	public ThirdPartyCredentitals findbyEmail(String email) {
		return thirdPartyDAO.findbyEmail(email);
	}

	@Override
	public Client findClientByThirdPartyId(Long id) {
		return thirdPartyDAO.findClientByThirdPartyId(id);
	}

	@Override
	public String verifyOtp(String otp, String email) {
		ThirdPartyCredentitals thirdPartyCredentitals = thirdPartyDAO.findbyEmail(email);
		if (thirdPartyCredentitals == null) {
			return "User not found";
		}
		if (thirdPartyCredentitals.getOtp() == null
				|| thirdPartyCredentitals.getExpirationTime().isBefore(LocalDateTime.now())) {
			return "Invalid or expired OTP!";
		} else if (thirdPartyCredentitals.getOtp().equals(otp)) {
			thirdPartyCredentitals.setOtp(null);
			thirdPartyCredentitals.setExpirationTime(null);
			entityManager.merge(thirdPartyCredentitals);
			return "OTP verified";
		}
		return "Error in verifying OTP";
	}

	@Override
	public String updateForgotPassword(String email, String newPassword) {
		ThirdPartyCredentitals thirdPartyCredentitals = thirdPartyDAO.findbyEmail(email);
		if (thirdPartyCredentitals == null) {
			return "User not found";
		} else {
			String encodedPassword = encoder.encode(newPassword);
			try {
				thirdPartyDAO.updateForgotPassword(email, encodedPassword);
				return "Password Updated successfully";
			} catch (Exception e) {
				return "Password updation failed!";
			}
		}
	}

	@Override
	public Vendor findVendorByThirdPartyId(Long id) {
		return thirdPartyDAO.findVendorByThirdPartyId(id);
	}

//	@Override
//	public String resetPassword(String email, String oldPassword, String newPassword) {
//		ThirdPartyCredentitals thirdPartyCredentitals = thirdPartyDAO.findbyEmail(email);
//
//	    if (thirdPartyCredentitals == null) {
//	        throw new IllegalArgumentException("User not found for Email: " + email);
//	    }
//
//	    if (oldPassword.equals(newPassword)) {
//	        throw new IllegalArgumentException("Old password and new password should not be the same");
//	    }
//
//	    if (!encoder.matches(oldPassword, thirdPartyCredentitals.getPassword())) {
//	        throw new IllegalArgumentException("Old password is incorrect");
//	    }
//
//	    thirdPartyCredentitals.setPassword(encoder.encode(newPassword));
//	    entityManager.merge(thirdPartyCredentitals);
//	    return "Password updated successfully";
//	}
	
	@Override
	@Transactional
	public String resetPassword(String email, String oldPassword, String newPassword) {
	    System.out.println("Resetting password for email: " + email);
	    
	    ThirdPartyCredentitals thirdPartyCredentitals = thirdPartyDAO.findbyEmail(email);

	    if (thirdPartyCredentitals == null) {
	        System.out.println("User not found for email: " + email);
	        throw new IllegalArgumentException("User not found for Email: " + email);
	    }

	    System.out.println("Old Password: " + oldPassword);
	    System.out.println("New Password: " + newPassword);
	    System.out.println("Encoded Password in DB: " + thirdPartyCredentitals.getPassword());

	    if (oldPassword.equals(newPassword)) {
	        System.out.println("Old password and new password should not be the same");
	        throw new IllegalArgumentException("Old password and new password should not be the same");
	    }

	    if (!encoder.matches(oldPassword, thirdPartyCredentitals.getPassword())) {
	        System.out.println("Old password is incorrect");
	        throw new IllegalArgumentException("Old password is incorrect");
	    }

	    thirdPartyCredentitals.setPassword(encoder.encode(newPassword));
	    entityManager.merge(thirdPartyCredentitals);
	    System.out.println("Password updated successfully for email: " + email);
	    return "Password updated successfully";
	}


}
