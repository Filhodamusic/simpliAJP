package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.LoginTransfer;
import com.entity.Orders;
import com.service.LoginService;
import com.service.OrdersService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("orders") // http://localhost:9191/orders
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	@Autowired
	LoginService loginService;
// curl -X POST http://localhost:9191/orders/place -H "Content-Type:application/json" -d '{"pid":111,"qty":2}'
	@PostMapping(value = "place")
	public String placeOrders(@RequestParam("pid") int pid, @RequestParam("qty") int qty, HttpSession session) {
	    Orders myOrder = new Orders();
	    myOrder.setPid(pid);
	    myOrder.setQty(qty);

	    LoginTransfer myLoginTransfer = (LoginTransfer) session.getAttribute("loggedInUser");
	    if (myLoginTransfer != null) {
	        myOrder.setLogin(loginService.findLoginByEmail(myLoginTransfer.getEmailid()));
	    }

	    return ordersService.placeOrder(myOrder);
	}

//	@PostMapping(value = "place")
//	public String placeOrders(@RequestBody OrdersTransfer orderRequest, HttpSession session) {
//	    Orders myOrder = new Orders();
//	    myOrder.setPid(orderRequest.getPid()); 
//	    myOrder.setQty(orderRequest.getQty()); 
//	   
//	    LoginTransfer myLoginTransfer = (LoginTransfer) session.getAttribute("loggedInUser");
//	    if (myLoginTransfer != null) {
//	        myOrder.setLogin(loginService.findLoginByEmail(myLoginTransfer.getEmailid()));
//	    }
//	    return ordersService.placeOrder(myOrder);
//	}

	
	// curl -X GET http://localhost:9191/orders/find
	@GetMapping(value = "find",produces = MediaType.APPLICATION_JSON_VALUE)
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
}
