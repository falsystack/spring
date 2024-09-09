package jp.co.falsystack.springtxreview.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
     * memberService        @Transactional: OFF
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON
     */
    @Test
    void outerTxOff_success() {
        // given
        var username = "outerTxOff_success";

        // when
        memberService.joinV1(username);

        // then
        assertThat(memberRepository.find(username).isPresent()).isTrue();
        assertThat(logRepository.find(username).isPresent()).isTrue();
    }

    /**
     * memberService        @Transactional: OFF
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON, Exception
     */
    @Test
    void outerTxOff_fail() {
        // given
        var username = "로그예외_outerTxOff_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        // then
        assertThat(memberRepository.find(username).isPresent()).isEqualTo(true);
        assertThat(logRepository.find(username).isPresent()).isEqualTo(false);
    }

    /**
     * memberService        @Transactional: ON
     * memberRepository     @Transactional: OFF
     * logRepository        @Transactional: OFF
     */
    @Test
    void singleTx() {
        // given
        var username = "singleTx";

        // when
        memberService.joinV1(username);

        // then
        assertThat(memberRepository.find(username).isPresent()).isTrue();
        assertThat(logRepository.find(username).isPresent()).isTrue();
    }

    /**
     * memberService        @Transactional: ON
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON
     */
    @Test
    void outerTxOn() {
        // given
        var username = "outerTxOn";

        // when
        memberService.joinV1(username);

        // then
        assertThat(memberRepository.find(username).isPresent()).isTrue();
        assertThat(logRepository.find(username).isPresent()).isTrue();
    }

    /**
     * memberService        @Transactional: ON
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON, Exception
     */
    @Test
    void outerTxOn_fail() {
        // given
        var username = "로그예외_outerTxOn_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        // then
        assertThat(memberRepository.find(username).isEmpty()).isEqualTo(true);
        assertThat(logRepository.find(username).isEmpty()).isEqualTo(true);
    }

    /**
     * memberService        @Transactional: ON
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON, Exception
     */
    @Test
    void recoverException_fail() {
        // given
        var username = "로그예외_recoverException_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV2(username)).isInstanceOf(RuntimeException.class);

        // then
        assertThat(memberRepository.find(username).isEmpty()).isEqualTo(true);
        assertThat(logRepository.find(username).isEmpty()).isEqualTo(true);
    }

    /**
     * memberService        @Transactional: ON
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON(REQUIRES_NEW), Exception
     */
    @Test
    void recoverException_success() {
        // given
        var username = "로그예외_recoverException_success";

        // when
        memberService.joinV2(username);

        // then
        assertThat(memberRepository.find(username).isPresent()).isEqualTo(true);
        assertThat(logRepository.find(username).isEmpty()).isEqualTo(true);
    }
}