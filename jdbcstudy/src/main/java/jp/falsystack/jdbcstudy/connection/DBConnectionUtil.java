package jp.falsystack.jdbcstudy.connection;

import static jp.falsystack.jdbcstudy.connection.ConnectionConst.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionUtil {

  public static Connection getConnection() {
    try {
      var conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      log.info("conn = {}, class = {}", conn, conn.getClass());
      return conn;
    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }

}
