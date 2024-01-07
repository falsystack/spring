package jp.falsystack.datajpa.repository;

import jp.falsystack.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
