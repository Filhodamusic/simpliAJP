package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Account;
import com.entity.Login;
import com.repository.AccountRepo;
import com.repository.LoginRepo;

@Service
public class LoginService {
	@Autowired
	LoginRepo loginRepo;
	//@Autowired
	//AccountService accountService;
	
	public String signIn(Login login) {
		if(loginRepo.findLoginByEmailId(login.getEmailid().toString()).isEmpty()) {
			return "success";
		}else {
			return "failure";
		}
	}
	
	public String signUp(Login login) {
		if(loginRepo.findLoginByEmailId(login.getEmailid().toString()).isEmpty()) {
			Login myLogin = login;
			Account myAccount = new Account(login.getEmailid().split("@")[0],10000000,login.getEmailid());
			//accountService.save(myAccount);
			//myLogin.setAccno(accountService.getAccountByEmailId());
			myLogin.setUserType("user");
			myLogin.setAccno(2);
			loginRepo.save(myLogin);
			return "success";
		}else {
			return "Account Already Exists";
		}
	}
	
}
