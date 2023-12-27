package jp.falsystack.jdbcstudy.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class UnCheckedAppTest {

  @Test
  void unchecked() {
    var controller = new Controller();
    assertThatThrownBy(controller::request).isInstanceOf(Exception.class);
  }

  @Test
  void printEx() {
    var controller = new Controller();
    try {
      controller.request();
    } catch (Exception e) {
      log.info("ex", e);
    }
  }

  private static class Controller {

    Service service = new Service();

    public void request() {
      service.logic();
    }
  }

  private static class Service {

    private final Repository repository = new Repository();
    private final NetworkClient networkClient = new NetworkClient();

    public void logic() {
      repository.call();
      networkClient.call();
    }
  }

  private static class NetworkClient {

    public void call() {
      throw new RuntimeConnectException("연결 실패");
    }
  }

  private static class Repository {

    public void call() {
      try {
        runSQL();
      } catch (SQLException e) {
        // Checked 예외를 Unchecked 예외로 변환
        // 예외를 변환 할 때는 꼭 기존 exception 을 전달해 주어야 한다.
        throw new RuntimeSQLException(e);
      }
    }

    public void runSQL() throws SQLException {
      throw new SQLException("ex");
    }
  }

  private static class RuntimeConnectException extends RuntimeException {

    public RuntimeConnectException(String message) {
      super(message);
    }
  }

  private static class RuntimeSQLException extends RuntimeException {

    public RuntimeSQLException(Throwable cause) {
      super(cause);
    }
  }


}
