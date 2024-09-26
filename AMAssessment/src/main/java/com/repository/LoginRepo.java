package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Account;
import com.entity.Login;
import com.entity.Order;

@Repository
public interface LoginRepo extends JpaRepository<Login, Integer>{
	@Query("select l.emailid from Login l where l.emailid = :emailid")
	public List<Login> findLoginByEmailId(@Param("emailid") String emailid);
}