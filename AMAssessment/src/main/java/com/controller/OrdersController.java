package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.LoginTransfer;
import com.entity.OrderShoe;
import com.entity.OrderShoeTransfer;
import com.entity.Orders;
import com.entity.Shoe;
import com.service.LoginService;
import com.service.OrdersService;
import com.service.ShoeService;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("orders") // http://localhost:9191/orders
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	@Autowired
	LoginService loginService;
	@Autowired
	ShoeService shoeService;

// curl -X POST http://localhost:9191/orders/place -H "Content-Type:application/json" -d '{"pid":111,"qty":2}'
	
	@PostMapping(value = "place")
	public void placeOrders(@ModelAttribute OrderShoeTransfer orderShoeTransfer, HttpSession session, Model mm,HttpServletResponse response) throws IOException {
		try {
			Orders myOrder = new Orders();
			List<OrderShoe> orderShoeList = new ArrayList<>();
			
	        for (OrderShoeTransfer.OrderShoeItem item : orderShoeTransfer.getOrderShoes()) {
	            OrderShoe orderShoe = new OrderShoe();
	            orderShoe.setQuantity(item.getQuantity());

	            // Set the shoe object in the orderShoe
	            Shoe shoe = new Shoe();
	            shoe.setPid(item.getPid());
	            orderShoe.setShoe(shoe);;
	            System.out.println("Product ID: " + item.getPid() + ", Quantity: " + item.getQuantity());
	            orderShoeList.add(orderShoe);
	        }
	        myOrder.setOrderShoe(orderShoeList);
	
		    LoginTransfer myLoginTransfer = (LoginTransfer) session.getAttribute("loggedInUser");
		    if (myLoginTransfer != null) {
		        myOrder.setLogin(loginService.findLoginByEmail(myLoginTransfer.getEmailid()));
		    }
		    
		    String result=ordersService.placeOrder(myOrder);
		    if(result.contentEquals("success")) {
	           mm.addAttribute("shoes", shoeService.findAll());
	           mm.addAttribute("successMessage", "Order placed successfully!");
		    } else {
	    	  mm.addAttribute("shoes", shoeService.findAll());
	          mm.addAttribute("errorMessage", "Failed to place order. Please try again.");

		    } 
		          
	    } catch (Exception e) {
            // Handle failure and display error message
            mm.addAttribute("shoes", shoeService.findAll());
            mm.addAttribute("errorMessage", "Failed to place order. Please try again.");
        }
		response.sendRedirect("/user");
	}


	
	// curl -X GET http://localhost:9191/orders/find
	@GetMapping(value = "orders/find",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orders> findAllOrders() {
		return ordersService.findAllOrders();
	}
	// curl -X GET http://localhost:9191/orders/findbyid/1 path param 
	@GetMapping(value = "findbyid/{oid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Orders findAllOrderById(@PathVariable("oid") int oid) {
		return ordersService.findOrderById(oid);
	}
	
	// curl -X GET http://localhost:9191/orders/findorderbypid?pid=100 query param
	
	@GetMapping(value = "findorderbypid",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orders> findOrderDetailsByPId(@RequestParam("pid") int pid) {
		return ordersService.findOrdersByShoeId(pid);
	}
	
	@GetMapping(value = "findorderbyemailid",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orders> findOrderDetailsByEmailId(HttpSession session,HttpServletResponse response) throws IOException {
	    LoginTransfer myLoginTransfer = (LoginTransfer) session.getAttribute("loggedInUser");
	    List<Orders> ordersList = new ArrayList<>();
	    if (myLoginTransfer != null) {
	        ordersList = ordersService.findOrdersByEmailId(myLoginTransfer.getEmailid());
	    }
	    response.sendRedirect("/user");
	    return ordersList;
	}
	

}
