package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Login;
import com.entity.LoginTransfer;
import com.entity.Orders;
import com.service.LoginService;
import com.service.OrdersService;
import com.service.ShoeService;

import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("login")
public class LoginController {
	@Autowired
	LoginService loginService;
	@Autowired
	ShoeService shoeService;
	@Autowired
	OrdersService ordersService;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String openLoginPage(@ModelAttribute("logintransfer") LoginTransfer ll, Model mm) {  
		mm.addAttribute("login", ll);		
		return "login";
	}
	
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String openAdminPage(HttpSession session, Model mm) {  	
		LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            mm.addAttribute("logintransfer", loggedInUser); 
        }
		return "admin";
	}
	
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public String openUserPage(HttpSession session, Model mm) { 
		mm.addAttribute("orders", new Orders());	
		LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            mm.addAttribute("logintransfer", loggedInUser); 
            mm.addAttribute("shoes", shoeService.findAll());
            mm.addAttribute("orders", ordersService.findOrdersByEmailId(loggedInUser.getEmailid()));
        }

		return "user";
	}
	
	
	@RequestMapping(value = "signIn",method = RequestMethod.POST)
	public String signIn(@ModelAttribute("logintransfer") LoginTransfer ll, Model mm, HttpSession session) {  
		mm.addAttribute("login", ll);	
		mm.addAttribute("orders", new Orders());
		String result = loginService.signIn(ll);
		if(result.equals("user")) {
			session.setAttribute("loggedInUser", ll);
			return "redirect:/user";
		}else {
			if(result.equals("admin")) {
				session.setAttribute("loggedInUser", ll);
				return "redirect:/admin";
			}else {
				return "redirect:/login?error";
			}
		}
	}
	
	@RequestMapping(value = "signUp",method = RequestMethod.POST)
	public String signUp(@ModelAttribute("logintransfer") LoginTransfer ll, Model mm, HttpSession session) {
		mm.addAttribute("login", ll);
		String result = loginService.signUp(ll);
		if(result.equals("user")) {
			session.setAttribute("loggedInUser", ll);
			return "redirect:/user";
		}else {
			session.setAttribute("loggedInUser", ll);
			return "redirect:/login?error";
		}
	}
	@RequestMapping(value = "updateAdminPass",method = RequestMethod.POST)
	public String updateAdminPass(@ModelAttribute("logintransfer") LoginTransfer ll, Model mm) {
		mm.addAttribute("login", ll);
		String result = loginService.updateAdminPass(ll);
		if(result.equals("Admin pass updated")) {
			return "success";
		}else {
			return "failure";
		}
	}
}
