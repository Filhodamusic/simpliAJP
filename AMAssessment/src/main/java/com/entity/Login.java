package com.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Login {


	@Id
	private String emailid;
	private String password;
	private String userType;
	private int accno;
    @OneToMany()
    private List<Orders> orders;
	
    // Default no-argument constructor
    public Login() {
    }
    
	public Login(String emailid, String password, String userType, int accno) {
		super();
		this.emailid = emailid;
		this.password = password;
		this.userType = userType;
		this.accno = accno;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}

	@Override
	public String toString() {
		return "Login [emailid=" + emailid + ", password=" + password + ", userType=" + userType + ", accno=" + accno
				+ "]";
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	





}
