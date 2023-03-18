package com.example.shoppingapp.entity;


import jakarta.persistence.*;
import java.util.Set;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable=false)
    private String orderFullName;
    @Column(nullable=false)
    private String orderFullAddress;
    @Column(nullable=false)
    private String orderContactNumber;
    private String orderAlternateContactNumber;
    @Column(nullable=false)
    private String orderStatus;
    @Column(nullable=false)
    private Double orderAmount;
    @Column(nullable=false)
    private String orderDatePlaced;

    private String deliveryMethod;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_PRODUCT",
            joinColumns = {
                    @JoinColumn(name="PRODUCT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ORDER_ID")
            })
    private Set<OrderProducts> products;
    @OneToOne
    private User user;


    public OrderDetail(String orderFullName, String orderFullAddress, String orderContactNumber, String orderAlternateContactNumber, String orderStatus,
                       Double orderAmount, String orderDatePlaced, String deliveryMethod, Set<OrderProducts> products, User user) {
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternateContactNumber = orderAlternateContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.orderDatePlaced = orderDatePlaced;
        this.deliveryMethod = deliveryMethod;
        this.products = products;
        this.user = user;
    }

    public OrderDetail() {

    }

    public String getOrderDatePlaced() {
        return orderDatePlaced;
    }

    public void setOrderDatePlaced(String orderDatePlaced) {
        this.orderDatePlaced = orderDatePlaced;
    }

    public void setProducts(Set<OrderProducts> products) {
        this.products = products;
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

    public Set<OrderProducts> getProducts() {
        return products;
    }

    public void setProduct(Set<Product> product) {
        this.products = products;
    }

    public void addProduct(OrderProducts product){
        this.products.add(product);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryCost(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
