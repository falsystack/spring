package jp.falsystack.jdbcstudy.connection;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class DBConnectionUtilTest {

  @Test
  void connection() {
    var conn = DBConnectionUtil.getConnection();
    assertThat(conn).isNotNull();
  }

}