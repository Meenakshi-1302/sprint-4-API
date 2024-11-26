package com.rts.tap.emailservice;

import java.time.LocalDateTime;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.ThirdPartyDAO;
import com.rts.tap.model.Client;
import com.rts.tap.model.ThirdPartyCredentitals;
import com.rts.tap.model.Vendor;
import com.rts.tap.utils.OtpUtils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ThirdPartyEmailServiceImpl implements ThirdPartyEmailService {

	private JavaMailSender mail;
	private ThirdPartyDAO thirdPartyDAO;

	public ThirdPartyEmailServiceImpl(JavaMailSender mail,
			ThirdPartyDAO thirdPartyDAO) {
		super();
		this.mail = mail;
		this.thirdPartyDAO = thirdPartyDAO;
	}

	@Override
	public String sendOtp(String email) {
		if (thirdPartyDAO.emailExists(email)) {

			ThirdPartyCredentitals thirdPartyCredentitals = thirdPartyDAO.findbyEmail(email);
			
			if (thirdPartyCredentitals != null) {
				
				String role = thirdPartyCredentitals.getRole().getRole();
				Long tpcId = thirdPartyCredentitals.getThirdPartyCredentialsId();
				
				if (role.equalsIgnoreCase("client")) {
					Client client = thirdPartyDAO.findClientByThirdPartyId(tpcId);

					String clientNameInEntity = client.getClientName();
					String otp = OtpUtils.generateOTP();
					thirdPartyCredentitals.setOtp(otp);
					thirdPartyCredentitals.setExpirationTime(LocalDateTime.now().plusMinutes(5));

					MimeMessage mailMessage = mail.createMimeMessage();

					try {
						MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
						helper.setTo(email);
						helper.setSubject("Forgot Password OTP code");

						String emailContent = "<html><body>" + "<div style='text-align: center;'>"
								+ "<img src='cid:logoImage'/>" + "</div>" + "<h2>Dear " + clientNameInEntity + ",</h2>"
								+ "<p>We have received a request to reset your password. Please use the OTP code below to proceed with updating your password.</p>"
								+ "<div style='text-align: center; font-size: 24px; color: red;'><b>" + otp
								+ "</b></div>"
								+ "<p>Please note that this OTP is valid only for 5 minutes. If you did not request a password reset, please ignore this email.</p>"
								+ "<p>Thank you for your attention.</p>" + "<p>Best Regards,</p>"
								+ "<p>Relevantz Technolgies and Services" + "</body></html>";
						helper.setText(emailContent, true);

						helper.addInline("logoImage", new ClassPathResource("/static/images/Relevantz-logo.webp"));

						mail.send(mailMessage);
						return "OTP sent successfully!";
					} catch (MessagingException | MailException e) {
						return "Error in sending OTP";
					}
				}
				
				else if(role.equalsIgnoreCase("vendor")) {
					Vendor vendor = thirdPartyDAO.findVendorByThirdPartyId(tpcId);

					String vendorNameInEntity = vendor.getContactName();
					String otp = OtpUtils.generateOTP();
					thirdPartyCredentitals.setOtp(otp);
					thirdPartyCredentitals.setExpirationTime(LocalDateTime.now().plusMinutes(5));

					MimeMessage mailMessage = mail.createMimeMessage();

					try {
						MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
						helper.setTo(email);
						helper.setSubject("Forgot Password OTP code");

						String emailContent = "<html><body>" + "<div style='text-align: center;'>"
								+ "<img src='cid:logoImage'/>" + "</div>" + "<h2>Dear " + vendorNameInEntity + ",</h2>"
								+ "<p>We have received a request to reset your password. Please use the OTP code below to proceed with updating your password.</p>"
								+ "<div style='text-align: center; font-size: 24px; color: red;'><b>" + otp
								+ "</b></div>"
								+ "<p>Please note that this OTP is valid only for 5 minutes. If you did not request a password reset, please ignore this email.</p>"
								+ "<p>Thank you for your attention.</p>" + "<p>Best Regards,</p>"
								+ "<p>Relevantz Technolgies and Services" + "</body></html>";
						helper.setText(emailContent, true);

						helper.addInline("logoImage", new ClassPathResource("/static/images/Relevantz-logo.webp"));

						mail.send(mailMessage);
						return "OTP sent successfully!";
					} catch (MessagingException | MailException e) {
						return "Error in sending OTP";
					}
				}
			}
			else {
				return "thirdPartyCredentitals is null";
			}
		}
		return "Invalid Email";
	}



}
