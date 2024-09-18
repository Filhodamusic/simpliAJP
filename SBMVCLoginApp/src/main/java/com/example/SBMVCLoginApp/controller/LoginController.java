package com.example.SBMVCLoginApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SBMVCLoginApp.bean.Login;
import com.example.SBMVCLoginApp.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String openLoginPage(Login ll, Model mm) {  // DI for login and model api 
		mm.addAttribute("login", ll);		// login object stored in model scope. 
		return "login";
	}
	
	
	@RequestMapping(value = "signIn",method = RequestMethod.POST)
	public String signIn(Login ll, Model mm) {  // DI for login and model api 
		mm.addAttribute("login", ll);		// login object stored in model scope. 
		String result = loginService.signIn(ll);
		if(result.equals("success")) {
			return "success";
		}else {
			return "failure";
		}
	}
	
	@RequestMapping(value = "signUp",method = RequestMethod.POST)
	public String signUp(Login ll, Model mm) {  // DI for login and model api 
		mm.addAttribute("login", ll);		// login object stored in model scope. 
		String result = loginService.signUp(ll);
		if(result.equals("success")) {
			return "success";
		}else {
			return "failure";
		}
	}
}
