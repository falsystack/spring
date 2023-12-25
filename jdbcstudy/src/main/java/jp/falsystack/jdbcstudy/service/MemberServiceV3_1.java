package jp.falsystack.jdbcstudy.service;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import jp.falsystack.jdbcstudy.domain.Member;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV2;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 트랜잭션 - 트랜잭션 매니저
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {

  //  private final DataSource dataSource;
  private final PlatformTransactionManager transactionManager;
  private final MemberRepositoryV3 memberRepository;

  public void accountTransfer(String fromId, String toId, int money) throws SQLException {
    // 트랜잭션 시작
    var status = transactionManager.getTransaction(new DefaultTransactionDefinition());

    try {
      // 비즈니스 로직
      bizLogic(fromId, toId, money);
      // 성공시 커밋
      transactionManager.commit(status);
    } catch (Exception e) {
      // 실패시 롤백
      transactionManager.rollback(status);
      throw new IllegalStateException(e);
    }
  }

  private void bizLogic(String fromId, String toId, int money)
      throws SQLException {
    var fromMember = memberRepository.findById(fromId);
    var toMember = memberRepository.findById(toId);

    memberRepository.update(fromId, fromMember.getMoney() - money);
    validation(toMember);
    memberRepository.update(toId, toMember.getMoney() + money);
  }

  private static void release(Connection conn) {
    if (conn != null) {
      try {
        conn.setAutoCommit(true); // 커넥션 풀 고려
        conn.close();
      } catch (Exception e) {
        log.info("error", e);
      }
    }
  }

  private static void validation(Member toMember) {
    if (toMember.getMemberId().equals("ex")) {
      throw new IllegalStateException("이체중 예외 발생");
    }
  }

}
