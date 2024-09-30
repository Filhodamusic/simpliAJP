
package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer>{

	@Query("SELECT o FROM Orders o JOIN o.orderShoe os WHERE os.shoe.id = :pid")
	public List<Orders> findOrderByProcuctId(@Param("pid") int pid);
	
	@Query("select o from Orders o where o.orderdatatime = :orderdatatime")
	public List<Orders> findOrderByDate(@Param("orderdatatime") int orderdatatime);
	
	@Query("select o from Orders o where o.login.emailid = :emailid")
	public List<Orders> findOrderByEmailId(@Param("emailid") String emailid);
	
}