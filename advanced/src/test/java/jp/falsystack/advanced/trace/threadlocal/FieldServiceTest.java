package jp.falsystack.advanced.trace.threadlocal;

import jp.falsystack.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

  private FieldService fieldService = new FieldService();

  @Test
  void field() {
    log.info("main start");
    Runnable userA = () -> fieldService.logic("userA");
    Runnable userB = () -> fieldService.logic("userB");

    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");

    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
//    sleep(2000); // 동시성 발생하지 않게 길게쉼
    sleep(100); // 동시성 발생하게 짧게
    threadB.start();


    sleep(3000); // 대기
    log.info("main exit");
  }

  private void sleep(int mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
