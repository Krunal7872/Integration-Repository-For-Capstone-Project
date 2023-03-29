package com.aws.ses.notification.service.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	
	public EmailService(MailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	private MailSender mailSender;

	public void sendMessage(SimpleMailMessage simpleMailMessage) {
		this.mailSender.send(simpleMailMessage);
	}
}
