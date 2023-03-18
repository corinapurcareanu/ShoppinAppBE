package com.example.shoppingapp.controller;

import com.example.shoppingapp.dto.CartDto;
import com.example.shoppingapp.exceptions.UserNotLoggedInException;
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
    public CartDto addToCart(@PathVariable(name="productId") Long productId) {
        try {
            return cartService.addToCart(productId);
        } catch (UserNotLoggedInException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping({"/updatedQuantityInCart/{productId}/{increase}"})
    @PreAuthorize("hasRole('user')")
    public CartDto updatedQuantityInCart(@PathVariable(name="productId") Long productId, @PathVariable(name="increase") Boolean increase) {
       try {
           return cartService.updatedQuantityInCart(productId, increase);
       } catch (UserNotLoggedInException e) {
           System.out.println(e.getMessage());
           return null;
       }
    }

    @GetMapping({"/getCartDetails"})
    @PreAuthorize("hasRole('user')")
    public List<CartDto> getCartDetails() throws UserNotLoggedInException {
        try {
            return cartService.getCartDetails();
        } catch (UserNotLoggedInException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping({"/deleteCartItem/{cartId}"})
    @PreAuthorize("hasRole('user')")
    public void deleteCartItem(@PathVariable(name = "cartId") Long cartId) {
        cartService.deleteCartItem(cartId);
    }
}
