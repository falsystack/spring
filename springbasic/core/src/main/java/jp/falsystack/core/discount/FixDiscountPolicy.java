package jp.falsystack.core.discount;

import jp.falsystack.core.member.Grade;
import jp.falsystack.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

  private final int DISCOUNT_FIX_AMOUNT = 1000;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return DISCOUNT_FIX_AMOUNT;
    }
    return 0;
  }
}
