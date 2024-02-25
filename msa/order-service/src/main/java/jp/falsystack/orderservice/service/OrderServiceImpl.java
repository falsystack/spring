package jp.falsystack.orderservice.service;

import jp.falsystack.orderservice.dto.OrderDto;
import jp.falsystack.orderservice.entity.Order;
import jp.falsystack.orderservice.repository.OrderRepository;
import jp.falsystack.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public ResponseOrder createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());

        Order savedOrder = orderRepository.save(orderDto.toEntity());
        return ResponseOrder.builder()
                .productId(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .createdAt(savedOrder.getCreatedAt())
                .orderId(orderDto.getOrderId())
                .build();
    }

    @Override
    public ResponseOrder getOrderByOrderId(String orderId) {
        return ResponseOrder.fromEntity(orderRepository.findByOrderId(orderId).orElseThrow(() -> new IllegalArgumentException("Not Found")));
    }

    @Override
    public List<ResponseOrder> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(ResponseOrder::fromEntity).toList();
    }
}
