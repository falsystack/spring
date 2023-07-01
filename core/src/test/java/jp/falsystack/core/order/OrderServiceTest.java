package jp.falsystack.core.order;

import jp.falsystack.core.AppConfig;
import jp.falsystack.core.member.Grade;
import jp.falsystack.core.member.Member;
import jp.falsystack.core.member.MemberService;
import jp.falsystack.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        memberService = ac.getBean(MemberService.class);
        orderService = ac.getBean(OrderService.class);
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