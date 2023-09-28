package jp.falsystack.advanced.trace.strategy;

import jp.falsystack.advanced.trace.strategy.code.strategy.ContextV1;
import jp.falsystack.advanced.trace.strategy.code.strategy.Strategy;
import jp.falsystack.advanced.trace.strategy.code.strategy.StrategyLogic1;
import jp.falsystack.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

  @Test
  void strategyV0() {
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    // business logic start
    log.info("비즈니스 로직1 실행");
    // business logic end
    long endTime = System.currentTimeMillis();
    long resultTIme = endTime - startTime;
    log.info("resultTime = {} ", resultTIme);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    // business logic start
    log.info("비즈니스 로직2 실행");
    // business logic end
    long endTime = System.currentTimeMillis();
    long resultTIme = endTime - startTime;
    log.info("resultTime = {} ", resultTIme);
  }

  /**
   * 전략 패턴 사용
   */
  @Test
  void strategyV1() {
    StrategyLogic1 strategyLogic1 = new StrategyLogic1();
    ContextV1 context1 = new ContextV1(strategyLogic1);
    context1.execute();

    StrategyLogic2 strategyLogic2 = new StrategyLogic2();
    ContextV1 context2 = new ContextV1(strategyLogic2);
    context2.execute();
  }

  @Test
  void strategyV2() {
    Strategy strategyLogic1 = () -> log.info("비즈니스 로직1 실행");
    ContextV1 context1 = new ContextV1(strategyLogic1);
    context1.execute();

    Strategy strategyLogic2 = () -> log.info("비즈니스 로직2 실행");
    ContextV1 context2 = new ContextV1(strategyLogic2);
    context2.execute();
  }

  @Test
  void strategyV3() {
    ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
    context1.execute();

    ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
    context2.execute();
  }
}
