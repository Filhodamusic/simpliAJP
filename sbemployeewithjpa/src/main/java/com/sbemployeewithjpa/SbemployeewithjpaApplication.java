package com.sbemployeewithjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com")	// enable @Restcontroller, @service @repository 
@EntityScan(basePackages = "com.entity")				// enable @entity 
@EnableJpaRepositories(basePackages = "com.repository")	// enable spring jpa data 
public class SbemployeewithjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbemployeewithjpaApplication.class, args);
	}

}
