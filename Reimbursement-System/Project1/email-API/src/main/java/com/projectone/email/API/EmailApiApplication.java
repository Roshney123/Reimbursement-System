package com.projectone.email.API;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application to receive a request to send an email message to specific user
 * Main Controller
 */
@SpringBootApplication
public class EmailApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmailApiApplication.class, args);
	}

	}

