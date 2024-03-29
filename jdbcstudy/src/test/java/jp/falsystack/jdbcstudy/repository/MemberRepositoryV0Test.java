package jp.falsystack.jdbcstudy.repository;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import jp.falsystack.jdbcstudy.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class MemberRepositoryV0Test {

  MemberRepositoryV0 repository = new MemberRepositoryV0();

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
  }

}