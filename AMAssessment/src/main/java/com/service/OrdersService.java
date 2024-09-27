package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Orders;
import com.entity.Shoe;
import com.repository.OrderRepo;
import com.repository.ShoeRepo;

@Service
public class OrdersService {

	@Autowired
	OrderRepo ordersRepository;
	
	@Autowired
	ShoeRepo shoeRepository;
	
	public String placeOrder(Orders order ) {		// pid, qty 
		Optional<Shoe> result = shoeRepository.findById(order.getPid());
		if(result.isPresent()) {
			Shoe p = result.get();
			order.setOrderdatatime(LocalDateTime.now());
			ordersRepository.save(order);
			p.setQty(p.getQty()-order.getQty());
			shoeRepository.saveAndFlush(p);
			return "order placed successfully";
		}else {
			return "Order not placed";
		}
	}
	
	public List<Orders> findAllOrders() {
		return ordersRepository.findAll();
	}
	
	public Orders findOrderById(int oid) {
		Optional<Orders> result = ordersRepository.findById(oid);
		if(result.isPresent()) {
			Orders o = result.get();
			return o;
		}else {
			return null;
		}
	}
	
	public List<Orders> findOrdersByShoeId(int pid){
		return ordersRepository.findOrderByProcuctId(pid);
	}
	
}
