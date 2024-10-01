package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Login;
import com.entity.LoginTransfer;
import com.entity.OrderShoe;
import com.entity.OrderShoeTransfer;
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
		 mm.addAttribute("logintransfer", new LoginTransfer());
		LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && loggedInUser.getEmailid().contentEquals("admin@gmail.com")) {
            mm.addAttribute("logintransfer", loggedInUser); 
            mm.addAttribute("orders", ordersService.findAllOrders());
            mm.addAttribute("usersRegistered", loginService.findAllUsers());
        }
		return "admin";
	}
	
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public String openUserPage(HttpSession session, Model mm) { 
		OrderShoeTransfer orderShoeTransfer = new OrderShoeTransfer();
        orderShoeTransfer.setOrderShoes(new ArrayList<>());
        mm.addAttribute("orderShoeTransfer", orderShoeTransfer);
	
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
	@RequestMapping(value = "/admin/updateAdminPass",method = RequestMethod.POST)
	public String updateAdminPass(@ModelAttribute("logintransfer") LoginTransfer ll, Model mm, HttpSession session) {
		LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && loggedInUser.getEmailid().contentEquals("admin@gmail.com")) {
    		mm.addAttribute("login", ll);
    		String result = loginService.updateAdminPass(ll);
    		if(result.equals("success")) {
    			return "redirect:/admin";
    		}else {
    			return "redirect:/admin?error";
    		}
		}else {
			return "redirect:/admin?error";
		}

	}
	@RequestMapping(value = "/admin/findAllUsers",method = RequestMethod.GET)
	public String findAllUsers(HttpSession session, Model mm) {  
		 mm.addAttribute("logintransfer", new LoginTransfer());
		LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");
        if (loggedInUser != null&& loggedInUser.getEmailid().contentEquals("admin@gmail.com")) {
            
        }
        return "redirect:/admin";
	}
}
