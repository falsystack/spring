package jp.falsystack.aop.member;

import jp.falsystack.aop.member.annotation.ClassAop;
import jp.falsystack.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

  @Override
  @MethodAop("test value")
  public String hello(String param) {
    return null;
  }

  public String internal(String param) {
    return "ok";
  }
}
