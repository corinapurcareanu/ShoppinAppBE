package com.example.shoppingapp.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Product product;
    @OneToOne
    private User user;

    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart() {

    }

    public Product getProduct() {
        return product;
    }

    public Cart( Product product, User user, Integer quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
