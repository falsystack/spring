package jp.falsystack.jdbcstudy.repository;

import javax.sql.DataSource;
import jp.falsystack.jdbcstudy.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * JdbcTemplate 사용
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository {

  private final JdbcTemplate template;

  public MemberRepositoryV5(DataSource dataSource) {
    this.template = new JdbcTemplate(dataSource);
  }

  @Override
  public Member save(Member member) {
    // parameter binding을 사용하면 sql 인젝션을 방지할 수 있다.
    String sql = "insert into member(member_id, money) values (?, ?)";

    template.update(sql, member.getMemberId(), member.getMoney());

    return member;
  }

  @Override
  public Member findById(String memberId) {
    String sql = "select * from member where member_id = ?";

    return (Member) template.queryForObject(sql, memberRowMapper(), memberId);
  }

  @Override
  public void update(String memberId, int money) {
    String sql = "update member set money=? where member_id=?";

    template.update(sql, money, memberId);
  }

  @Override
  public void delete(String memberId) {
    String sql = "delete from member where member_id = ?";

    template.update(sql, memberId);
  }

  private RowMapper<Object> memberRowMapper() {
    return (rs, rowNum) -> {
      var member = new Member();
      member.setMemberId(rs.getString("member_id"));
      member.setMoney(rs.getInt("money"));
      return member;
    };
  }
}
