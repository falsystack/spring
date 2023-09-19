package jp.falsystack.springtx.apply;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class TxBasicTest {

  @Autowired
  BasicService basicService;

  @Test
  void proxyCheck() {
    log.info("AOP class = {}", basicService.getClass());
    assertThat(AopUtils.isAopProxy(basicService)).isTrue();
  }

  @Test
  void txText() {
    basicService.tx();
    basicService.nonTx();
  }

  @TestConfiguration
  static class TxApplyBasicConfig {

    @Bean
    BasicService basicService() {
      return new BasicService();
    }
  }

  @Slf4j
  static class BasicService {

    @Transactional
    public void tx() {
      log.info("tx called");
      log.info("tx active = {}", TransactionSynchronizationManager.isActualTransactionActive());
    }

    public void nonTx() {
      log.info("nonTx called");
      log.info("nonTx active = {}", TransactionSynchronizationManager.isActualTransactionActive());
    }
  }


}
