package jp.falsystack.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jp.falsystack.servlet.basic.HelloData;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //Content-Type: application/json
    response.setContentType("application/json");

    HelloData helloData = new HelloData();
    helloData.setUsername("kakao");
    helloData.setAge(20);

    // {"username": "kakao", "age": 20}
    String result = objectMapper.writeValueAsString(helloData);

//    response.getWriter().write(result);
    response.getOutputStream().write(result.getBytes());
  }
}
