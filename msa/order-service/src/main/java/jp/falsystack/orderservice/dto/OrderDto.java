package jp.falsystack.orderservice.dto;

import jp.falsystack.orderservice.entity.Order;
import lombok.Data;

@Data
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    public Integer getTotalPrice() {
        return qty * unitPrice;
    }

    public Order toEntity() {
        return Order.builder()
                .productId(productId)
                .qty(qty)
                .unitPrice(unitPrice)
                .totalPrice(getTotalPrice())
                .orderId(orderId)
                .userId(userId)
                .build();
    }
}
