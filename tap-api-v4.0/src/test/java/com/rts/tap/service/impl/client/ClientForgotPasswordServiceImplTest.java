package com.rts.tap.service.impl.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;

import com.rts.tap.dao.ClientForgotPasswordDAO;
import com.rts.tap.model.Client;
import com.rts.tap.serviceimplementation.ClientForgotPasswordServiceImpl;
import com.rts.tap.utils.OtpUtils;

class ClientForgotPasswordServiceImplTest {
	@Mock
	private ClientForgotPasswordDAO clientForgotPasswordDAO;
	@Mock
	private JavaMailSender mailSender;
	@InjectMocks
	private ClientForgotPasswordServiceImpl clientForgotPasswordService;
	private Client client;

	@BeforeEach
	public void setUp() {
		client = new Client();
		client.setClientName("John Doe");
		client.setClientEmail("john.doe@example.com");
	}

	@Test
	public void testEmailExists() {
		when(clientForgotPasswordDAO.emailExists(anyString())).thenReturn(true);
		boolean exists = clientForgotPasswordService.emailExists("john.doe@example.com");
		assertTrue(exists);
	}

	@Test
	public void testUpdateForgotPassword_Success() {
		when(clientForgotPasswordDAO.findClientByEmail(anyString())).thenReturn(client);
		String result = clientForgotPasswordService.updateForgotPassword("john.doe@example.com", "newpassword");
		assertEquals("Password Updated successfully", result);
	}

	@Test
	public void testUpdateForgotPassword_ClientNotFound() {
		when(clientForgotPasswordDAO.findClientByEmail(anyString())).thenReturn(null);
		String result = clientForgotPasswordService.updateForgotPassword("unknown@example.com", "newpassword");
		assertEquals("Client not found", result);
	}

	@Test
	public void testVerifyOtp_Success() {
		String otp = OtpUtils.generateOTP();
		client.setOtp(otp);
		client.setExpirationTime(LocalDateTime.now().plusMinutes(5));
		when(clientForgotPasswordDAO.findClientByEmail(anyString())).thenReturn(client);
		String result = clientForgotPasswordService.verifyOtp(otp, "john.doe@example.com");
		assertEquals("OTP verified", result);
	}

	@Test
	public void testVerifyOtp_InvalidOrExpired() {
		client.setOtp(null);
		when(clientForgotPasswordDAO.findClientByEmail(anyString())).thenReturn(client);
		String result = clientForgotPasswordService.verifyOtp("someotp", "john.doe@example.com");
		assertEquals("Invalid or expired OTP!", result);
	}

}
