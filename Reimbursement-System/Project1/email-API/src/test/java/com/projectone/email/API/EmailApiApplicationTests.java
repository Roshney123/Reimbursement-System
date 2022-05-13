package com.projectone.email.API;

import com.projectone.email.API.EmailService.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.internet.AddressException;

/** Tests for EmailApiApplication*/
@SpringBootTest
class EmailApiApplicationTests {
	@Autowired
	EmailService emailService;
	/** Test for email address is null*/
	@Test
	public void shouldThrowAddressException() {
		AddressException addressException=Assertions.assertThrows(AddressException.class,()->{

			emailService.sendMail(null);
		});
		Assertions.assertEquals("Mail Id Cannot be null",addressException .getMessage(), "Can't throw null value for emailId");


	}
	/** Happy path should send email, if email is passed correctly */
	@Test
	public void shouldSendEmail()throws Exception{
		Assertions.assertNotNull(emailService.sendMail("roshney.kuriakose@gmail.com"));

			Assertions.assertEquals("Email Sent Successfully", "Email Sent Successfully");

		}
	@Test
	void contextLoads() {
	}

}
