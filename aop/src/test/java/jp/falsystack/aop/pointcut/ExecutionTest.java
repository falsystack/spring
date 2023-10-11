package jp.falsystack.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import jp.falsystack.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method helloMethod;

  @BeforeEach
  void init() throws NoSuchMethodException {
    helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
  }

  @Test
  void printMethod() {
    log.info("helloMethod={}", helloMethod);
  }

  @Test
  void exactMatch() {
    // public java.lang.String jp.falsystack.aop.member.MemberServiceImpl.hello(java.lang.String)
    pointcut.setExpression(
        "execution(public String jp.falsystack.aop.member.MemberServiceImpl.hello(String))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void allMatch() {
    pointcut.setExpression("execution(* *(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatch() {
    pointcut.setExpression("execution(* hello(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchStar1() {
    pointcut.setExpression("execution(* hel*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchStar2() {
    pointcut.setExpression("execution(* *el*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchFalse() {
    pointcut.setExpression("execution(* nono*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void packageExactMatch1() {
    pointcut.setExpression("execution(* jp.falsystack.aop.member.MemberServiceImpl.hello(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactMatch2() {
    pointcut.setExpression("execution(* jp.falsystack.aop.member.*.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactFalse() {
    pointcut.setExpression("execution(* jp.falsystack.aop.*.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void packageMatchSubPackage1() {
    pointcut.setExpression("execution(* jp.falsystack.aop..*.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeExactMatch() {
    pointcut.setExpression("execution(* jp.falsystack.aop.member.MemberServiceImpl.*(..)))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeMatchSuperType() {
    pointcut.setExpression("execution(* jp.falsystack.aop.member.MemberService.*(..)))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void typeMatchInternal() throws NoSuchMethodException {
    pointcut.setExpression("execution(* jp.falsystack.aop.member.MemberService.*(..)))");

    Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void argsMatch() {
    pointcut.setExpression("execution(* *(String)))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void argsMatchNoArgs() {
    pointcut.setExpression("execution(* *()))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void argsMatchStar() {
    pointcut.setExpression("execution(* *(*)))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void argsMatchAll() {
    pointcut.setExpression("execution(* *(..)))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void argsMatchComplex() {
    pointcut.setExpression("execution(* *(String, ..)))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }


}
