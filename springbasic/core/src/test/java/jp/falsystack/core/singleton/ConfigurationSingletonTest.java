package jp.falsystack.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import jp.falsystack.core.AppConfig;
import jp.falsystack.core.member.MemberRepository;
import jp.falsystack.core.member.MemberServiceImpl;
import jp.falsystack.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  @DisplayName("")
  void configurationTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();

    System.out.println("memberService -> memberRepository1 = " + memberRepository1);
    System.out.println("orderService -> memberRepository2 = " + memberRepository2);

    System.out.println("memoryMemberRepository = " + memberRepository);

    assertThat(memberRepository1).isSameAs(memberRepository2);
  }

  @Test
  @DisplayName("")
  void configurationDeep() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class);

    AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println("bean.getClass() = " + bean.getClass());
  }
}
