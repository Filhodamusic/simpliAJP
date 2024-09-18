package com.example.SBMVCLoginApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.SBMVCLoginApp.bean.Login;

@Repository
public class LoginDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int signIn(Login login) {
		try {
	return jdbcTemplate.queryForList("select * from login where emailid=? and password=?", 
			login.getEmailid(),login.getPassword()).size();		
		} catch (Exception e) {
			System.err.println(e);
			return 0;
		}
	}
	
	public int signUp(Login login) {
		try {
			System.out.println("ENTREI AQUI CRL");
	return jdbcTemplate.update("insert into login values(?,?)", login.getEmailid(),login.getPassword());	

		} catch (Exception e) {
			System.err.println(e);
			return 0;
		}
	}
}
