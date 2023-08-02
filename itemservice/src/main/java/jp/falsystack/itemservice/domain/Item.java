package jp.falsystack.itemservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    @Builder
    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
