package com.example.shoppingapp.mapper;

import com.example.shoppingapp.dto.CartDto;
import com.example.shoppingapp.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartDto toDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getProduct(),cart.getQuantity());
    }
}
