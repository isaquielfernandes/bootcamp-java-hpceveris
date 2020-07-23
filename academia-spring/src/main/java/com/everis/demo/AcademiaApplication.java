package com.everis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class AcademiaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}
	
}
