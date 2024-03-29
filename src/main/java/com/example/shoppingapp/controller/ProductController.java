package com.example.shoppingapp.controller;

import com.example.shoppingapp.entity.ImageModel;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('admin')")
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] file) {

        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for(MultipartFile file: multipartFiles) {
            ImageModel imageModel= new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );

            imageModels.add(imageModel);
        }

        return imageModels;
    }

    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0")int pageNumber,
                                        @RequestParam(defaultValue = "") String searchKey) {
        return productService.getAllProducts(pageNumber, searchKey);
    }

    @GetMapping({"/getAllProductsByType"})
    public List<Product> getAllProductsByType(@RequestParam(defaultValue = "0")int pageNumber,
                                        @RequestParam(defaultValue = "") String ... types) {
        return productService.getAllProductsByType(pageNumber, types);
    }

    @GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById(@PathVariable("productId") Long productId) {
        return productService.getProductDetailsById(productId);
    }

    @DeleteMapping({"/deleteProductDetails/{productId}"})
    @PreAuthorize("hasRole('admin')")
    public void deleteProductDetails(@PathVariable("productId") Long productId) {
        productService.deleteProductDetails(productId);
    }

    @GetMapping({"/getProductDetails"})
    public List<Product> getProductDetails() {
        return productService.getProductDetails();
    }
}
