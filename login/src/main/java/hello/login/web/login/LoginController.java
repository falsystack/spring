package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;
  private final SessionManager sessionManager;

  @GetMapping("/login")
  public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
    return "login/loginForm";
  }

//  @PostMapping("/login")
  public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
      HttpServletResponse response) {
    if (bindingResult.hasErrors()) {
      return "login/loginForm";
    }

    var loginMember = loginService.login(form.getLoginId(), form.getPassword());

    if (loginMember == null) {
      bindingResult.reject("loginFail", "ID/PW 가 맞지 않습니다.");
      return "login/loginForm";
    }

    response.addCookie(new Cookie("memberId", loginMember.getId().toString()));

    return "redirect:/";
  }

//  @PostMapping("/login")
  public String loginv2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
      HttpServletResponse response) {
    if (bindingResult.hasErrors()) {
      return "login/loginForm";
    }

    var loginMember = loginService.login(form.getLoginId(), form.getPassword());

    if (loginMember == null) {
      bindingResult.reject("loginFail", "ID/PW 가 맞지 않습니다.");
      return "login/loginForm";
    }

    sessionManager.createSession(loginMember, response);

    return "redirect:/";
  }

//  @PostMapping("/login")
  public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
      HttpServletResponse response, HttpServletRequest request) {
    if (bindingResult.hasErrors()) {
      return "login/loginForm";
    }

    var loginMember = loginService.login(form.getLoginId(), form.getPassword());

    if (loginMember == null) {
      bindingResult.reject("loginFail", "ID/PW 가 맞지 않습니다.");
      return "login/loginForm";
    }

    // http servlet sessionを使用
    // セッションがあったらセッションを返す、無かったら新しく生成する
    var session = request.getSession();
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    return "redirect:/";
  }

  @PostMapping("/login")
  public String loginV4(
      @Valid @ModelAttribute LoginForm form,
      BindingResult bindingResult,
      HttpServletResponse response,
      HttpServletRequest request,
      @RequestParam(defaultValue = "/") String redirectURL) {
    if (bindingResult.hasErrors()) {
      return "login/loginForm";
    }

    var loginMember = loginService.login(form.getLoginId(), form.getPassword());

    if (loginMember == null) {
      bindingResult.reject("loginFail", "ID/PW 가 맞지 않습니다.");
      return "login/loginForm";
    }

    // http servlet sessionを使用
    // セッションがあったらセッションを返す、無かったら新しく生成する
    var session = request.getSession();
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    return "redirect:" + redirectURL;
  }

//  @PostMapping("/logout")
  public String logout(HttpServletResponse response) {
    expireCookie(response);
    return "redirect:/";
  }

//  @PostMapping("/logout")
  public String logoutV2(HttpServletRequest request, HttpServletResponse response) {
    sessionManager.expire(request);
    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logoutV3(HttpServletRequest request, HttpServletResponse response) {
    var session = request.getSession(false);
    if (session != null) {
      session.invalidate(); // destroy session
    }
    return "redirect:/";
  }

  private static void expireCookie(HttpServletResponse response) {
    var cookie = new Cookie("memberId", null);
    cookie.setMaxAge(-1);
    response.addCookie(cookie);
  }
}
