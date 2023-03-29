package com.aws.ses.notification.service.emailcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ses.notification.service.emailservice.EmailService;
import com.aws.ses.notification.service.model.EmailDetails;

@RestController
public class EmailController {

	
	public EmailController(EmailService emailService) {
		super();
		this.emailService = emailService;
	}

	private EmailService emailService;

	@PostMapping("/sendEmail")
	public String sendMessage(@RequestBody EmailDetails emailDetails) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(emailDetails.getFromEmail());
		simpleMailMessage.setTo(emailDetails.getToEmail());
		simpleMailMessage.setSubject(emailDetails.getSubject());
		simpleMailMessage.setText(emailDetails.getBody());
		emailService.sendMessage(simpleMailMessage);

		return "Email sent successfully";
	}
}
