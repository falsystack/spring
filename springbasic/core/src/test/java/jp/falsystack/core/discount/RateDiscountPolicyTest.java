package jp.falsystack.core.discount;

import static org.assertj.core.api.Assertions.assertThat;

import jp.falsystack.core.member.Grade;
import jp.falsystack.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

  DiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP는 10%할인이 적용되어야 한다.")
  void vipSale() {
    // given
    Member member = new Member(1L, "memberVIP", Grade.VIP);

    // when(when 절에는 test 의 목적이 와야한다.)
    int discount = discountPolicy.discount(member, 20000);

    //then
    assertThat(discount).isEqualTo(2000);
  }

  @Test
  @DisplayName("VIP가 아니면 10% 할인이 적용되어서는 안된다.")
  void noVipNoSale() {
    // given
    Member member = new Member(2L, "memberBASIC", Grade.BASIC);

    // when(when 절에는 test 의 목적이 와야한다.)
    int discount = discountPolicy.discount(member, 20000);

    //then
    assertThat(discount).isEqualTo(0);
  }
}