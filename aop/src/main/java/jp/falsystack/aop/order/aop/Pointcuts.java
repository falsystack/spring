package jp.falsystack.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

  // jp.falsystack.aop.order 패키지와 하위 패키지 모두
  @Pointcut("execution(* jp.falsystack.aop.order..*(..))")
  public void allorder() {} // point cut signature

  // 클래스 이름 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  public void allService() {}

  // allOrder && allService
  @Pointcut("allorder() && allService()")
  public void orderAndService() {}

}
