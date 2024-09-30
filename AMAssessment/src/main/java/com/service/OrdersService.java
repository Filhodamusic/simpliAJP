package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.OrderShoe;
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
		for (OrderShoe orderShoe : order.getOrderShoe()) {	
			Optional<Shoe> result = shoeRepository.findById(orderShoe.getShoe().getPid());
			 if (result.isPresent()) {
		            Shoe shoe = result.get();
		            if (shoe.getQty() - orderShoe.getQuantity() >= 0) {
		                shoe.setQty(shoe.getQty() - orderShoe.getQuantity());
		                orderShoe.setOrder(order); 
		            } else {
		                return "failure";
		            }
		        } else {
		            return "failure"; 
		        }
		    }
		    order.setOrderdatatime(LocalDateTime.now());
		    ordersRepository.save(order);
		    shoeRepository.saveAll(order.getOrderShoe().stream().map(OrderShoe::getShoe).collect(Collectors.toList()));
		    
		    return "success"; // All items processed successfully
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
	public List<Orders> findOrdersByEmailId(String emailId){
		return ordersRepository.findOrderByEmailId(emailId);
	}
	
}
