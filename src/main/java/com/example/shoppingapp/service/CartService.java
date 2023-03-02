package com.example.shoppingapp.service;

import com.example.shoppingapp.configuration.JwtRequestFilter;
import com.example.shoppingapp.entity.Cart;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.repository.CartRepository;
import com.example.shoppingapp.repository.ProductRepository;
import com.example.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart addToCart(Long productId) {
       Product product = productRepository.findById(productId).get();
       String userName = JwtRequestFilter.CURRENT_USER;
        User user = null;

       if(userName != null) {
           user = userRepository.findByUserName(userName).get();
       }
       List<Cart> cartList = cartRepository.findByUser(user);
       List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getId() == productId).collect(Collectors.toList());
       if(filteredList.size() > 0) {
           Cart cart = filteredList.get(0);
           cart.setQuantity(cart.getQuantity() + 1);
           cartRepository.deleteById(cart.getId());
           return cartRepository.save(cart);
       }

       if(product != null && user != null) {
           Cart cart = new Cart(
                   product,
                   user,
                   1
           );
           return cartRepository.save(cart);
       }
        return null;
    }

    public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;

        if(username != null) {
            User user = userRepository.findByUserName(username).get();
            return cartRepository.findByUser(user);
        }

        return null;
    }

    public void deleteCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
