package jp.co.falsystack.ssiacommon.services;

import jp.co.falsystack.ssiacommon.entities.Product;
import jp.co.falsystack.ssiacommon.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
