package com.example.shoppingapp.mapper;

import com.example.shoppingapp.dto.CartDto;
import com.example.shoppingapp.dto.UserDto;
import com.example.shoppingapp.entity.Cart;
import com.example.shoppingapp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getUserName(), user.getUserFirstName(), user.getUserLastName(),
                user.getUserEmail(), user.getUserPassword(), user.getUserPhoneNumber());
    }
}
