package com.example.shoppingapp.entity;

import java.util.List;

public class OrderInput {

    private String orderFullName;
    private String orderFullAddress;
    private String orderContactNumber;
    private String orderAlternateContactNumber;

    private Double deliveryCost;
    private List<Cart> cart;

    public String getOrderFullName() {
        return orderFullName;
    }

    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }

    public String getOrderFullAddress() {
        return orderFullAddress;
    }

    public void setOrderFullAddress(String orderFullAddress) {
        this.orderFullAddress = orderFullAddress;
    }

    public String getOrderContactNumber() {
        return orderContactNumber;
    }

    public void setOrderContactNumber(String orderContactNumber) {
        this.orderContactNumber = orderContactNumber;
    }

    public String getOrderAlternateContactNumber() {
        return orderAlternateContactNumber;
    }

    public void setOrderAlternateContactNumber(String orderAlternateContactNumber) {
        this.orderAlternateContactNumber = orderAlternateContactNumber;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setOrderProductQuantityList(List<Cart> cart) {
        this.cart = cart;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }
}
