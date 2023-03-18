package com.example.shoppingapp.service;

import com.example.shoppingapp.dto.UserDto;
import com.example.shoppingapp.entity.Role;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.exceptions.DuplicateUserException;
import com.example.shoppingapp.mapper.UserMapper;
import com.example.shoppingapp.repository.RoleRepository;
import com.example.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserDto registerNewUser(UserDto userDto) throws DuplicateUserException {

        Role role = roleRepository.findByRoleName("user").get();

        String encodedPassword = getEncodedPassword(userDto.getUserPassword());



        if(userRepository.findByUserName(userDto.getUserFirstName()).isPresent()) {
            throw new DuplicateUserException();
        }

        User user = new User(userDto.getUserName(), userDto.getUserFirstName(), userDto.getUserLastName(), userDto.getUserEmail(),
                encodedPassword, userDto.getUserPhoneNumber(), role);
        return userMapper.toDto(userRepository.save(user));
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
