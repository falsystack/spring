package jp.falsystack.core;

import jp.falsystack.core.discount.DiscountPolicy;
import jp.falsystack.core.discount.RateDiscountPolicy;
import jp.falsystack.core.member.MemberRepository;
import jp.falsystack.core.member.MemberService;
import jp.falsystack.core.member.MemberServiceImpl;
import jp.falsystack.core.member.MemoryMemberRepository;
import jp.falsystack.core.order.OrderService;
import jp.falsystack.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public MemberService memberService() {
    System.out.println("AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    System.out.println("AppConfig.orderService");
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
