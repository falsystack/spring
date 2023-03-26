package jp.falsystack.servlet.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jp.falsystack.servlet.web.frontcontroller.MyView;
import jp.falsystack.servlet.web.frontcontroller.v2.ControllerV2;

public class MemberFormControllerV2 implements ControllerV2 {

  @Override
  public MyView process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    return new MyView("/WEB-INF/views/new-form.jsp");
  }
}
