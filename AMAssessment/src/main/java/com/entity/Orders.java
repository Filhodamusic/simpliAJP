package com.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int oid;
private LocalDateTime orderdatatime;
private int qty;
private int pid;

public int getOid() {
	return oid;
}
public void setOid(int oid) {
	this.oid = oid;
}
public LocalDateTime getOrderdatatime() {
	return orderdatatime;
}
public void setOrderdatatime(LocalDateTime orderdatatime) {
	this.orderdatatime = orderdatatime;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}

}
