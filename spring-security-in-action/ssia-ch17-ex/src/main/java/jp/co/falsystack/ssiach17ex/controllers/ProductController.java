package jp.co.falsystack.ssiach17ex.controllers;

import jp.co.falsystack.ssiach17ex.entities.Product;
import jp.co.falsystack.ssiach17ex.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/sell")
    public List<Product> sellProducts() {
        var records = new ArrayList<Product>();
        records.add(new Product("beer", "nikolai"));
        records.add(new Product("candy", "nikolai"));
        records.add(new Product("chocolate", "julien"));


        return productService.sellProducts(records);
    }

    @GetMapping("/find")
    public List<Product> findProducts() {
        return productService.findProducts();
    }
}
