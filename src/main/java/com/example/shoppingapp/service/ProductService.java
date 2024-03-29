package com.example.shoppingapp.service;

import com.example.shoppingapp.configuration.JwtRequestFilter;
import com.example.shoppingapp.entity.Cart;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.repository.CartRepository;
import com.example.shoppingapp.repository.ProductRepository;
import com.example.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(int pageNumber,  String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 12);
        if(searchKey.equals("")) {
            return (List<Product>) productRepository.findAll(pageable);
        } else {
            return (List<Product>) productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }
    }

    public Product getProductDetailsById(Long productId) {
        return productRepository.findById(productId).get();
    }

    public void deleteProductDetails(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getProductDetails() {
        String userName = JwtRequestFilter.CURRENT_USER;
        User user = userRepository.findByUserName(userName).get();
        List<Cart> carts = cartRepository.findByUser(user);
        return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
    }

    public List<Product> getAllProductsByType(int pageNumber, String... types) {
        Pageable pageable = PageRequest.of(pageNumber, 12);
        List<Product> products = new ArrayList<>();

        List<String> typeList = Arrays.stream(types.clone()).toList();

        typeList.forEach(type ->
                        products.addAll(productRepository.findByType(type))
            );
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());
        return products.subList(start, end);
    }
}
