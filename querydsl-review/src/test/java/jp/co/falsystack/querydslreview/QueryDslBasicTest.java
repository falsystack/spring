package jp.co.falsystack.querydslreview;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jp.co.falsystack.querydslreview.entity.Member;
import jp.co.falsystack.querydslreview.entity.QMember;
import jp.co.falsystack.querydslreview.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static jp.co.falsystack.querydslreview.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

    @PersistenceContext
    EntityManager em;

    private JPAQueryFactory query;

    @BeforeEach
    void setUp() {
        query = new JPAQueryFactory(em);

        var teamA = new Team("teamA");
        var teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        var member1 = new Member("member1", 10, teamA);
        var member2 = new Member("member2", 20, teamA);

        var member3 = new Member("member3", 30, teamB);
        var member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    void startJPQL() {
        // member1 을 찾아라
        var qlString = "select m from Member m where m.username = :username";
        var findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    void startQueryDSL() {
        var m1 = new QMember("m1");
        var findMember = query
                .selectFrom(m1)
                .where(m1.username.eq("member1"))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
}
