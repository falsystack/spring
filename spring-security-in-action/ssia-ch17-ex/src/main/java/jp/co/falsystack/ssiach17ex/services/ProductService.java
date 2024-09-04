package jp.co.falsystack.ssiach17ex.services;

import jp.co.falsystack.ssiach17ex.entities.Product;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        // 상품을 판매하고 판매된 상품 리스트를 반환
        return products;
    }

    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> findProducts() {
        var records = new ArrayList<Product>();
        records.add(new Product("beer", "nikolai"));
        records.add(new Product("candy", "nikolai"));
        records.add(new Product("chocolate", "julien"));

        return records;
    }
}
