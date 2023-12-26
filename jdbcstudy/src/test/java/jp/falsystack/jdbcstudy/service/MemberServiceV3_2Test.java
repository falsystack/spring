package jp.falsystack.jdbcstudy.service;

import static jp.falsystack.jdbcstudy.connection.ConnectionConst.PASSWORD;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.URL;
import static jp.falsystack.jdbcstudy.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import jp.falsystack.jdbcstudy.domain.Member;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 트랜잭션 - 트랜잭션 탬플릿
 */
@Slf4j
class MemberServiceV3_2Test {

  public static final String MEMBER_A = "memberA";
  public static final String MEMBER_B = "memberB";
  public static final String MEMBER_EX = "ex";

  private MemberRepositoryV3 memberRepository;
  private MemberServiceV3_2 memberService;

  @BeforeEach
  void setUp() {
    var dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    memberRepository = new MemberRepositoryV3(dataSource);
    var transactionManager = new DataSourceTransactionManager(dataSource);
    memberService = new MemberServiceV3_2(transactionManager, memberRepository);
  }

  @AfterEach
  void tearDown() throws SQLException {
    memberRepository.delete(MEMBER_A);
    memberRepository.delete(MEMBER_B);
    memberRepository.delete(MEMBER_EX);
  }

  @Test
  @DisplayName("정상 이체")
  void accountTransfer() throws SQLException {
    // given
    var memberA = new Member(MEMBER_A, 10000);
    var memberB = new Member(MEMBER_B, 10000);
    memberRepository.save(memberA);
    memberRepository.save(memberB);

    // when
    log.info("START TX");
    memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);
    log.info("END TX");

    // then
    var findMemberA = memberRepository.findById(memberA.getMemberId());
    var findMemberB = memberRepository.findById(memberB.getMemberId());
    assertThat(findMemberA.getMoney()).isEqualTo(8000);
    assertThat(findMemberB.getMoney()).isEqualTo(12000);

  }

  @Test
  @DisplayName("이체중 예외 발생")
  void accountTransferEx() throws SQLException {
    // given
    var memberA = new Member(MEMBER_A, 10000);
    var memberEx = new Member(MEMBER_EX, 10000);
    memberRepository.save(memberA);
    memberRepository.save(memberEx);

    // expected
    assertThatThrownBy(
        () -> memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(),
            2000)).isInstanceOf(
        IllegalStateException.class);

    var findMemberA = memberRepository.findById(memberA.getMemberId());
    var findMemberB = memberRepository.findById(memberEx.getMemberId());
    assertThat(findMemberA.getMoney()).isEqualTo(10000);
    assertThat(findMemberB.getMoney()).isEqualTo(10000);

  }
}