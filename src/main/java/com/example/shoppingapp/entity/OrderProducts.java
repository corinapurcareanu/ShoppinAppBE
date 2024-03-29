package com.example.shoppingapp.entity;

import jakarta.persistence.*;

@Entity
@Table
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Product product;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderProducts() {

    }

    public Product getProduct() {
        return product;
    }

    public OrderProducts( Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
