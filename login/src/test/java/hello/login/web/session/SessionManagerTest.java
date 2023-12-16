package hello.login.web.session;

import static org.assertj.core.api.Assertions.*;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

  private SessionManager sessionManager = new SessionManager();

  @Test
  void sessionTest() {

    // 세션 생성 (서버)
    var response = new MockHttpServletResponse();
    var member = new Member("1", "kakao", "kakao!");
    sessionManager.createSession(member, response);

    // 요청에 응답 쿠키 저장 (클라이언트)
    var request = new MockHttpServletRequest();
    request.setCookies(response.getCookies()); // mySessionId=123123-123123-12312d

    // 세션 조회
    var result = sessionManager.getSession(request);
    assertThat(result).isEqualTo(member);

    // 세션 만료
    sessionManager.expire(request);
    var expired = sessionManager.getSession(request);
    assertThat(expired).isNull();
  }

}