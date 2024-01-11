package jp.falsystack.querydsl.repository;

import jakarta.persistence.EntityManager;
import jp.falsystack.querydsl.dto.MemberSearchCond;
import jp.falsystack.querydsl.dto.MemberTeamDto;
import jp.falsystack.querydsl.entity.Member;
import jp.falsystack.querydsl.entity.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> members = memberJpaRepository.findAll();
        assertThat(members).containsExactly(member);

        List<Member> findMembers = memberJpaRepository.findByUsername("member1");
        assertThat(findMembers).containsExactly(member);

    }

    @Test
    void search() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCond searchCond = new MemberSearchCond();
        searchCond.setAgeGoe(35);
        searchCond.setAgeLoe(40);
        searchCond.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.search(searchCond);
        assertThat(result).extracting("username").containsExactly("member4");
    }

}