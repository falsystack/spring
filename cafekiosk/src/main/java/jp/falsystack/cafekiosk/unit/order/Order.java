package jp.falsystack.cafekiosk.unit.order;

import java.time.LocalDateTime;
import java.util.List;
import jp.falsystack.cafekiosk.unit.beverage.Beverage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Order {

  private final LocalDateTime orderTime;
  private final List<Beverage> beverages;

  @Builder
  public Order(LocalDateTime orderTime, List<Beverage> beverages) {
    this.orderTime = orderTime;
    this.beverages = beverages;
  }
}
