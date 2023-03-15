package com.example.shoppingapp.controller;

import com.example.shoppingapp.entity.Cart;
import com.example.shoppingapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"/updatedQuantityInCart/{productId}/{increase}"})
    @PreAuthorize("hasRole('user')")
    public Cart updatedQuantityInCart(@PathVariable(name="productId") Long productId, @PathVariable(name="increase") Boolean increase) {
       System.out.println("updated get");
        return cartService.updatedQuantityInCart(productId, increase);
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
