package jp.falsystack.jdbcstudy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import javax.sql.DataSource;
import jp.falsystack.jdbcstudy.domain.Member;
import jp.falsystack.jdbcstudy.repository.MemberRepository;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV3;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV4_1;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV4_2;
import jp.falsystack.jdbcstudy.repository.MemberRepositoryV5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 예외 누수 문제 해결
 * SQLException 제거
 *
 * MemberRepository 인터페이스에 의존
 */
@Slf4j
@SpringBootTest
class MemberServiceV4Test {

  public static final String MEMBER_A = "memberA";
  public static final String MEMBER_B = "memberB";
  public static final String MEMBER_EX = "ex";

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private MemberServiceV4 memberService;

  @AfterEach
  void tearDown() {
    memberRepository.delete(MEMBER_A);
    memberRepository.delete(MEMBER_B);
    memberRepository.delete(MEMBER_EX);
  }

  @Test
  void aopCheck() {
    log.info("memberService class = {}", memberService.getClass());
    log.info("memberRepository class = {}", memberRepository.getClass());
  }

  @Test
  @DisplayName("정상 이체")
  void accountTransfer() {
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
  void accountTransferEx() {
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

  @TestConfiguration
  @RequiredArgsConstructor
  static class TestConfig {

    private final DataSource dataSource;

    @Bean
    MemberRepository memberRepositoryV4() {
      return new MemberRepositoryV5(dataSource);
    }

    @Bean
    MemberServiceV4 memberServiceV4() {
      return new MemberServiceV4(memberRepositoryV4());
    }
  }
}