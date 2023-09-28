package jp.falsystack.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {

  private Strategy strategy;

  public ContextV1(Strategy strategy) {
    this.strategy = strategy;
  }

  public void execute() {
    long startTime = System.currentTimeMillis();
    // business logic start
    strategy.call(); // 위임, delegate
    // business logic end
    long endTime = System.currentTimeMillis();
    long resultTIme = endTime - startTime;
    log.info("resultTime = {} ", resultTIme);
  }
}
