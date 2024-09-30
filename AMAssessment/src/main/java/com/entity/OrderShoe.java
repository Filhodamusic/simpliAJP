package com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_shoe")
public class OrderShoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "oid", nullable = false)
    private Orders order; // The order this item belongs to

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private Shoe shoe; // The product in the order

    private int quantity; // Quantity of the product in the order

    // Getters and setters...
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Orders getOrder() {
        return order;
    }
    public void setOrder(Orders order) {
        this.order = order;
    }
    public Shoe getShoe() {
        return shoe;
    }
    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
