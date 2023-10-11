package jp.falsystack.cafekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;

import jp.falsystack.cafekiosk.unit.beverage.Americano;
import jp.falsystack.cafekiosk.unit.beverage.Latte;
import org.junit.jupiter.api.Test;

class CafeKioskTest {

  @Test
  void add_manual() {
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();
    cafeKiosk.add(new Americano());
    // expected

    System.out.println(">> 담김 음료의 수 : " + cafeKiosk.getBeverages().size());
    System.out.println(">> 담김 음료 : " + cafeKiosk.getBeverages().get(0).getName());

  }

  @Test
  void add() {
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();
    // when
    cafeKiosk.add(new Americano());
    // expected
    assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
    assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
  }

  @Test
  void remove() {
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    cafeKiosk.add(americano);
    // when
    cafeKiosk.remove(americano);
    // then
    assertThat(cafeKiosk.getBeverages().size()).isEqualTo(0);
  }

  @Test
  void clear() {
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    Latte latte = new Latte();
    cafeKiosk.add(americano);
    cafeKiosk.add(latte);
    // when
    cafeKiosk.clear();
    // then
    assertThat(cafeKiosk.getBeverages().size()).isEqualTo(0);
  }

}