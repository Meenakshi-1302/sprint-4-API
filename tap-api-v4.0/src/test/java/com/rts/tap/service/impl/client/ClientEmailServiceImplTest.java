//package com.rts.tap.service.impl.client;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import com.rts.tap.dao.client.ClientForgotPasswordDAO;
//import com.rts.tap.model.Client;
//
//import jakarta.mail.internet.MimeMessage;
//
//class ClientEmailServiceImplTest {
//
//	@Mock
//	private ClientForgotPasswordDAO clientForgotPasswordDAO;
//	@Mock
//	private JavaMailSender mailSender;
//	@InjectMocks
//	private ClientEmailServiceImpl clientEmailService;
//	private Client client;
//
//	@BeforeEach
//	public void setUp() {
//		client = new Client();
//		client.setClientName("Zoho");
//		client.setClientEmail("zoho@relevantz.com");
//	}
//
//	@Test
//	public void testSendOtp_Success() throws Exception { // Arrange
//		when(clientForgotPasswordDAO.emailExists(anyString())).thenReturn(true);
//		when(clientForgotPasswordDAO.findClientByEmail(anyString())).thenReturn(client);
//		MimeMessage mimeMessage = mock(MimeMessage.class);
//		when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
//
//		String result = clientEmailService.sendOtp("john.doe@example.com");
//
//		assertEquals("OTP sent successfully!", result);
//		verify(mailSender, times(1)).send(any(MimeMessage.class));
//	}
//
//	@Test
//	public void testSendOtp_ClientEmailDoesNotExist() {
//		when(clientForgotPasswordDAO.emailExists(anyString())).thenReturn(false);
//		
//		// Act 
//		String result = clientEmailService.sendOtp("unknown@example.com");
//		assertEquals("Client email does not exist!", result);
//		verify(mailSender, never()).send(any(MimeMessage.class)); }
//		
//	@Test 
//	public void testSendOtp_ClientNotFound() {
//		when(clientForgotPasswordDAO.emailExists(anyString())).thenReturn(true);
//		when(clientForgotPasswordDAO.findClientByEmail(anyString())).thenReturn(null);
//		String result =
//		clientEmailService.sendOtp("unknown@example.com");
//		 assertEquals("Client not found", result); verify(mailSender, never()).send(any(MimeMessage.class)); 
//	} 
//	@Test 
//	public void testSendOtp_MailException() throws Exception {
//		when(clientForgotPasswordDAO.emailExists(anyString())).thenReturn(true);
//		when(clientForgotPasswordDAO.findClientByEmail(anyString())).thenReturn(client);
//		MimeMessage mimeMessage = mock(MimeMessage.class);
//		when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
//		doThrow(new MailException("Mail sending failed"){}).when(mailSender).send(any(MimeMessage.class));
//		
//		String result = clientEmailService.sendOtp("john.doe@example.com");
//		assertEquals("Error in sending OTP", result);
//		verify(mailSender, times(1)).send(any(MimeMessage.class)); 
//		}
//	}
//}
