
package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Shoe;

@Repository
public interface ShoeRepo extends JpaRepository<Shoe, Integer>{

	
	@Query("select s from Shoe s where s.price > :my_price")
	public List<Shoe> findShoeByPrice(@Param("my_price") float price);
	
	//@Modifying
	//@Transactional
	//@Query("update Product p set p.qty=:qty where p.price >= :price")
	//public int updateProductQtyUsingPrice(@Param("qty") int qty, @Param("price") float price);
}
