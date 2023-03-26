package jp.falsystack.servlet.web.frontcontroller.v3;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jp.falsystack.servlet.web.frontcontroller.ModelView;
import jp.falsystack.servlet.web.frontcontroller.MyView;
import jp.falsystack.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import jp.falsystack.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import jp.falsystack.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

  private final Map<String, ControllerV3> controllerMap = new HashMap<>();

  public FrontControllerServletV3() {
    controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String requestURI = request.getRequestURI();
    ControllerV3 controller = controllerMap.get(requestURI);
    if (controller == null) {
      response.setStatus(SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(request);
    ModelView mv = controller.process(paramMap);

    String viewName = mv.getViewName();
    MyView view = viewResolver(viewName);

    view.render(mv.getModel(), request, response);
  }

  private static MyView viewResolver(String viewName) {
    MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
    return view;
  }

  private static Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining((paramName) -> paramMap.put(paramName, request.getParameter(paramName)));
    return paramMap;
  }


}
