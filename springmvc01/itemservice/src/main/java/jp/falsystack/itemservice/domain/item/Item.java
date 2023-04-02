package jp.falsystack.itemservice.domain.item;

import lombok.Data;

@Data // @Data는 위험하다. 주의가 필요하다.
public class Item {

  private Long id;
  private String itemName;
  private Integer price;
  private Integer quantity;

  public Item() {
  }

  public Item(String itemName, Integer price, Integer quantity) {
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }
}
