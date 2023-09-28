package jp.falsystack.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;
// 탬플릿 메서드 패턴, 상속으로 해결하기
@Slf4j
public abstract class AbstractTemplate {

  public void execute() {
    long startTime = System.currentTimeMillis();
    // business logic start
    call();
    // business logic end
    long endTime = System.currentTimeMillis();
    long resultTIme = endTime - startTime;
    log.info("resultTime = {} ", resultTIme);
  }

  protected abstract void call();
}
