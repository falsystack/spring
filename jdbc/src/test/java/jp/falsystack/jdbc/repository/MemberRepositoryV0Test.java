package jp.falsystack.jdbc.repository;

import jp.falsystack.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repsitory = new MemberRepositoryV0();

    @Test
    @DisplayName("crud")
    void crud() throws SQLException {
        // expected
        Member member = new Member("memberV0", 10000);
        repsitory.save(member);

        // findById
        Member findMember = repsitory.findById(member.getMemberId());
        log.info("findMember = {}, ", findMember);
        assertThat(findMember).isEqualTo(member);
    }

}