package jp.falsystack.core.order;

import jp.falsystack.core.AppConfig;
import jp.falsystack.core.member.Grade;
import jp.falsystack.core.member.Member;
import jp.falsystack.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

  MemberService memberService;
  OrderService orderService;

  @BeforeEach
  void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }

  @Test
  @DisplayName("주문생성")
  void createOrder() {
    // given
    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    // when(when 절에는 test 의 목적이 와야한다.)
    Order order = orderService.createOrder(memberId, "itemA", 10000);

    // then
    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }

}