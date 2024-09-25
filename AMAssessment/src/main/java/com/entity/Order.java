package com.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int oid;
private LocalDateTime orderdatatime;
@Column
private int qty;
private Integer pid;

}
