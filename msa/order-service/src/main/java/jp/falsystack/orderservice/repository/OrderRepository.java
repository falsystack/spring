package jp.falsystack.orderservice.repository;

import jp.falsystack.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(String orderId);

    List<Order> findByUserId(String userId);
}
