package com.example.shoppingapp.controller;

import com.example.shoppingapp.entity.OrderDetail;
import com.example.shoppingapp.entity.OrderInput;
import com.example.shoppingapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping({"/placeOrder"})
    public OrderDetail placeOrder(
            @RequestBody OrderInput orderInput){
        return orderDetailService.placeOrder(orderInput);

    }
}
