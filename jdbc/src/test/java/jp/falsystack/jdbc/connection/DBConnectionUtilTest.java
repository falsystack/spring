package jp.falsystack.jdbc.connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionUtilTest {

    @Test
    @DisplayName("connection test")
    void connection() {
        // given
        Connection connection = DBConnectionUtil.getConnection();

        // expected
        Assertions.assertThat(connection).isNotNull();
    }

}