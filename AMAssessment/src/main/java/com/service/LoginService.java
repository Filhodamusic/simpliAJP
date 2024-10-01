package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Account;
import com.entity.Login;
import com.entity.LoginAccountDTO;
import com.entity.LoginTransfer;
import com.entity.Orders;
import com.entity.Shoe;
import com.repository.AccountRepo;
import com.repository.LoginRepo;

@Service
public class LoginService {
	@Autowired
	LoginRepo loginRepo;
	@Autowired
	AccountService accountService;
	
	public String signIn(LoginTransfer login) {
		if(loginRepo.findLoginByEmailId(login.getEmailid().toString())==null) {
			return "failure";
		}else {
			if(loginRepo.findLoginByEmailId(login.getEmailid().toString()).getPassword().contentEquals(login.getPassword())) {
				if(loginRepo.findLoginByEmailId(login.getEmailid().toString()).getUserType().contentEquals("admin")) {
					return "admin";
				}else {
					return "user";
				}
			}else {
				return "failure";
			}
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
				return "user";		
			}
		}else {
			return "failure";
		}
	}
	public String updateAdminPass(LoginTransfer login) {
		if(loginRepo.findLoginByEmailId("admin@gmail.com").getEmailid().isEmpty()) {
			return "ADMIN ACCOUNT WAS ERASED! ALERT!!!!";
		} else {
			Login myLogin = loginRepo.findLoginByEmailId("admin@gmail.com");
			if(myLogin.getPassword().contentEquals(login.getEmailid())) {
				myLogin.setPassword(login.getPassword());
				loginRepo.save(myLogin);
				return "success";
			}else {
				return "failure";
			}

		}
	}
	public Login findLoginByEmail(String emailId) {
		Optional<Login> result=Optional.ofNullable(loginRepo.findLoginByEmailId(emailId));
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	
	public LoginAccountDTO searchUser(String emailId) {
		Login login = loginRepo.findLoginByEmailId(emailId);
        if (login != null) {
            Account account = accountService.findAccountByEmail(login.getEmailid());
            LoginAccountDTO loginAccountDTO = new LoginAccountDTO();
            loginAccountDTO.setEmailid(login.getEmailid());
            loginAccountDTO.setUserType(login.getUserType());
            loginAccountDTO.setAccno(login.getAccno());

            if (account != null) {
                loginAccountDTO.setAmount(account.getAmount());
                loginAccountDTO.setName(account.getName());
            } else {
                loginAccountDTO.setAmount(0);
                loginAccountDTO.setName("UNKNOWN");
            }

            return loginAccountDTO;
        } else {
        	return null;
        }
    }
        
	public List<LoginAccountDTO> findAllUsers() {
		List<Login> loginList = loginRepo.findAll();
        return loginList.stream().map(login -> {

            Account account = accountService.findAccountByEmail(login.getEmailid());

            LoginAccountDTO loginAccountDTO = new LoginAccountDTO();
            loginAccountDTO.setEmailid(login.getEmailid());
            loginAccountDTO.setUserType(login.getUserType());
            loginAccountDTO.setAccno(login.getAccno());

            if (account != null) {
                loginAccountDTO.setAmount(account.getAmount());
                loginAccountDTO.setName(account.getName());
            } else {
                loginAccountDTO.setAmount(0);
                loginAccountDTO.setName("UNKNOWN");
            }

            return loginAccountDTO;
        }).collect(Collectors.toList());
    }
	
}
