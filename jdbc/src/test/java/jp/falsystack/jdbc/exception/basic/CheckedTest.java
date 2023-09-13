package jp.falsystack.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

  @Test
  @DisplayName("checked catch")
  void checked_catch() {
    Service service = new Service();
    service.callCatch();
  }

  @Test
  @DisplayName("checked_throw")
  void checked_throw() {
    Service service = new Service();
    assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyCheckedException.class);
  }

  /**
   * Checked 예외는 예외를 잡아서 처리하거나, 던지거나 둘중 하나를 필수로 선택해야 한다.
   */
  private static class Service {

    Repository repository = new Repository();

    /**
     * 예외를 잡아서 처리하는 코드
     */
    public void callCatch() {
      try {
        repository.call();
      } catch (MyCheckedException e) {
        //예외 처리 로직
        log.info("예외 처리, message = {}", e.getMessage(), e);
      }
    }

    /**
     * 체크 예외를 밖으로 던지는 코드 체크 예외는 예외를 잡지 않고 밖으로 던지려면 throws 예외를 메서드에 필수로 선언해야한다.
     */
    public void callThrow() throws MyCheckedException {
      repository.call();
    }

  }

  private static class Repository {

    public void call() throws MyCheckedException {
      throw new MyCheckedException("ex");
    }
  }

  private static class MyCheckedException extends Exception {

    public MyCheckedException(String message) {
      super(message);
    }
  }


}
