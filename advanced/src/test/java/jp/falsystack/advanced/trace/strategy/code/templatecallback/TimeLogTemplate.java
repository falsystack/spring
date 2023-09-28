package jp.falsystack.advanced.trace.strategy.code.templatecallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

  public void execute(Callback callback) {
    long startTime = System.currentTimeMillis();
    // business logic start
    callback.call(); // 委任
    // business logic end
    long endTime = System.currentTimeMillis();
    long resultTIme = endTime - startTime;
    log.info("resultTime = {} ", resultTIme);
  }
}
