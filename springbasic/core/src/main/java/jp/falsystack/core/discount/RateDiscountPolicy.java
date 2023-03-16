package jp.falsystack.core.discount;

import jp.falsystack.core.annotation.MainDiscountPolicy;
import jp.falsystack.core.member.Grade;
import jp.falsystack.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

  private final int DISCOUNT_PERCENT = 10;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return price * DISCOUNT_PERCENT / 100;
    }
    return 0;
  }
}
