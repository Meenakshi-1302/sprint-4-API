package com.rts.tap.emailservice;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.rts.tap.constants.MailConstants;
import java.util.List;

@Service
public class MailService {

    private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	public void sendAssessmentNotifications(ScheduleRequest scheduleRequest) {
        List<String> candidateEmails = scheduleRequest.getCandidateEmails();
        String subject = MailConstants.MAIL_SEND_ASSESSMENT_NOTIFICATION_SUBJECT + scheduleRequest.getAssessmentName();
        String message = "You have been scheduled for an assessment.\n\n" +
                         "Assessment Name: " + scheduleRequest.getAssessmentName() + "\n" +
                         "Assessment Link: " + scheduleRequest.getAssessmentLink() + "\n" +
                         "Assessment Type: " + scheduleRequest.getAssessmentType() + "\n\n" +
                         "Assessment Start Date: " + scheduleRequest.getAssessmentStartDate() + "\n" +
                         "Assessment End Date: " + scheduleRequest.getAssessmentEndDate() + "\n" +
                         MailConstants.GOOD_LUCK;

        for (String email : candidateEmails) {
            sendEmail(email, subject, message);
        }
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("tap.emailservice@gmail.com"); 
        mailSender.send(message);
    }
}

