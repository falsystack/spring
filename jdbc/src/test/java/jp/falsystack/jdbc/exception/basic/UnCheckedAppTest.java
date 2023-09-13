package jp.falsystack.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.ConnectException;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class UnCheckedAppTest {

  @Test
  @DisplayName("checked")
  void checked() {
    Controller controller = new Controller();
    assertThatThrownBy(() -> controller.request()).isInstanceOf(Exception.class);
  }

  @Test
  @DisplayName("printEx")
  void printEx() {
    Controller controller = new Controller();
    try {
      controller.request();
    } catch (Exception e) {
      // e.printStackTrace();를 부르는건 안좋은 패턴이다. System.out으로 출력되게 된다.
//      e.printStackTrace();
      log.info("ex", e);
    }
  }


  static class Controller {

    Service service = new Service();

    public void request() {
      service.logic();
    }

  }


  static class Service {

    Repository repository = new Repository();
    NetworkClient networkClient = new NetworkClient();

    public void logic() {
      repository.call();
      networkClient.call();
    }
  }

  static class NetworkClient {

    public void call() {
      throw new RuntimeConnectException("연결 실패");
    }
  }

  static class Repository {
    public void call() {
      try {
        runSQL();
      } catch (SQLException e) {
        // e를 넘기는걸 잊으면 안된다.
        throw new RuntimeSQLException(e);
      }
    }

    public void runSQL() throws SQLException {
      throw new SQLException("ex");
    }
  }

  static class RuntimeConnectException extends RuntimeException {

    public RuntimeConnectException(String message) {
      super(message);
    }
  }

  static class RuntimeSQLException extends RuntimeException {

    public RuntimeSQLException(Throwable cause) {
      super(cause);
    }
  }


}
