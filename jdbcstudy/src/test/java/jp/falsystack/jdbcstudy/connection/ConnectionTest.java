package jp.falsystack.jdbcstudy.connection;

import static jp.falsystack.jdbcstudy.connection.ConnectionConst.PASSWORD;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.URL;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
class ConnectionTest {

  @Test
  void driverManager() throws SQLException {
    var con1 = DriverManager.getConnection(URL, USERNAME,
        PASSWORD);
    var con2 = DriverManager.getConnection(URL, USERNAME,
        PASSWORD);
    log.info("con1 = {}, class={}", con1, con1.getClass());
    log.info("con2 = {}, class={}", con2, con2.getClass());
  }

  @Test
  void dataSourceDriverManager() throws SQLException {
    // DriverManagerDataSource - 항상 새로운 커넥션을 획득
    var dataSource = new DriverManagerDataSource(URL, USERNAME,
        PASSWORD);

    useDataSoure(dataSource);
  }

  private void useDataSoure(DriverManagerDataSource dataSource) throws SQLException {
    var con1 = dataSource.getConnection();
    var con2 = dataSource.getConnection();
    log.info("con1 = {}, class={}", con1, con1.getClass());
    log.info("con2 = {}, class={}", con2, con2.getClass());
  }
}
