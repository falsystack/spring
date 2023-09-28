package jp.falsystack.advanced.trace.template;

import jp.falsystack.advanced.trace.template.code.AbstractTemplate;
import jp.falsystack.advanced.trace.template.code.SubClassLogic1;
import jp.falsystack.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
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
   * 탬플릿 메서드 패턴 적용
   */
  @Test
  void templateMethodV1() {
    AbstractTemplate template1 = new SubClassLogic1();
    template1.execute();

    AbstractTemplate template2 = new SubClassLogic2();
    template2.execute();
  }

  @Test
  void templateMethodV2() {
    AbstractTemplate template1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직 1 실행");
      }
    };
    log.info("클래스 이름1 = {}", template1.getClass());
    template1.execute();

    AbstractTemplate template2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직 1 실행");
      }
    };
    log.info("클래스 이름2 = {}", template2.getClass());
    template2.execute();
  }

}
