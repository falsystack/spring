package jp.falsystack.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import jp.falsystack.jdbc.connection.ConnectionConst;
import jp.falsystack.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static jp.falsystack.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(USERNAME);
        dataSource.setJdbcUrl(URL);
        dataSource.setPassword(PASSWORD);

        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    @DisplayName("crud")
    void crud() throws SQLException {
        log.info("start");

        Member member = new Member("memberV0", 10000);
        repository.save(member);

        // findById
        Member findMember = repository.findById(member.getMemberId());
        assertThat(findMember).isNotNull();

        // update
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // delete
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId())).isInstanceOf(NoSuchElementException.class);
    }

}