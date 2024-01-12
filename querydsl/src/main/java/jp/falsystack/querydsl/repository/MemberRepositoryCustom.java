package jp.falsystack.querydsl.repository;

import jp.falsystack.querydsl.dto.MemberSearchCond;
import jp.falsystack.querydsl.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCond condition);

    Page<MemberTeamDto> searchPageComplex(MemberSearchCond cond, Pageable pageable);
}
