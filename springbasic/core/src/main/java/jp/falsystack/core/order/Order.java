package jp.falsystack.core.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Order {

  private Long memberId;
  private String itemName;
  private int itemPrice;
  private int discountPrice;

  // business logic
  public int calculatePrice() {
    return itemPrice - discountPrice;
  }

}
