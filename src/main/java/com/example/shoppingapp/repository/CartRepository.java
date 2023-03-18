package com.example.shoppingapp.repository;

import com.example.shoppingapp.entity.Cart;
import com.example.shoppingapp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    public List<Cart> findByUser(User user);
}
