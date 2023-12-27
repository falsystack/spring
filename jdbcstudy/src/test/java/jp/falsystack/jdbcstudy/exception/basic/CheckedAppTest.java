package jp.falsystack.jdbcstudy.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.ConnectException;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class CheckedAppTest {

  @Test
  void checked() {
    var controller = new Controller();
    assertThatThrownBy(controller::request).isInstanceOf(Exception.class);
  }

  private static class Controller {

    Service service = new Service();

    public void request() throws SQLException, ConnectException {
      service.logic();
    }
  }

  private static class Service {

    private final Repository repository = new Repository();
    private final NetworkClient networkClient = new NetworkClient();

    public void logic() throws SQLException, ConnectException {
      repository.call();
      networkClient.call();
    }
  }

  private static class NetworkClient {

    public void call() throws ConnectException {
      throw new ConnectException("연결 실패");
    }
  }

  private static class Repository {

    public void call() throws SQLException {
      throw new SQLException("ex");
    }
  }


}
