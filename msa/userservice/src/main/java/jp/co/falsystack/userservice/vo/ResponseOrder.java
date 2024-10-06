package jp.co.falsystack.userservice.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;

    private String orderId;
}
