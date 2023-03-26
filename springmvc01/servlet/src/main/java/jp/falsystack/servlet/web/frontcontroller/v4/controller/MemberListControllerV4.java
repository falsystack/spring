package jp.falsystack.servlet.web.frontcontroller.v4.controller;

import java.util.List;
import java.util.Map;
import jp.falsystack.servlet.domain.member.Member;
import jp.falsystack.servlet.domain.member.MemberRepository;
import jp.falsystack.servlet.web.frontcontroller.v4.ControllerV4;

public class MemberListControllerV4 implements ControllerV4 {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public String process(Map<String, String> paramMap, Map<String, Object> model) {

    List<Member> members = memberRepository.findAll();
    model.put("members", members);

    return "members";
  }
}
