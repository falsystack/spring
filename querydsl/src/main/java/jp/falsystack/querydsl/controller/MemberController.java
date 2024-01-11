package jp.falsystack.querydsl.controller;

import jp.falsystack.querydsl.dto.MemberSearchCond;
import jp.falsystack.querydsl.dto.MemberTeamDto;
import jp.falsystack.querydsl.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberTeamV1(MemberSearchCond cond) {
        return memberJpaRepository.search(cond);
    }
}
