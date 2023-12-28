package jp.falsystack.jdbcstudy.exception.translator;

import static jp.falsystack.jdbcstudy.connection.ConnectionConst.PASSWORD;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.URL;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.sql.DataSource;
import jp.falsystack.jdbcstudy.domain.Member;
import jp.falsystack.jdbcstudy.repository.ex.MyDbException;
import jp.falsystack.jdbcstudy.repository.ex.MyDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

@Slf4j
public class ExTranslatorV1Test {

  Repository repository;
  Service service;

  @BeforeEach
  void setUp() {
    var dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    repository = new Repository(dataSource);
    service = new Service(repository);
  }

  @Test
  void duplicateKeySave() {
    service.create("myId");
    service.create("myId");
  }


  @Slf4j
  @RequiredArgsConstructor
  static class Service {

    private final Repository repository;

    public void create(String memberId) {
      try {
        repository.save(new Member(memberId, 0));
        log.info("saveId = {}", memberId);
      } catch (MyDuplicateException e) {
        log.info("키 중복, 복구 시도");
        var retryId = generateNewId(memberId);
        log.info("retryId = {}", retryId);
        repository.save(new Member(retryId, 0));
      } catch (MyDbException e) {
        log.info("데이터 접근 계층 예외", e);
        throw e;
      }
    }

    private String generateNewId(String memberId) {
      return memberId + new Random().nextInt(10000);
    }
  }


  @RequiredArgsConstructor
  static class Repository {

    private final DataSource dataSource;

    public Member save(Member member) {
      var sql = "insert into member(member_id, money) values(?, ?)";
      Connection con = null;
      PreparedStatement pstmt = null;

      try {
        con = dataSource.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, member.getMemberId());
        pstmt.setInt(2, member.getMoney());
        pstmt.executeUpdate();
        return member;
      } catch (SQLException e) {
        // h2 db
        if (e.getErrorCode() == 23505) {
          throw new MyDuplicateException(e);
        }
        throw new MyDbException(e);
      } finally {
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(con);
      }
    }

  }
}
