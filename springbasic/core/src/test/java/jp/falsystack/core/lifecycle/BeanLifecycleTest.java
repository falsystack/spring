package jp.falsystack.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifecycleTest {

  @Test
  @DisplayName("")
  void lifeCycleTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);

    NetworkClient client = ac.getBean(NetworkClient.class);
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig {

//    @Bean(initMethod = "init", destroyMethod = "close")
    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://www.falsystack.jp");
      return networkClient;
    }

  }
}
