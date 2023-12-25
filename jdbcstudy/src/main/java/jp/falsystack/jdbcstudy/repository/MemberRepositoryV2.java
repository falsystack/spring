package jp.falsystack.jdbcstudy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import jp.falsystack.jdbcstudy.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * JDBC - Connection Parameter
 */
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryV2 {

  private final DataSource dataSource;

  public Member save(Member member) throws SQLException {
    // parameter binding을 사용하면 sql 인젝션을 방지할 수 있다.
    String sql = "insert into member(member_id, money) values (?, ?)";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, member.getMemberId());
      pstmt.setInt(2, member.getMoney());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      log.error("db error = ", e);
      throw e;
    } finally {
      close(conn, pstmt, null);
    }

    return member;
  }

  public Member findById(String memberId) throws SQLException {
    String sql = "select * from member where member_id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, memberId);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        var member = new Member();
        member.setMemberId(rs.getString("member_id"));
        member.setMoney(rs.getInt("money"));
        return member;
      } else {
        throw new NoSuchElementException("member not found memberId=" + memberId);
      }
    } catch (SQLException e) {
      log.error("db error", e);
      throw e;
    } finally {
      close(conn, pstmt, rs);
    }
  }

  public Member findById(Connection conn, String memberId) throws SQLException {
    String sql = "select * from member where member_id = ?";

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, memberId);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        var member = new Member();
        member.setMemberId(rs.getString("member_id"));
        member.setMoney(rs.getInt("money"));
        return member;
      } else {
        throw new NoSuchElementException("member not found memberId=" + memberId);
      }
    } catch (SQLException e) {
      log.error("db error", e);
      throw e;
    } finally {
      // connection은 여기서 닫지 않는다.
      JdbcUtils.closeResultSet(rs);
      JdbcUtils.closeStatement(pstmt);
//      JdbcUtils.closeConnection(conn);
    }
  }

  public void update(String memberId, int money) throws SQLException {
    String sql = "update member set money=? where member_id=?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, money);
      pstmt.setString(2, memberId);
      var resultSize = pstmt.executeUpdate();
      log.info("resultSize = {}", resultSize);
    } catch (SQLException e) {
      log.error("db error = ", e);
      throw e;
    } finally {
      close(conn, pstmt, null);
    }
  }

  public void update(Connection conn, String memberId, int money) throws SQLException {
    String sql = "update member set money=? where member_id=?";

    PreparedStatement pstmt = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, money);
      pstmt.setString(2, memberId);
      var resultSize = pstmt.executeUpdate();
      log.info("resultSize = {}", resultSize);
    } catch (SQLException e) {
      log.error("db error = ", e);
      throw e;
    } finally {
      // connection은 여기서 닫지 않는다.
      JdbcUtils.closeStatement(pstmt);
    }
  }

  public void delete(String memberId) throws SQLException {
    String sql = "delete from member where member_id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, memberId);
      var resultSize = pstmt.executeUpdate();
      log.info("resultSize = {}", resultSize);
    } catch (SQLException e) {
      log.error("db error = ", e);
      throw e;
    } finally {
      close(conn, pstmt, null);
    }

  }

  private void close(Connection conn, Statement stmt, ResultSet rs) {
    JdbcUtils.closeResultSet(rs);
    JdbcUtils.closeStatement(stmt);
    JdbcUtils.closeConnection(conn);
  }

  private Connection getConnection() throws SQLException {
    var con = dataSource.getConnection();
    log.info("con = {}, con.class = {}", con, con.getClass());
    return con;
  }
}
