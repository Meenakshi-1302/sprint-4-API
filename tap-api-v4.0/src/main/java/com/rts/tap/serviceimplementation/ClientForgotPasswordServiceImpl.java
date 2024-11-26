package com.rts.tap.serviceimplementation;

import java.time.LocalDateTime;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.ClientForgotPasswordDAO;
import com.rts.tap.model.Client;
import com.rts.tap.service.ClientForgotPasswordService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientForgotPasswordServiceImpl implements ClientForgotPasswordService {

	private ClientForgotPasswordDAO clientForgotPasswordDAO;
	private JavaMailSender mail;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	private EntityManager entityManager;

	public ClientForgotPasswordServiceImpl(ClientForgotPasswordDAO clientForgotPasswordDAO, JavaMailSender mail,
			EntityManager entityManager) {
		super();
		this.clientForgotPasswordDAO = clientForgotPasswordDAO;
		this.mail = mail;
		this.entityManager = entityManager;
	}

	@Override
	public boolean emailExists(String clientEmail) {
		return clientForgotPasswordDAO.emailExists(clientEmail);
	}

//	@Override
//	public String sendOtp(String clientEmail) {
//
//		if (!clientForgotPasswordDAO.emailExists(clientEmail)) {
//			return "Client email does not exist!";
//		}
//
//		Client client = clientForgotPasswordDAO.findClientByEmail(clientEmail);
//		if (client == null) {
//			return "Client not found";
//		} else {
//			String clientNameInEntity = client.getClientName();
//			String otp = OtpUtils.generateOTP();
//			client.setOtp(otp);
//			client.setExpirationTime(LocalDateTime.now().plusMinutes(5));
//
//			SimpleMailMessage mailMessage = new SimpleMailMessage();
//			mailMessage.setTo(clientEmail);
//			mailMessage.setSubject("Forgot Password OTP code");
//			mailMessage.setText("Dear " + clientNameInEntity + ",\n\n Your OTP code for updating your password is: "
//					+ otp + "\n\nThank you!");
//			try {
//				mail.send(mailMessage);
//				return "OTP sent successfully!";
//			} catch (MailException e) {
//				return "Error in sending OTP";
//			}
//		}
//	}

	@Override
	public String updateForgotPassword(String clientEmail, String updatedPassword) {
		Client client = clientForgotPasswordDAO.findClientByEmail(clientEmail);
		if (client == null) {
			return "Client not found";
		}

	    String encodedPassword = encoder.encode(updatedPassword);
		try {
			clientForgotPasswordDAO.updatePassword(clientEmail, encodedPassword);
			return "Password Updated successfully";
		} catch (Exception e) {
			return "Password updation failed!";
		}
	}

	@Override
	public String verifyOtp(String otp, String clientEmail) {
		Client client = clientForgotPasswordDAO.findClientByEmail(clientEmail);
		if (client == null) {
			return "Client not found";
		}
		
		if (client.getOtp() == null || client.getExpirationTime().isBefore(LocalDateTime.now())) {
			return "Invalid or expired OTP!";
		} 
		else if(client.getOtp().equals(otp)) {
			client.setOtp(null);
			client.setExpirationTime(null);
			entityManager.merge(client);
			return "OTP verified";
		}
		return "Error in verifying OTP";
	}

}
