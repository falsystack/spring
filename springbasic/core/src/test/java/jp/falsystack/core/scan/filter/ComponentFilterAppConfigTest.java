package jp.falsystack.core.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.context.annotation.ComponentScan.Filter;
import static org.springframework.context.annotation.FilterType.ANNOTATION;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class ComponentFilterAppConfigTest {

  @Test
  @DisplayName("")
  void filterScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ComponentFilterAppConfig.class);
    BeanA beanA = ac.getBean("beanA", BeanA.class);
    assertThat(beanA).isNotNull();
    assertThatThrownBy(() -> ac.getBean("beanB")).isInstanceOf(NoSuchBeanDefinitionException.class);
  }


  @Configuration
  @ComponentScan(
      includeFilters = @Filter(type = ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @Filter(type = ANNOTATION, classes = MyExcludeCompnent.class)
  )
  static class ComponentFilterAppConfig {

  }
}
