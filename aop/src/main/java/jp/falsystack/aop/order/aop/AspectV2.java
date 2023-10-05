package jp.falsystack.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

  // jp.falsystack.aop.order 패키지와 하위 패키지 모두
  @Pointcut("execution(* jp.falsystack.aop.order..*(..))")
  private void allorder() {} // point cut signature

  @Around("allorder()")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("[log] {}", joinPoint.getSignature()); // join point signature
    return joinPoint.proceed();
  }
}
