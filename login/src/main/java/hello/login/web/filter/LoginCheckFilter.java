package hello.login.web.filter;

import static hello.login.web.SessionConst.*;

import hello.login.web.SessionConst;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginCheckFilter implements Filter {

  public static final String[] whiteList = {"/", "/members/add", "/login", "/logout", "/css/*"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    var httpRequest = (HttpServletRequest) request;
    var requestURI = httpRequest.getRequestURI();

    var httpResponse = (HttpServletResponse) response;

    try {
      log.info("인증 체크 필터 시작 {}", requestURI);

      if (isLoginCheckPath(requestURI)) {
        log.info("인증 체크 로직 실행 {}", requestURI);
        var session = httpRequest.getSession(false);

        if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
          log.info("미인증 사용자 요청 {}", requestURI);
          // 로그인으로 redirect
          httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
          return;
        }
      }

      chain.doFilter(request, response);
    } catch (Exception e) {
      throw e; // 예외 로깅 가능하지만, 톰캣까지 예외를 보내주어야 한다.
      // 여기서 예외를 먹어버리면 정상흐름처럼 작동하기 때문이다.
    } finally {
      log.info("인증 체크 필터 종료 {}", requestURI);
    }
  }

  /**
   * 화이트 리스트의 경우 인증 체크X
   */
  private boolean isLoginCheckPath(String requestURI) {
    return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
  }

}
