package jp.falsystack.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import jp.falsystack.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ReqeustParamController {

  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    log.info("username = {} ", username);
    log.info("age = {} ", age);

    response.getWriter().write("OK");
  }


  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
      @RequestParam("username") String memberName,
      @RequestParam("age") int memberAge) {

    log.info("memberName = {} ", memberName);
    log.info("memberAge = {} ", memberAge);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
      @RequestParam String memberName,
      @RequestParam int memberAge) {

    log.info("memberName = {} ", memberName);
    log.info("memberAge = {} ", memberAge);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(
      String username,
      int age) {

    log.info("username = {} ", username);
    log.info("age = {} ", age);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(
      @RequestParam String username, // 필수 값이 없을 경우 bad request - 400
      @RequestParam(required = false) Integer age // int는 null이 들어갈 수 없다. 조심
  ) {
    log.info("username = {} ", username);
    log.info("age = {} ", age);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
      @RequestParam(defaultValue = "guest") String username,
      @RequestParam(required = false, defaultValue = "-1") Integer age
  ) {
    log.info("username = {} ", username);
    log.info("age = {} ", age);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(
      @RequestParam Map<String, Object> paramMap
  ) {
    log.info("username = {} ", paramMap.get("username"));
    log.info("age = {} ", paramMap.get("age"));

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v0")
  public String modelAttributeV0(@RequestParam String username,
      @RequestParam int age) {
    log.info("username = {} ", username);
    log.info("age = {} ", age);

    HelloData helloData = new HelloData();
    helloData.setUsername(username);
    helloData.setAge(age);

    log.info("helloData = {} ", helloData);
    return "OK";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v1")
  public String modelAttributeV1(@ModelAttribute HelloData helloData) {
    log.info("helloData = {} ", helloData);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v2")
  public String modelAttributeV2(HelloData helloData) {
    log.info("helloData = {} ", helloData);

    return "OK";
  }

}

