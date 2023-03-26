package jp.falsystack.servlet.web.frontcontroller.v3.controller;

import java.util.Map;
import jp.falsystack.servlet.domain.member.Member;
import jp.falsystack.servlet.domain.member.MemberRepository;
import jp.falsystack.servlet.web.frontcontroller.ModelView;
import jp.falsystack.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {
    String username = paramMap.get("username");
    int age = Integer.parseInt(paramMap.get("age"));

    Member member = new Member();
    member.setUsername(username);
    member.setAge(age);

    memberRepository.save(member);

    ModelView mv = new ModelView("save-result");
    mv.getModel().put("member", member);
    return mv;
  }
}
