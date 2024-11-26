package com.rts.tap.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rts.tap.dto.RecruitingManagerAddCandidateDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CandidateMailServiceImplementation implements CandidateMailService {

	@Autowired
    private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.from-name}")
    private String fromName;
 
    @Value("${spring.mail.username}")
    private String fromEmail;
 
    @Value("${company.website}")
    private String companyWebsite;
 
    @Override
    public String sendCandidateConfirmation(RecruitingManagerAddCandidateDto candidateDto) throws MessagingException {
        try {
        	MimeMessage message = javaMailSender.createMimeMessage(); 
        	MimeMessageHelper helper = new MimeMessageHelper(message, true);       	
        	String closingDiv = "</div>";
         	helper.setFrom(fromName + " <" + fromEmail + ">");
         	helper.setTo(candidateDto.getEmail());
         	helper.setSubject("Your Application at Relevantz Technology Services");    	
        	String htmlContent = "<html>" 
        	        + "<body style='font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, sans-serif; background-color: #f5f5f7; color: #1d1d1f; padding: 20px;'>"
        	        + "<div style='max-width: 600px; background-color: #ffffff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); margin: auto;'>"
         	        // Logo section
         	        + "<img src='https://relevantz.com/wp-content/uploads/2022/07/Relevantz_Logo-png200.png' alt='Relevantz Technology and Services' style='max-width: 150px; display: block; margin: 0 auto 30px;'>"
         	        // Welcome title
         	        + "<h1 style='font-size: 28px; font-weight: 600; color: #1d1d1f; text-align: center; margin-bottom: 10px;'>Welcome to Relevantz Technology Services</h1>"
         	        // Introduction and body
         	        + "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Dear " + candidateDto.getFirstName()+" "+ candidateDto.getLastName() + ",</p>"
         	        + "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>We are pleased to inform you that we have received your application for a position at Relevantz Technology Services through our referral program.</p>"
         	        + "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Thank you for your interest in joining our team. Your experience and qualifications are currently under review, and we will notify you of the next steps in our hiring process.</p>"
         	        // Company exploration link
         	        + "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>In the meantime, feel free to explore our company further on our website: "
         	        + "<a href='" + companyWebsite + "' style='color: #0071e3; text-decoration: none;'>" + companyWebsite + "</a>.</p>"
         	        // Contact and assistance
         	        + "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>If you have any questions or need assistance, please don't hesitate to reach out to us.</p>"
         	        // Closing
         	        + "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Thank you for considering a career with us!</p>"
         	        // Social links (if applicable)
         	        + "<div class='social-links' style='margin-top: 20px; text-align: center;'>"
         	        + "<p style='font-size: 16px; line-height: 1.6; color: #6e6e73;'>Connect with us:</p>"
         	        + "<a href='[facebook_link]' style='color: #0071e3; text-decoration: none; margin: 0 10px;'>Facebook</a>"
         	        + "<a href='[twitter_link]' style='color: #0071e3; text-decoration: none; margin: 0 10px;'>Twitter</a>"
         	        + "<a href='[linkedin_link]' style='color: #0071e3; text-decoration: none; margin: 0 10px;'>LinkedIn</a>"
         	        + closingDiv   	
        	        // Footer section
        	        + "<div class='footer' style='text-align: center; font-size: 14px; color: #86868b; margin-top: 30px;'>"
        	        + "<p>Best regards,<br>"+ fromName +"<br>Relevantz Technology Services</p>"
        	        + closingDiv
        	        + closingDiv
        	        + "</body>"
        	        + "</html>";    	
        	helper.setText(htmlContent, true);
        	javaMailSender.send(message);     	
            return "Email sent successfully!";
        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email to the candidate.", e);
        }
    }
}
