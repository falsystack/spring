package jp.falsystack.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import javax.inject.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class SingletonWithPrototypeTest1 {

  @Test
  @DisplayName("")
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class);

    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(1);

    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }

  @Test
  @DisplayName("")
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ClientBean.class,
        PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int logic1 = clientBean1.logic();
    assertThat(logic1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int logic2 = clientBean2.logic();
    assertThat(logic2).isEqualTo(1);
  }

  @Scope("prototype")
  @Component
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init" + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }

  @Scope("singleton")
  @Component
  static class ClientBean {

    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider;

//    @Autowired
//    private ObjectFactory<PrototypeBean> prototypeBeanFactory;


    public int logic() {
      // DL(Dependency Lookup)
//      PrototypeBean prototypeBean = prototypeBeanFactory.getObject();
//      PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
      PrototypeBean prototypeBean = prototypeBeanProvider.get();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }
}
