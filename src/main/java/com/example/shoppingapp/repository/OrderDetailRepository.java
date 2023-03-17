package com.example.shoppingapp.repository;

import com.example.shoppingapp.entity.OrderDetail;
import com.example.shoppingapp.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
    public List<OrderDetail> findByUser(User user);

    public List<OrderDetail> findByOrderStatus(String status);
}
