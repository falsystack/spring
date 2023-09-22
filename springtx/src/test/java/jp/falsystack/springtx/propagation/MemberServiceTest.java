package jp.falsystack.springtx.propagation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

@Slf4j
@SpringBootTest
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  LogRepository logRepository;

  /**
   * memberService     @Transactional: OFF
   * memberRepository  @Transactional: ON
   * logRepository     @Transactional: ON
   */
  @Test
  void outerTxOff_success() {
    var username = "outerTxOff_success";

    memberService.joinV1(username);

    assertThat(memberRepository.find(username).isPresent()).isTrue();
    assertThat(logRepository.find(username).isPresent()).isTrue();
  }

  /**
   * memberService     @Transactional: ON
   * memberRepository  @Transactional: ON
   * logRepository     @Transactional: Exception
   */
  @Test
  void outerTxOff_fail() {
    var username = "로그예외_outerTxOff_success";

    assertThatThrownBy(() -> memberService.joinV1(username))
        .isInstanceOf(RuntimeException.class);

    assertThat(memberRepository.find(username).isPresent()).isTrue();
    assertThat(logRepository.find(username).isEmpty()).isTrue();
  }

  /**
   * memberService     @Transactional: ON
   * memberRepository  @Transactional: OFF
   * logRepository     @Transactional: OFF
   */
  @Test
  void singleTx() {
    var username = "outerTxOff_success";

    memberService.joinV1(username);

    assertThat(memberRepository.find(username).isPresent()).isTrue();
    assertThat(logRepository.find(username).isPresent()).isTrue();
  }

  /**
   * memberService     @Transactional: ON
   * memberRepository  @Transactional: ON
   * logRepository     @Transactional: ON
   */
  @Test
  void outerTxOn_success() {
    var username = "outerTxOn_success";

    memberService.joinV1(username);

    assertThat(memberRepository.find(username).isPresent()).isTrue();
    assertThat(logRepository.find(username).isPresent()).isTrue();
  }

  /**
   * memberService     @Transactional: ON
   * memberRepository  @Transactional: ON
   * logRepository     @Transactional: ON Exception
   */
  @Test
  void outerTxOn_fail() {
    var username = "로그예외_outerTxOff_success";

    assertThatThrownBy(() -> memberService.joinV1(username))
        .isInstanceOf(RuntimeException.class);

    // 모든 데이터가 롤백된다.
    assertThat(memberRepository.find(username).isEmpty()).isTrue();
    assertThat(logRepository.find(username).isEmpty()).isTrue();
  }

  /**
   * memberService     @Transactional: ON
   * memberRepository  @Transactional: ON
   * logRepository     @Transactional: ON Exception
   */
  @Test
  void recoverException_fail() {
    var username = "로그예외_recoverException_fail";

    assertThatThrownBy(() -> memberService.joinV2(username))
        .isInstanceOf(UnexpectedRollbackException.class);

    assertThat(memberRepository.find(username).isPresent()).isTrue();
    assertThat(logRepository.find(username).isEmpty()).isTrue();
  }

  /**
   * memberService     @Transactional: ON
   * memberRepository  @Transactional: ON
   * logRepository     @Transactional: ON(REQUIRES_NEW) Exception
   */
  @Test
  void recoverException_success() {
    var username = "로그예외_recoverException_success";

    memberService.joinV2(username);

    assertThat(memberRepository.find(username).isPresent()).isTrue();
    assertThat(logRepository.find(username).isEmpty()).isTrue();
  }
}