package jp.falsystack.springtx.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class InitTxTest {

  @Autowired
  Hello hello;

  @Test
  void go() {
    // 초기화 코드는 스프링이 초기화 시점에 호출한다.
  }

  @TestConfiguration
  static class InitTxTestConfig {

    @Bean
    Hello hello() {
      return new Hello();
    }

  }

  @Slf4j
  static class Hello {

    @PostConstruct
    @Transactional
    public void initV1() {
      log.info("Hello init @POstConstruct tx active = {}",
          TransactionSynchronizationManager.isActualTransactionActive());
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initV2() {
      log.info("Hello init ApplicationReadyEvent tx active = {}",
          TransactionSynchronizationManager.isActualTransactionActive());
    }
  }
}
