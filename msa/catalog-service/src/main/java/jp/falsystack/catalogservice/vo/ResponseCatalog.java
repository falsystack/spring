package jp.falsystack.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jp.falsystack.catalogservice.entity.Catalog;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;

    private LocalDate createdAt;

    @Builder
    public ResponseCatalog(String productId, String productName, Integer unitPrice, Integer stock, LocalDate createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = createdAt;
    }

    public static ResponseCatalog fromCatalog(Catalog catalog) {
        return ResponseCatalog.builder()
                .productId(catalog.getProductId())
                .productName(catalog.getProductName())
                .unitPrice(catalog.getUnitPrice())
                .stock(catalog.getStock())
                .build();
    }
}
