package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Login;
import com.entity.LoginAccountDTO;
import com.entity.LoginTransfer;
import com.entity.OrderShoe;
import com.entity.OrderShoeTransfer;
import com.entity.Orders;
import com.entity.Shoe;
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
            mm.addAttribute("shoes", shoeService.findAll());
            mm.addAttribute("shoe", new Shoe());
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
	
	@RequestMapping(value = "/admin/searchUser",method = RequestMethod.POST)
	public ModelAndView searchUser(HttpSession session, Model mm,@RequestParam("emailid") String emailId) {  
		ModelAndView mv = new ModelAndView("admin");
		 LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");

        if (loggedInUser != null&& loggedInUser.getEmailid().contentEquals("admin@gmail.com")) {
            mv.addObject("logintransfer", loggedInUser);
            mv.addObject("orders", ordersService.findAllOrders());
            mv.addObject("usersRegistered", loginService.findAllUsers());
            mv.addObject("shoes", shoeService.findAll());
        	Optional<LoginAccountDTO> login = Optional.ofNullable(loginService.searchUser(emailId));
            if (login.isPresent()) {
                LoginAccountDTO user = login.get();

                mv.addObject("user", user);
            } else {
                // If no user is found, set the user to null
            	mv.addObject("error", "No user found with the given email ID.");
            }
        }
        return mv;
	}
	
	@RequestMapping(value = "/admin/deleteShoe",method = RequestMethod.POST)
	public ModelAndView deleteShoe(HttpSession session, Model mm,@RequestParam("shoeId") int shoeId) {  
		ModelAndView mv = new ModelAndView("admin");
		 LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");

        if (loggedInUser != null&& loggedInUser.getEmailid().contentEquals("admin@gmail.com")) {
            mv.addObject("logintransfer", loggedInUser);
            mv.addObject("orders", ordersService.findAllOrders());
            mv.addObject("usersRegistered", loginService.findAllUsers());
            mv.addObject("shoes", shoeService.findAll());
            try {
                shoeService.deletedShoe(shoeId);
                mv.addObject("shoes", shoeService.findAll());
                mv.addObject("successMessage", "Shoe with ID " + shoeId + " deleted successfully.");
            } catch (Exception e) {
                mv.addObject("errorMessage", "Error occurred while trying to delete the shoe with ID " + shoeId);
            }
        }
        return mv;
	}
	@RequestMapping(value = "/admin/addShoe",method = RequestMethod.POST)
	public ModelAndView addShoe(HttpSession session, Model mm,@ModelAttribute Shoe shoe) {  
		ModelAndView mv = new ModelAndView("admin");
		 LoginTransfer loggedInUser = (LoginTransfer) session.getAttribute("loggedInUser");

        if (loggedInUser != null&& loggedInUser.getEmailid().contentEquals("admin@gmail.com")) {
            mv.addObject("logintransfer", loggedInUser);
            mv.addObject("orders", ordersService.findAllOrders());
            mv.addObject("usersRegistered", loginService.findAllUsers());
            mv.addObject("shoes", shoeService.findAll());
            try {
                shoeService.storeShoe(shoe);
                mv.addObject("shoes", shoeService.findAll());
                mv.addObject("successMessage", "Shoe added successfully.");
            } catch (Exception e) {
                mv.addObject("errorMessage", "Error occurred while trying to adding the shoe ");
            }
        }
        return mv;
	}
}
