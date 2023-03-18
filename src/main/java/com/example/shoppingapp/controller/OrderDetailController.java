package com.example.shoppingapp.controller;

import com.example.shoppingapp.entity.OrderDetail;
import com.example.shoppingapp.entity.OrderInput;
import com.example.shoppingapp.exceptions.EmptyCartException;
import com.example.shoppingapp.exceptions.UserNotLoggedInException;
import com.example.shoppingapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping({"/placeOrder"})
    @PreAuthorize("hasRole('user')")
    public OrderDetail placeOrder(
            @RequestBody OrderInput orderInput){
        try {
            return orderDetailService.placeOrder(orderInput);
        } catch (EmptyCartException e) {
            System.out.println(e.getMessage());
            return null;
        }  catch (UserNotLoggedInException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping({"/getOrderDetails"})
    @PreAuthorize("hasRole('user')")
    public List<OrderDetail> getOrderDetails() {
        return orderDetailService.getOrderDetails();
    }

    @GetMapping({"/getAllOrderDetails/{status}"})
    @PreAuthorize("hasRole('admin')")
    public List<OrderDetail> gettAllOrderDetails(@PathVariable(name="status") String status) {
        return orderDetailService.getAllOrderDetails(status);
    }
    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    @PreAuthorize("hasRole('admin')")
    public void markOrderAsDelivered(@PathVariable(name = "orderId") Long orderId) {
        orderDetailService.markOrderAdDelivered(orderId);
    }
}
