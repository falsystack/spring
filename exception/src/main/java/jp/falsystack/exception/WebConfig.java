package jp.falsystack.exception;

import java.util.List;
import jp.falsystack.exception.resolver.MyHandlerExceptionResolver;
import jp.falsystack.exception.resolver.UserHandlerExceptionResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

  @Override
  public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    resolvers.add(new MyHandlerExceptionResolver());
    resolvers.add(new UserHandlerExceptionResolver());
  }
}
