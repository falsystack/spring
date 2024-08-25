package jp.co.falsystack.ssiacommon.repositories;

import jp.co.falsystack.ssiacommon.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
