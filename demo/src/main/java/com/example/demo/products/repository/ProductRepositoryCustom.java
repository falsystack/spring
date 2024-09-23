package com.example.demo.products.repository;

import com.example.demo.products.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> getProducts(int page);
}
