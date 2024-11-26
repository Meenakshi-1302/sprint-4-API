package com.rts.tap.serviceimplementation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import com.rts.tap.dao.LoginCredentialsDao;
import com.rts.tap.daoimplementation.AdminDaoImpl;
import com.rts.tap.daoimplementation.LoginCredentialsDaoImpl;
import com.rts.tap.daoimplementation.ThirdPartyDaoImpl;
import com.rts.tap.dto.LoginResponse;
import com.rts.tap.model.Admin;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Client;
import com.rts.tap.model.LoginCredentials;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.EmailService1;
import com.rts.tap.service.EmployeeService;
import com.rts.tap.service.LoginCredentialsService;

@Service
public class LoginCredentialsServiceImpl implements LoginCredentialsService {

	private LoginCredentialsDao loginCredentialsDao;
	private AdminDaoImpl adminDaoImpl;
	private LoginCredentialsDaoImpl loginCredentialsDaoImpl;
	private EmailService1 emailService;
	private EmployeeService employeeService;
	private ThirdPartyDaoImpl thirdPartyImpl;

	private Map<String, String> otpStorage = new HashMap<>();
	private Map<String, Long> otpExpiry = new HashMap<>();

	

	public LoginCredentialsServiceImpl(LoginCredentialsDao loginCredentialsDao, AdminDaoImpl adminDaoImpl,
			LoginCredentialsDaoImpl loginCredentialsDaoImpl, EmailService1 emailService,
			EmployeeService employeeService, ThirdPartyDaoImpl thirdPartyImpl) {
		super();
		this.loginCredentialsDao = loginCredentialsDao;
		this.adminDaoImpl = adminDaoImpl;
		this.loginCredentialsDaoImpl = loginCredentialsDaoImpl;
		this.emailService = emailService;
		this.employeeService = employeeService;
		this.thirdPartyImpl = thirdPartyImpl;
	}

	@Override
	public LoginCredentials create(LoginCredentials loginCredentials) {
		return loginCredentialsDao.save(loginCredentials);
	}

	@Override
	public LoginResponse authenticate(String email, String password) {

		// Check if the email exists in Admin (for admin login)
		Admin admin = adminDaoImpl.findEmail(email);
		LoginCredentials loginCredentials = loginCredentialsDaoImpl.findEmail(email);
		Object credentials = thirdPartyImpl.doLogin(email, password);
		 

		if (admin != null) {
			if (admin.getPasswordHash().equals(password)) {
				Long adminId = admin.getAdminId();
				return new LoginResponse(adminId, "Success", "Admin");
			} else {
				return new LoginResponse(null, "Invalid credentials", "Admin");
			}
		} else if (loginCredentials != null) {

			String status = employeeService.checkEmployeeStatus(email);

			if (status.equals("ACTIVE")) {
				LocalDateTime lockoutTime = loginCredentials.getLockoutTime();
				if (lockoutTime != null) {
					if (lockoutTime.isAfter(LocalDateTime.now())) {
						// Account is still locked, calculate how much time is left
						long minutesLeft = ChronoUnit.MINUTES.between(LocalDateTime.now(), lockoutTime);
						return new LoginResponse(null,
								"Account locked. Please try again in " + minutesLeft + " minute(s).", "Unknown");
					} else if (lockoutTime.isBefore(LocalDateTime.now())) {
						// Lockout time has expired, reset failed attempts and lockout details
						loginCredentials.setFailedAttempts(0); // Reset failed attempts
						loginCredentials.setLockoutTime(null); // Remove lockout time

						// If more than 1 hour has passed since the last lockout, reset the lockout
						// count
						if (loginCredentials.getLockoutCount() > 0
								&& lockoutTime.isBefore(LocalDateTime.now().minusHours(1))) {
							loginCredentials.setLockoutCount(0); // Reset lockout count after 1 hour of inactivity
						}

						loginCredentialsDaoImpl.updateFailedAttempts(loginCredentials); // Save changes
					}
				}

				if (loginCredentials.getPasswordHash().equals(password)) {
					// Successful login: Reset failed attempts and lockout details
					loginCredentials.setFailedAttempts(0); // Reset failed attempts
					loginCredentials.setLockoutTime(null); // Clear lockout time
					loginCredentials.setLockoutCount(0); // Reset lockout count after successful login
					loginCredentialsDaoImpl.updateFailedAttempts(loginCredentials); // Save changes
					Long userId = loginCredentials.getUserId();
					String role = loginCredentialsDaoImpl.getRole(userId);
					Long employeeId = loginCredentials.getEmployee().getEmployeeId();
					String passwordStatus = loginCredentials.getIsPasswordChange();
					return new LoginResponse(userId, "Success", role, employeeId, passwordStatus);
				} else {
					// Increment failed login attempts
					int failedAttempts = loginCredentials.getFailedAttempts() + 1;
					loginCredentials.setFailedAttempts(failedAttempts);
					// Lockout logic: if failed attempts exceed 3, lock the account
					int lockoutDuration = 5; // Default to 5 minutes lockout for the first 3 failed attempts

					// If the account has been locked more than once (2 or more times), lock for 1
					// hour
					if (loginCredentials.getLockoutCount() >= 2) {
						lockoutDuration = 60; // 1 hour
					}

					// If failed attempts exceed 3, lock the account and increase lockout count
					if (failedAttempts >= 3) {
						loginCredentials.setLockoutTime(LocalDateTime.now().plusMinutes(lockoutDuration));
						// Set lockout time
						loginCredentials.setLockoutCount(loginCredentials.getLockoutCount() + 1);
						// Increment lockout count
					}

					loginCredentialsDaoImpl.updateFailedAttempts(loginCredentials); // Save changes

					return new LoginResponse(null, "Invalid credentials. " + (3 - failedAttempts) + " attempt(s) left.",
							"Unknown");
				}

			} else {
				return new LoginResponse(null, "INACTIVE", "Employee", null, null);
			}
		} else if (credentials != null) {
			if (credentials instanceof Client) {
				Client client = (Client) credentials;
				return new LoginResponse("Success", client.getClientId(), "Client");
			} else if (credentials instanceof Vendor) {
				Vendor vendor = (Vendor) credentials;
				return new LoginResponse("Success", vendor.getVendorId(), "Vendor");
			} else if (credentials instanceof Candidate) {
				Candidate candidate = (Candidate) credentials;
				return new LoginResponse("Success", candidate.getCandidateId(), "Candidate");
			}
			return null;
		} else {
			return new LoginResponse(null, "User not found", "Unknown");
		}
	}

//		if (admin != null)&& admin.getPasswordHash().equals(password)) {
//			Long adminId = admin.getAdminId();
//			return new LoginResponse(adminId, "Success", "Admin");
//		} else if (admin == null && loginCredentials == null ) {
//			return new LoginResponse(null, "Invalid credentials", null);
//		} else {
//			
//			String status = employeeService.checkEmployeeStatus(email);
//
//			if (status.equals("ACTIVE")) {
//
//				if (loginCredentials == null) {
//				}
//
//				// Check if the account is locked due to previous failed attempts
//				if (loginCredentials.getLockoutTime() != null
//						&& loginCredentials.getLockoutTime().isAfter(LocalDateTime.now())) {
//					// Account is still locked, calculate how much time is left
//					long minutesLeft = ChronoUnit.MINUTES.between(LocalDateTime.now(),
//							loginCredentials.getLockoutTime());
//					return new LoginResponse(null, "Account locked. Please try again in " + minutesLeft + " minute(s).",
//							"Unknown");
//				}
//
//				// If the lockout time has expired (even if the account was locked before),
//				// reset failed attempts and lockout details
//				if (loginCredentials.getLockoutTime() != null
//						&& loginCredentials.getLockoutTime().isBefore(LocalDateTime.now())) {
//					// Lockout time has expired, reset failed attempts and lockout details
//					loginCredentials.setFailedAttempts(0); // Reset failed attempts
//					loginCredentials.setLockoutTime(null); // Remove lockout time
//
//					// If more than 1 hour has passed since the last lockout, reset the lockout
//					// count
//					if (loginCredentials.getLockoutCount() > 0
//							&& loginCredentials.getLockoutTime().isBefore(LocalDateTime.now().minusHours(1))) {
//						loginCredentials.setLockoutCount(0); // Reset lockout count after 1 hour of inactivity
//					}
//
//					loginCredentialsDaoImpl.updateFailedAttempts(loginCredentials); // Save changes
//				}
//
//				// Check if the password is correct
//				if (loginCredentials.getPasswordHash().equals(password)) {
//					// Successful login: Reset failed attempts and lockout details
//					loginCredentials.setFailedAttempts(0); // Reset failed attempts
//					loginCredentials.setLockoutTime(null); // Clear lockout time
//					loginCredentials.setLockoutCount(0); // Reset lockout count after successful login
//					loginCredentialsDaoImpl.updateFailedAttempts(loginCredentials); // Save changes
//
//					Long userId = loginCredentials.getUserId();
//					String role = loginCredentialsDaoImpl.getRole(userId);
//					Long employeeId = loginCredentials.getEmployee().getEmployeeId();
//					String passwordStatus = loginCredentials.getIsPasswordChange();
//					return new LoginResponse(userId, "Success", role, employeeId, passwordStatus);
//
//				} else {
//					// Increment failed login attempts
//					int failedAttempts = loginCredentials.getFailedAttempts() + 1;
//					loginCredentials.setFailedAttempts(failedAttempts);
//
//					// Lockout logic: if failed attempts exceed 3, lock the account
//					int lockoutDuration = 5; // Default to 5 minutes lockout for the first 3 failed attempts
//
//					// If the account has been locked more than once (2 or more times), lock for 1
//					// hour
//					if (loginCredentials.getLockoutCount() >= 2) {
//						lockoutDuration = 60; // 1 hour
//					}
//
//					// If failed attempts exceed 3, lock the account and increase lockout count
//					if (failedAttempts >= 3) {
//						loginCredentials.setLockoutTime(LocalDateTime.now().plusMinutes(lockoutDuration)); // Set
//																											// lockout
//																											// time
//						loginCredentials.setLockoutCount(loginCredentials.getLockoutCount() + 1); // Increment lockout
//																									// count
//					}
//
//					loginCredentialsDaoImpl.updateFailedAttempts(loginCredentials); // Save changes
//
//					return new LoginResponse(null, "Invalid credentials. " + (3 - failedAttempts) + " attempt(s) left.",
//							"Unknown");
//				}
//			} else {
//				
//			}
//		}

//	}

	@Override
	public void generateAndSendOtp(String email) {
		String otp = String.valueOf(new Random().nextInt(9999)); // Generate a random 6-digit OTP
		otpStorage.put(email, otp);
		otpExpiry.put(email, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)); // Set expiry for 5 minutes

		// Use EmailService to send the OTP
		String subject = "Your OTP Code";
		String body = "Your OTP code is: " + otp + ". It is valid for 1 minutes.";
		emailService.sendEmail(email, subject, body); // Send OTP via email
	}

	@Override
	public boolean verifyOtp(String email, String submittedOtp) {
		Long expiryTime = otpExpiry.get(email);
		if (expiryTime != null && System.currentTimeMillis() < expiryTime) {
			String storedOtp = otpStorage.get(email);
			if (storedOtp != null && storedOtp.equals(submittedOtp)) {

				otpStorage.remove(email);
				otpExpiry.remove(email);
				return true; // OTP verified successfully
			}
		}
		return false; // OTP is invalid or expired
	}

	@Override
	public LoginCredentials getLoginCredentialsById(Long userId) {
		return loginCredentialsDao.findById(userId);
	}

	@Override
	public String updatePassword(String email, String password) {
		LoginCredentials loginCredentials = loginCredentialsDaoImpl.findEmail(email);

		if (loginCredentials != null) {
			loginCredentials.setPasswordHash(password);
			loginCredentials.setIsPasswordChange("YES");
		}
		String response = loginCredentialsDao.updatePassword(loginCredentials);

		return response;

	}

}
