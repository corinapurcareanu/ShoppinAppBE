package com.example.shoppingapp.service;

import com.example.shoppingapp.configuration.JwtRequestFilter;
import com.example.shoppingapp.dto.CartDto;
import com.example.shoppingapp.entity.Cart;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.exceptions.UserNotLoggedInException;
import com.example.shoppingapp.mapper.CartMapper;
import com.example.shoppingapp.repository.CartRepository;
import com.example.shoppingapp.repository.ProductRepository;
import com.example.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartMapper cartMapper;

    public CartDto addToCart(Long productId) throws UserNotLoggedInException {
       Product product = productRepository.findById(productId).get();
       String userName = JwtRequestFilter.CURRENT_USER;
        User user = null;

       if(userName != null) {
           user = userRepository.findByUserName(userName).get();
       }
       List<Cart> cartList = cartRepository.findByUser(user);

       List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getId() == productId).collect(Collectors.toList());

       if(filteredList.size() > 0) {
           Cart cart = filteredList.get(0);
           cart.setQuantity(cart.getQuantity() + 1);
           cartRepository.deleteById(cart.getId());
           return cartMapper.toDto(cartRepository.save(cart));
       }

       if(product != null && user != null) {
           Cart cart = new Cart(
                   product,
                   user,
                   1
           );
           return cartMapper.toDto(cartRepository.save(cart));
       }
        throw new UserNotLoggedInException();
    }

    public List<CartDto> getCartDetails() throws UserNotLoggedInException {
        String username = JwtRequestFilter.CURRENT_USER;

        if(username != null) {
            User user = userRepository.findByUserName(username).get();
            return cartRepository.findByUser(user).stream().map(cartMapper::toDto).collect(Collectors.toList());
        }

        throw new UserNotLoggedInException();
    }

    public void deleteCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public CartDto updatedQuantityInCart(Long productId, Boolean increase) throws UserNotLoggedInException {
        Product product = productRepository.findById(productId).get();
        String userName = JwtRequestFilter.CURRENT_USER;
        User user = null;

        if (userName != null) {
            user = userRepository.findByUserName(userName).get();
        }
        List<Cart> cartList = cartRepository.findByUser(user);

        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getId() == productId).collect(Collectors.toList());
        if (filteredList.size() > 0) {
            Cart cart = filteredList.get(0);
            if(increase) {
                cart.setQuantity(cart.getQuantity() + 1);
            } else {
                cart.setQuantity(cart.getQuantity() - 1);
            }
            return cartMapper.toDto(cartRepository.save(cart));
        }

        if (product != null && user != null) {
            Cart cart = new Cart(
                    product,
                    user,
                    1
            );
            return cartMapper.toDto(cartRepository.save(cart));
        }
        throw new UserNotLoggedInException();
    }
}
