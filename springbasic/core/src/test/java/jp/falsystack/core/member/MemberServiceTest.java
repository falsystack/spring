package jp.falsystack.core.member;

import jp.falsystack.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  private MemberService memberService;

  @BeforeEach
  void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }

  @Test
  @DisplayName("회원가입")
  void join() {
    //given
    Member member = new Member(1L, "memberA", Grade.VIP);

    //when(when절에는 test의 목적이 와야한다.)
    memberService.join(member);
    Member findMember = memberService.findMember(member.getId());

    //then
    Assertions.assertThat(member).isEqualTo(findMember);
  }

}