package com.entity;

import java.util.List;

public class OrderShoeTransfer {
    private List<OrderShoeItem> orderShoes; // List of shoe items to order

    // Getters and Setters
    public List<OrderShoeItem> getOrderShoes() {
        return orderShoes;
    }

    public void setOrderShoes(List<OrderShoeItem> orderShoes) {
        this.orderShoes = orderShoes;
    }

    // Inner class to represent each shoe item with pid and quantity
    public static class OrderShoeItem {
        private int pid; // Product ID
        private int quantity; // Quantity of the shoe

        // Getters and Setters
        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
