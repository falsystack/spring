package jp.falsystack.querydsl.repository;

import jp.falsystack.querydsl.dto.MemberSearchCond;
import jp.falsystack.querydsl.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void basicTest() {
        Member member = new Member("member1", 10);
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> members = memberRepository.findAll();
        assertThat(members).containsExactly(member);

        List<Member> findMembers = memberRepository.findByUsername("member1");
        assertThat(findMembers).containsExactly(member);
    }

}