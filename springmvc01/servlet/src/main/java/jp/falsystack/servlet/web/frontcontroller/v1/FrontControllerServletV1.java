package jp.falsystack.servlet.web.frontcontroller.v1;

import static jakarta.servlet.http.HttpServletResponse.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jp.falsystack.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import jp.falsystack.servlet.web.frontcontroller.v1.controller.MemberListContollerV1;
import jp.falsystack.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

  private final Map<String, ControllerV1> controllerMap = new HashMap<>();

  public FrontControllerServletV1() {
    controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
    controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
    controllerMap.put("/front-controller/v1/members", new MemberListContollerV1());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("FrontControllerServletV1.service");

    String requestURI = request.getRequestURI();
    ControllerV1 controller = controllerMap.get(requestURI);
    if (controller == null) {
      response.setStatus(SC_NOT_FOUND);
      return;
    }

    controller.process(request, response);
  }
}
