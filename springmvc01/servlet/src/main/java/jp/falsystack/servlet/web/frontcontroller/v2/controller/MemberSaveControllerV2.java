package jp.falsystack.servlet.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jp.falsystack.servlet.domain.member.Member;
import jp.falsystack.servlet.domain.member.MemberRepository;
import jp.falsystack.servlet.web.frontcontroller.MyView;
import jp.falsystack.servlet.web.frontcontroller.v2.ControllerV2;

public class MemberSaveControllerV2 implements ControllerV2 {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public MyView process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);

    memberRepository.save(member);

    //Model 에 데이터를 보관한다.
    request.setAttribute("member", member);

    return new MyView("/WEB-INF/views/save-result.jsp");
  }
}
