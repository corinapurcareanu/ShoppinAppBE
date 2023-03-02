package com.example.shoppingapp.service;

import com.example.shoppingapp.configuration.JwtRequestFilter;
import com.example.shoppingapp.entity.*;
import com.example.shoppingapp.repository.CartRepository;
import com.example.shoppingapp.repository.OrderDetailRepository;
import com.example.shoppingapp.repository.ProductRepository;
import com.example.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;


    public OrderDetail placeOrder(OrderInput orderInput) {
       List<Cart> cart = orderInput.getCart();

       if(cart.size() == 0) {
           return null;
       }

        String currentUser = JwtRequestFilter.CURRENT_USER;

       if(currentUser == null) {
           return null;
       }

        User user = userRepository.findByUserName(currentUser).get();

        OrderDetail orderDetail = new OrderDetail(
                orderInput.getOrderFullName(),
                orderInput.getOrderFullAddress(),
                orderInput.getOrderContactNumber(),
                orderInput.getOrderAlternateContactNumber(),
                ORDER_PLACED,
                (double) 0,
                new HashSet<>(),
                user
        );

        Double amount = Double.valueOf(0);

       for(Cart c : cart) {
           Product product = productRepository.findById(c.getProduct().getId()).get();

           amount += product.getProductDiscountedPrice() * c.getQuantity();

           orderDetail.addProduct(product);

           cartRepository.deleteById(c.getId());
       }

       orderDetail.setOrderAmount(amount);

        return orderDetailRepository.save(orderDetail);
    }
}
