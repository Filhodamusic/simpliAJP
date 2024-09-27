package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.entity.Login;

@Repository
public interface LoginRepo extends JpaRepository<Login, Integer>{
	@Query("select l from Login l where l.emailid = :emailid")
	public Login findLoginByEmailId(@Param("emailid") String emailid);
}