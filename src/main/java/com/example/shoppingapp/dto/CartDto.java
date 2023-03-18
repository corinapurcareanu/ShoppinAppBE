package com.example.shoppingapp.dto;

import com.example.shoppingapp.entity.Product;

public class CartDto {
    private Long id;
    private Product product;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CartDto() {

    }

    public Product getProduct() {
        return product;
    }

    public CartDto(Long id, Product product, Integer quantity) {
        this.id = id;
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
