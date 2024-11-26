package com.rts.tap.emailservice;

import java.time.LocalDateTime;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.ClientForgotPasswordDAO;
import com.rts.tap.model.Client;
import com.rts.tap.utils.OtpUtils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientEmailServiceImpl implements ClientEmailService {

	private ClientForgotPasswordDAO clientForgotPasswordDAO;
	private JavaMailSender mail;

	public ClientEmailServiceImpl(ClientForgotPasswordDAO clientForgotPasswordDAO, JavaMailSender mail,
			EntityManager entityManager) {
		super();
		this.clientForgotPasswordDAO = clientForgotPasswordDAO;
		this.mail = mail;
	}

	@Override
	public String sendOtp(String clientEmail) {
		if (!clientForgotPasswordDAO.emailExists(clientEmail)) {
			return "Client email does not exist!";
		}
		else {
			Client client = clientForgotPasswordDAO.findClientByEmail(clientEmail);
			if (client == null) {
				return "Client not found";
			} else {
				String clientNameInEntity = client.getClientName();
				String otp = OtpUtils.generateOTP();
				client.setOtp(otp);
				client.setExpirationTime(LocalDateTime.now().plusMinutes(5));
				
				MimeMessage mailMessage = mail.createMimeMessage();
				
				try {
					MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
					helper.setTo(clientEmail);
					helper.setSubject("Forgot Password OTP code");
					
					String emailContent = "<html><body>" + "<div style='text-align: center;'>"
							+ "<img src='cid:logoImage'/>" + "</div>" + "<h2>Dear " + clientNameInEntity + ",</h2>"
							+ "<p>We have received a request to reset your password. Please use the OTP code below to proceed with updating your password.</p>"
							+ "<div style='text-align: center; font-size: 24px; color: red;'><b>" + otp + "</b></div>"
							+ "<p>Please note that this OTP is valid only for 5 minutes. If you did not request a password reset, please ignore this email.</p>"
							+ "<p>Thank you for your attention.</p>" + "<p>Best Regards,</p>" + "<p>Relevantz Technolgies and Services"
							+ "</body></html>";
					helper.setText(emailContent, true);
					
					helper.addInline("logoImage", new ClassPathResource("/static/images/Relevantz-logo.webp"));
					
					mail.send(mailMessage);
					return "OTP sent successfully!";
				} catch (MessagingException | MailException e) {
					return "Error in sending OTP";
				}
			}
		}
	}

}
