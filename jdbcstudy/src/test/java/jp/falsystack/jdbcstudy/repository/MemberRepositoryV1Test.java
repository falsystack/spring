package jp.falsystack.jdbcstudy.repository;

import static jp.falsystack.jdbcstudy.connection.ConnectionConst.PASSWORD;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.URL;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import jp.falsystack.jdbcstudy.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
class MemberRepositoryV1Test {

  MemberRepositoryV1 repository;

  @BeforeEach
  void setUp() {
    // 기본 DriverManager - 항상 새로운 커넥션을 획득
   //  var dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

    // 커넥션 풀링
    var dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    repository = new MemberRepositoryV1(dataSource);
  }

  @Test
  void crud() throws SQLException {
    // save
    var member = new Member("memberV4", 10000);
    repository.save(member);

    // findById
    var findMember = repository.findById(member.getMemberId());
    log.info("findMember = {}", findMember);
    assertThat(findMember).isEqualTo(member);

    // update: money 10000 -> 20000
    repository.update(member.getMemberId(), 20000);
    var updatedMember = repository.findById(member.getMemberId());
    assertThat(updatedMember.getMoney()).isEqualTo(20000);

    // delete
    repository.delete(member.getMemberId());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}