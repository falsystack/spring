package jp.falsystack.core.discount;

import jp.falsystack.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
