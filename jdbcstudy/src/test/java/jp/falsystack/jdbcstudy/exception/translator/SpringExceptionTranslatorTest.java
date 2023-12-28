package jp.falsystack.jdbcstudy.exception.translator;

import static jp.falsystack.jdbcstudy.connection.ConnectionConst.PASSWORD;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.URL;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

@Slf4j
public class SpringExceptionTranslatorTest {

  DataSource dataSource;

  @BeforeEach
  void setUp() {
    dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
  }

  @Test
  void sqlExceptionErrorCode() {
    String sql = "select bad grammar";

    try {
      var con = dataSource.getConnection();
      var pstmt = con.prepareStatement(sql);
      pstmt.executeQuery();
    } catch (SQLException e) {
      var errorCode = e.getErrorCode();
      assertThat(errorCode).isEqualTo(42122);
      log.info("errorCode = {}", errorCode);
      log.info("error", e);
    }
  }

  @Test
  void exceptionTranslator() {
    String sql = "select bad grammar";
    try {
      var con = dataSource.getConnection();
      var pstmt = con.prepareStatement(sql);
      pstmt.executeQuery();
    } catch (SQLException e) {
      assertThat(e.getErrorCode()).isEqualTo(42122);
      var translator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
      var result = translator.translate("select", sql, e);
      log.info("resultEx", result);
      assertThat(result.getClass()).isEqualTo(BadSqlGrammarException.class);
    }
  }
}
