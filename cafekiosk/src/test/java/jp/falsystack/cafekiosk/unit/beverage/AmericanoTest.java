package jp.falsystack.cafekiosk.unit.beverage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AmericanoTest {

  @Test
  void getPrice() {
    // given
    Americano americano = new Americano();

    // expected
    assertThat(americano.getPrice()).isEqualTo(4000);
  }

  @Test
  void getName() {
    // given
    Americano americano = new Americano();

    // expected
    assertThat(americano.getName()).isEqualTo("아메리카노");
  }
}