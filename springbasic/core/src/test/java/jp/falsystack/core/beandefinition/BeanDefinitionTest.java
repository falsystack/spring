package jp.falsystack.core.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

  //  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
  GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

  @Test
  @DisplayName("빈 설정 메타정보 확인")
  void findApplicationBean() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String name : beanDefinitionNames) {
      BeanDefinition beanDefinition = ac.getBeanDefinition(name);
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        System.out.println("name = " + name + " beanDefinition = " + beanDefinition);
      }
    }
  }

}
