package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int accno;
private String name;
private float amount;
@Column(unique = true)
private String emailid;
@OneToOne
@JoinColumn(name = "accno")


public int getAccno() {
	return accno;
}
public void setAccno(int accno) {
	this.accno = accno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}

//Default no-argument constructor
public Account() {
}
public Account(String name, float amount, String emailid) {
	super();
	this.name = name;
	this.amount = amount;
	this.emailid = emailid;
}


}

