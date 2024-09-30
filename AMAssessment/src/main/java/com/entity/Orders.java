package com.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int oid;
private LocalDateTime orderdatatime;
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderShoe> orderShoe;

@ManyToOne
@JoinColumn(name = "emailid", nullable = false)
private Login login;

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
public List<OrderShoe> getOrderShoe() {
    return orderShoe;
}
public void setOrderShoe(List<OrderShoe> orderShoe) {
    this.orderShoe = orderShoe;
}
public Login getLogin() {
	return login;
}
public void setLogin(Login login) {
	this.login = login;
}

}
