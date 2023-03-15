package jp.falsystack.core.autowired;

import java.util.Optional;
import jp.falsystack.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {


  @Test
  @DisplayName("")
  void AutowiredOption() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(
        TestBean.class);

  }


  static class TestBean {

    // 스프링 컨테이너에 의해 관리되지 않는 Member객체를 자동주입하려고 함.
    // required = false 자동주입할 대상이 없으면 메서드 호출 자체가 안된다.
    @Autowired(required = false)
    public void setNoBean1(Member member) {
      System.out.println("member1 = " + member);
    }

    // 스프링 컨테이너에 의해 관리되지 않는 Member객체를 자동주입하려고 함.
    // 호출은 되지만 null로 들어온다.
    @Autowired
    public void setNoBean2(@Nullable Member member) {
      System.out.println("member2 = " + member);
    }

    // 스프링 빈이 없을경우 Optional.empty가 돌아온다.
    @Autowired
    public void setNoBean3(Optional<Member> member) {
      System.out.println("member3 = " + member);
    }
  }
}
