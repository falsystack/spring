package jp.falsystack.cafekiosk.unit;

import jp.falsystack.cafekiosk.unit.beverage.Americano;
import jp.falsystack.cafekiosk.unit.beverage.Latte;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CafeKioskRunner {

  public static void main(String[] args) {
    CafeKiosk kiosk = new CafeKiosk();
    kiosk.add(new Americano());
    log.info(" =>> 아메리카노 추가");

    kiosk.add(new Latte());
    log.info(" =>> 라떼 추가");

    int totalPrice = kiosk.calculateTotalPrice();
    log.info("총 주문가격 = {}", totalPrice);
  }

}
