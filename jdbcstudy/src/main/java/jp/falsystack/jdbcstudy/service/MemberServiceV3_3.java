package jp.falsystack.jdbcstudy.service;

import java.sql.SQLException;
import jp.falsystack.jdbcstudy.domain.Member;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 트랜잭션 -
 */
@Slf4j
public class MemberServiceV3_3 {

  private final MemberRepositoryV3 memberRepository;

  public MemberServiceV3_3(MemberRepositoryV3 memberRepository) {
    this.memberRepository = memberRepository;
  }

  private static void validation(Member toMember) {
    if (toMember.getMemberId().equals("ex")) {
      throw new IllegalStateException("이체중 예외 발생");
    }
  }

  @Transactional
  public void accountTransfer(String fromId, String toId, int money) throws SQLException {
    bizLogic(fromId, toId, money);
  }

  private void bizLogic(String fromId, String toId, int money)
      throws SQLException {
    var fromMember = memberRepository.findById(fromId);
    var toMember = memberRepository.findById(toId);

    memberRepository.update(fromId, fromMember.getMoney() - money);
    validation(toMember);
    memberRepository.update(toId, toMember.getMoney() + money);
  }

}
