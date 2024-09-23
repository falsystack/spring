package com.example.demo.products.controller;

import com.example.demo.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/")
    public String demo() {
        var products = productRepository.getProducts(10);
        return products.toString();
    }

}
