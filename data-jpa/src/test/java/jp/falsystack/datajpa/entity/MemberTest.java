package jp.falsystack.datajpa.entity;

import jakarta.persistence.EntityManager;
import jp.falsystack.datajpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamA);
        Member member3 = new Member("member3", 10, teamB);
        Member member4 = new Member("member4", 10, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // 초기화
        em.flush();
        em.clear();

        // 확인
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("member.getTeam() = " + member.getTeam());
        }
    }

    @Test
    void jpaEventBaseEntity() throws InterruptedException {
        // given
        Member member1 = new Member("member1");
        memberRepository.save(member1); // @PrePersist 발생

        Thread.sleep(1000);
        member1.setUsername("member2");

        em.flush(); // @PreUpdate 발생
        em.clear();

        // when
        Member findMember = memberRepository.findById(member1.getId()).get();
        // then
        System.out.println("getCreatedDate = " + findMember.getCreatedDate());
        System.out.println("getUpdatedDate = " + findMember.getLastModifiedDate());
        System.out.println("createdBy = " + findMember.getCreatedBy());
        System.out.println("modifiedBy = " + findMember.getLastModifiedBy());
    }
}