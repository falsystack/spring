package jp.falsystack.querydsl.repository;

import jp.falsystack.querydsl.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // JPQL : "select m from Member m where m.username = :username";
    // SQL  : "select * from member m where m.username = username"
    List<Member> findByUsername(String username);
}
