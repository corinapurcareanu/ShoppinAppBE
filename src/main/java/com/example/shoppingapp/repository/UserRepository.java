package com.example.shoppingapp.repository;

import com.example.shoppingapp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
   Optional<User> findByUserName(String s);
}
