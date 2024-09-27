package com.example.AMAssessment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.entity.Account;
import com.entity.Login;
import com.entity.Orders;
import com.repository.LoginRepo;
import com.repository.AccountRepo;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "com.repository")
public class AmAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmAssessmentApplication.class, args);
		

	}
	@Autowired
	LoginRepo loginRepo;
	@Autowired
	AccountRepo accountRepo;
	@PostConstruct
	public void init() {
		Login ll = new Login("admin@gmail.com","admin@123","admin",1);
		Optional<Login> result = Optional.ofNullable(loginRepo.findLoginByEmailId(ll.getEmailid()));
		if(result.isEmpty()) {
		    loginRepo.save(ll);
		    Account account = new Account("admin",10000000,"admin@gmail.com");
		    accountRepo.save(account);
		}
	}

}
