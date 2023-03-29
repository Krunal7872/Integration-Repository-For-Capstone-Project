package com.aws.ses.notification.service.sesconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import io.awspring.cloud.mail.simplemail.SimpleEmailServiceJavaMailSender;
import io.awspring.cloud.mail.simplemail.SimpleEmailServiceMailSender;

@Configuration
public class SesConfig {

	@Value("${aws.credentials.acccessKey}")
	private String accessKey;

	@Value("${aws.credentials.secretKey}")
	private String secretKey;

	@Value("${aws.region.static}")
	private String region;

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
	}

	@Bean
	public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		return new io.awspring.cloud.ses.SimpleEmailServiceMailSender(amazonSimpleEmailService);
	}

//	@Bean
//	public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
//		return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
//	}
}
