package com.example.shoppingapp.controller;

import com.example.shoppingapp.entity.Cart;
import com.example.shoppingapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping({"/addToCart/{productId}"})
    @PreAuthorize("hasRole('user')")
    public Cart addToCart(@PathVariable(name="productId") Long productId) {
        return cartService.addToCart(productId);
    }

    @GetMapping({"/getCartDetails"})
    @PreAuthorize("hasRole('user')")
    public List<Cart> getCartDetails() {
        return cartService.getCartDetails();
    }

    @DeleteMapping({"/deleteCartItem/{cartId}"})
    @PreAuthorize("hasRole('user')")
    public void deleteCartItem(@PathVariable(name = "cartId") Long cartId) {
        cartService.deleteCartItem(cartId);
    }
}
