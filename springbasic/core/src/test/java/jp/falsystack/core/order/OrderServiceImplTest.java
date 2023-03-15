package jp.falsystack.core.order;

import jp.falsystack.core.discount.FixDiscountPolicy;
import jp.falsystack.core.member.Grade;
import jp.falsystack.core.member.Member;
import jp.falsystack.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {


  @Test
  @DisplayName("")
  void createOrder() {
    // given
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    memberRepository.save(new Member(1L, "kakao", Grade.BASIC));

    OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,
        new FixDiscountPolicy());
    orderService.createOrder(1L, "itemA", 10000);

    // when(when 절에는 test 의 목적이 와야한다.)

    // then
  }
}