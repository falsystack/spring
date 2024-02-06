package jp.falsystack.userservice.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
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
}
