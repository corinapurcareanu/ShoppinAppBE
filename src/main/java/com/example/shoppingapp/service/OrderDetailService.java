package com.example.shoppingapp.service;

import com.example.shoppingapp.configuration.JwtRequestFilter;
import com.example.shoppingapp.entity.*;
import com.example.shoppingapp.exceptions.EmptyCartException;
import com.example.shoppingapp.exceptions.UserNotLoggedInException;
import com.example.shoppingapp.repository.CartRepository;
import com.example.shoppingapp.repository.OrderDetailRepository;
import com.example.shoppingapp.repository.ProductRepository;
import com.example.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public OrderDetail placeOrder(OrderInput orderInput) throws EmptyCartException, UserNotLoggedInException {
       List<Cart> cart = orderInput.getCart();

       if(cart.size() == 0) {
           throw new EmptyCartException();
       }

        String currentUser = JwtRequestFilter.CURRENT_USER;

       if(currentUser == null) {
           throw new UserNotLoggedInException();
       }

        User user = userRepository.findByUserName(currentUser).get();

       String deliveryMethod = "";

        if (orderInput.getDeliveryCost() == 0) {
            deliveryMethod = "Pickup from office";
        } else if (orderInput.getDeliveryCost() == 2) {
            deliveryMethod = "Postal service";
        } else if (orderInput.getDeliveryCost() == 4) {
            deliveryMethod = "Courier service";
        }
        OrderDetail orderDetail = new OrderDetail(
                orderInput.getOrderFullName(),
                orderInput.getOrderFullAddress(),
                orderInput.getOrderContactNumber(),
                orderInput.getOrderAlternateContactNumber(),
                ORDER_PLACED,
                (double) 0,
                LocalDate.now().toString(),
                deliveryMethod,
                new HashSet<>(),
                user
        );

        Double amount = Double.valueOf(0);

       for(Cart c : cart) {
           Product product = productRepository.findById(c.getProduct().getId()).get();

           if(product.getProductDiscountedPrice() != null && product.getProductDiscountedPrice() != 0) {
               amount += product.getProductDiscountedPrice() * c.getQuantity();
           } else {
               amount += product.getProductActualPrice() * c.getQuantity();
               product.setProductStock(product.getProductStock() - c.getQuantity());
               productRepository.save(product);
           }
           orderDetail.addProduct(new OrderProducts(
                   c.getProduct(),
                   c.getQuantity()
           ));

           cartRepository.deleteById(c.getId());
       }

       orderDetail.setOrderAmount(amount);

        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> getOrderDetails() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userRepository.findByUserName(currentUser).get();

        return orderDetailRepository.findByUser(user);
    }

    public List<OrderDetail> getAllOrderDetails(String status){
        List<OrderDetail> orderDetails = new ArrayList<>();

        if(status.equals("All")){
            orderDetailRepository.findAll().forEach(
                    x -> orderDetails.add(x)
            );
        } else {
            orderDetailRepository.findByOrderStatus(status).forEach(
                    x -> orderDetails.add(x)
            );
        }

        return orderDetails;
    }

    public void markOrderAdDelivered(Long orderId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).get();
        if(orderDetail != null) {
            orderDetail.setOrderStatus("Delivered");
            orderDetailRepository.save(orderDetail);
        }
    }
}
