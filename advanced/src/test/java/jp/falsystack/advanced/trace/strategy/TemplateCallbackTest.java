package jp.falsystack.advanced.trace.strategy;

import jp.falsystack.advanced.trace.strategy.code.templatecallback.Callback;
import jp.falsystack.advanced.trace.strategy.code.templatecallback.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

  /**
   * 탬플릿 콜백 패턴 - 익명 내부 클래스
   */
  @Test
  void callbackV1() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("비즈니스 로직 1 실행");
      }
    });

    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("비즈니스 로직 2 실행");
      }
    });
  }

  /**
   * 탬플릿 콜백 패턴 - lambda
   */
  @Test
  void callbackV2() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(() -> log.info("비즈니스 로직 1 실행"));
    template.execute(() -> log.info("비즈니스 로직 2 실행"));
  }
}
