package com.example.demo.products.repository;

import com.example.demo.products.entity.Product;
import com.example.demo.products.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> getProducts(int page) {
        return jpaQueryFactory.selectFrom(QProduct.product)
                .limit(page)
                .fetch();
    }
}
