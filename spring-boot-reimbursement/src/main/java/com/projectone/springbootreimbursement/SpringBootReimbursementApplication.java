package com.projectone.springbootreimbursement;

		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.annotation.Bean;
		import org.springframework.web.client.RestTemplate;

/**
 * main Controller of Spring Boot Application
 */
@SpringBootApplication
public class SpringBootReimbursementApplication {


	public static void main(String[] args) {

		/** To get logging message*/
		Logger logger = LoggerFactory.getLogger( SpringBootReimbursementApplication.class);
		logger.info("Starting in main");

		SpringApplication.run(SpringBootReimbursementApplication.class, args);

	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}