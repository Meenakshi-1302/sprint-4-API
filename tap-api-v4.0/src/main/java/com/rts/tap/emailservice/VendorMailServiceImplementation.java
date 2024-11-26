package com.rts.tap.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.VendorDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

/** 
 * author: Jeevarajan Rajarajacholan
 * version: v1.0
 * updated at: 04-11-2024
**/

@Service
@Transactional
public class VendorMailServiceImplementation implements VendorMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.from-name}")
	private String fromName;

	@Value("${spring.mail.username}")
	private String fromEmail;

	String loginUrl = MessageConstants.VENDOR_LOGIN_URL;

	@Override
	public String sendVendorCredentials(VendorDto vendorDto) throws MessagingException {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			String closingDiv = "</div>";
			helper.setFrom(fromName+" <"+fromEmail+">");
			helper.setTo(vendorDto.getEmail());
			helper.setSubject("Welcome to Relevantz Technology and Services");
			String htmlContent = "<html>"
					+ "<body style='font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, sans-serif; background-color: #f5f5f7; color: #1d1d1f; padding: 20px;'>"
					+ "<div style='max-width: 600px; background-color: #ffffff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); margin: auto;'>"
					// Logo section
					+ "<img src='https://relevantz.com/wp-content/uploads/2022/07/Relevantz_Logo-png200.png' alt='Relevantz Technology and Services' style='max-width: 150px; display: block; margin: 0 auto 30px;'>"
					// Welcome title
					+ "<h1 style='font-size: 28px; font-weight: 600; color: #1d1d1f; text-align: center; margin-bottom: 10px;'>Welcome to Relevantz Technology Services</h1>"
					+ "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Dear "
					+ vendorDto.getOrganizationName() + ",</p>"
					// Message content
					+ "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>We’re delighted to partner with you in sourcing talented individuals for our team. Below, you’ll find the credentials to access your account and begin submitting candidates.</p>"
					// Credentials
					+ "<div class='credentials' style='background-color: #f5f5f7; padding: 15px; border-radius: 8px; text-align: center; margin-top: 20px;'>"
					+ "<p><strong>Username:</strong> " + vendorDto.getUsername() + "</p>"
					+ "<p><strong>Password:</strong> " + vendorDto.getPassword() + "</p>"
					+ "<p><strong>Login URL:</strong> <a href='" + loginUrl
					+ "' style='color: #0071e3; text-decoration: none;'>Access Your Account</a></p>" + closingDiv
					// Closing message
					+ "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Should you have any questions or require assistance, feel free to <a href='mailto:[your_email]' style='color: #0071e3; text-decoration: none;'>contact us</a> at any time. We’re here to ensure your experience is as smooth and effective as possible.</p>"
					// Social links
					+ "<div class='social-links' style='margin-top: 20px; text-align: center;'>"
					+ "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Connect with us:</p>"
					+ "<a href='[facebook_link]' style='color: #0071e3; text-decoration: none; margin: 0 10px;'>Facebook</a>"
					+ "<a href='[twitter_link]' style='color: #0071e3; text-decoration: none; margin: 0 10px;'>Twitter</a>"
					+ "<a href='[linkedin_link]' style='color: #0071e3; text-decoration: none; margin: 0 10px;'>LinkedIn</a>"
					+ closingDiv
					+ "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Thank you for collaborating with us. We look forward to welcoming exceptional talent to Relevantz Technology and Services.</p>"
					// Footer
					+ "<div class='footer' style='text-align: center; font-size: 14px; color: #86868b; margin-top: 30px;'>"
					+ "<p>Best regards,<br>TAP Team<br>Relevantz Technology and Services</p>" + closingDiv
					+ closingDiv + "</body>" + "</html>";
			helper.setText(htmlContent, true);
			javaMailSender.send(message);
			return MessageConstants.MESSAGE_SENT_SUCCESS;
		} catch (Exception e) {
			return MessageConstants.MESSAGE_SENT_FAILED;
		}

	}

}
