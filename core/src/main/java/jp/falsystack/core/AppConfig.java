package jp.falsystack.core;

import jp.falsystack.core.discount.DiscountPolicy;
import jp.falsystack.core.discount.FixDiscountPolicy;
import jp.falsystack.core.discount.RateDiscountPolicy;
import jp.falsystack.core.member.MemberRepository;
import jp.falsystack.core.member.MemberService;
import jp.falsystack.core.member.MemberServiceImpl;
import jp.falsystack.core.member.MemoryMemberRepository;
import jp.falsystack.core.order.OrderService;
import jp.falsystack.core.order.OrderServiceImpl;

public class AppConfig {

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
