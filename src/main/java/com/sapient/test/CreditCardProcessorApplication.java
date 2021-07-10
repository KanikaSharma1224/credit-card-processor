package com.sapient.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CreditCardProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardProcessorApplication.class, args);
	}

}
