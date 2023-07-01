package jp.falsystack.core;

import jp.falsystack.core.discount.FixDiscountPolicy;
import jp.falsystack.core.member.MemberService;
import jp.falsystack.core.member.MemberServiceImpl;
import jp.falsystack.core.member.MemoryMemberRepository;
import jp.falsystack.core.order.OrderService;
import jp.falsystack.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
