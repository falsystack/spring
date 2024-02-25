package jp.falsystack.orderservice.service;

import jp.falsystack.orderservice.dto.OrderDto;
import jp.falsystack.orderservice.vo.ResponseOrder;

import java.util.List;

public interface OrderService {
    ResponseOrder createOrder(OrderDto orderDetails);
    ResponseOrder getOrderByOrderId(String orderId);
    List<ResponseOrder> getOrdersByUserId(String userId);
}
