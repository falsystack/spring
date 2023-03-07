package jp.falsystack.core.xml;

import static org.assertj.core.api.Assertions.assertThat;

import jp.falsystack.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

  @Test
  @DisplayName("")
  void xmlAppContext() {
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext(
        "appConfig.xml");

    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
