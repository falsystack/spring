package jp.falsystack.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedTest {

  @Test
  @DisplayName("unchecked_catch")
  void unchecked_catch() {
    Service service = new Service();
    service.callCatch();
  }

  @Test
  @DisplayName("unchecked_throw")
  void unchedcked_throw() {
    Service service = new Service();
    assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyUncheckedException.class);
  }

  /**
   * RuntimeException을 상속받은 예외는 언체크 예외가 된다.
   */
  static class MyUncheckedException extends RuntimeException {

    public MyUncheckedException(String message) {
      super(message);
    }
  }

  /**
   * Unchecked 예외는 에외를 잡거나, 던지지 ㅇ낳아도 된다. 예외를 잡지 않으면 자동으로 밖으로 던진다.
   */
  static class Service {

    Repository repository = new Repository();

    /**
     * 필요한 경우 예외를 잡아서 처리하면 된다.
     */
    public void callCatch() {
      try {
        repository.call();
      } catch (MyUncheckedException e) {
        // 에외 처리 로직
        log.info("에외 처리, message = {}", e.getMessage(), e);
      }
    }

    /**
     * 예외를 잡지 않아도 된다. 자연스럽게 상위로 넘어간다. 체크 예외와 다르게 throws 예외 선언을 하지 않아도 된다.
     */
    public void callThrow() {
      repository.call();
    }
  }

  static class Repository {

    public void call() {
      throw new MyUncheckedException("ex");
    }
  }


}
