
package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

	@Query("select o from Orders o where o.pid = :pid")
	public List<Order> findOrderByProcuctId(@Param("pid") int pid);
	
	@Query("select o from Orders o where o.orderdatatime = :orderdatatime")
	public List<Order> findOrderByDate(@Param("orderdatatime") int orderdatatime);
	
}