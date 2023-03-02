package com.example.shoppingapp.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String orderFullName;
    private String orderFullAddress;
    private String orderContactNumber;
    private String orderAlternateContactNumber;
    private String orderStatus;
    private Double orderAmount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_PRODUCT",
            joinColumns = {
                    @JoinColumn(name="PRODUCT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ORDER_ID")
            })
    private Set<Product> products;
    @OneToOne
    private User user;


    public OrderDetail(String orderFullName, String orderFullAddress, String orderContactNumber, String orderAlternateContactNumber, String orderStatus, Double orderAmount, Set<Product> products, User user) {
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternateContactNumber = orderAlternateContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.products = products;
        this.user = user;
    }

    public OrderDetail() {

    }

    public String getOrderFullName() {
        return orderFullName;
    }

    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }

    public String getOrderFullAddress() {
        return orderFullAddress;
    }

    public void setOrderFullAddress(String orderFullOrder) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProduct(Set<Product> product) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
