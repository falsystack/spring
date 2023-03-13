package jp.falsystack.core.scan;

import jp.falsystack.core.AutoAppConfig;
import jp.falsystack.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

  @Test
  @DisplayName("")
  void basicScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AutoAppConfig.class);
    MemberService memberService = ac.getBean(MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
  }

}
