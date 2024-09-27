package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Account;
import com.entity.Login;
import com.entity.LoginTransfer;
import com.repository.AccountRepo;
import com.repository.LoginRepo;

@Service
public class LoginService {
	@Autowired
	LoginRepo loginRepo;
	@Autowired
	AccountService accountService;
	
	public String signIn(LoginTransfer login) {
		if(loginRepo.findLoginByEmailId(login.getEmailid().toString()).getEmailid().isEmpty()) {
			return "success";
		}else {
			return "failure";
		}
	}
	
	public String signUp(LoginTransfer login) {
		if(loginRepo.findLoginByEmailId(login.getEmailid().toString())==null) {
			if(login.getEmailid().equalsIgnoreCase("admin@gmail.com")) {
				return "Admin account can't signup";
			}else {
				Login myLogin = new Login();
				myLogin.setEmailid(login.getEmailid());
				myLogin.setPassword(login.getPassword());
				myLogin.setUserType("user");
				Account myAccount = new Account(login.getEmailid().split("@")[0],10000000,login.getEmailid());
				accountService.createAccount(myAccount);
				myLogin.setAccno(accountService.findAccno(login.getEmailid()));
				loginRepo.save(myLogin);
				return "success";		
			}
		}else {
			return "Account Already Exists";
		}
	}
	public String updateAdminPass(LoginTransfer login) {
		if(loginRepo.findLoginByEmailId("admin@gmail.com").getEmailid().isEmpty()) {
			return "ADMIN ACCOUNT WAS ERASED! ALERT!!!!";
		} else {
			if(loginRepo.findLoginByEmailId(login.getEmailid().toString()).getPassword().equalsIgnoreCase(login.getEmailid())) {
				Login myLogin = loginRepo.findLoginByEmailId(login.getEmailid().toString());
				myLogin.setPassword(login.getPassword());
				loginRepo.save(myLogin);
				return "success";
			}else {
				return "failure";
			}

		}
	}
	
}
