package jp.falsystack.core.order;

import jp.falsystack.core.AppConfig;
import jp.falsystack.core.member.Grade;
import jp.falsystack.core.member.Member;
import jp.falsystack.core.member.MemberService;
import jp.falsystack.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}