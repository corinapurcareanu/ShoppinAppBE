package com.example.shoppingapp.repository;

import com.example.shoppingapp.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByRoleName(String roleName);
}
