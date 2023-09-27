package jp.falsystack.advanced;

import jp.falsystack.advanced.trace.logtrace.FieldLogTrace;
import jp.falsystack.advanced.trace.logtrace.LogTrace;
import jp.falsystack.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new ThreadLocalLogTrace();
  }

}