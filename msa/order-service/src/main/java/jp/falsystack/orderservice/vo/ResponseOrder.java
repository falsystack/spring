package jp.falsystack.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jp.falsystack.orderservice.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;

    private String orderId;

    @Builder
    public ResponseOrder(String productId, Integer qty, Integer unitPrice, Integer totalPrice, LocalDate createdAt, String orderId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.orderId = orderId;
    }

    public static ResponseOrder fromEntity(Order order) {
        return ResponseOrder.builder()
                .productId(order.getProductId())
                .qty(order.getQty())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .orderId(order.getOrderId())
                .build();
    }
}
