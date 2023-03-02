package com.example.shoppingapp.repository;

import com.example.shoppingapp.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
