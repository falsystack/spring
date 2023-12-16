package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    log.info("supportsParameter 실행");

    var hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
    var hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

    return hasLoginAnnotation && hasMemberType;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    log.info("resolveArgument 실행");

    var request = (HttpServletRequest) webRequest.getNativeRequest();
    var session = request.getSession(false);
    if (session == null) {
      return null;
    }

    return session.getAttribute(SessionConst.LOGIN_MEMBER);
  }
}
