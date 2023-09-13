package jp.falsystack.jdbc.repository;

import jp.falsystack.jdbc.domain.Member;

public interface MemberRepository {

  Member save(Member member);

  Member findById(String memberId);

  void update(String memberId, int money);

  void delete(String memberId);
}
