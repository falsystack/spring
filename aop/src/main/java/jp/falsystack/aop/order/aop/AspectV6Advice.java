package jp.falsystack.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

//  @Around("jp.falsystack.aop.order.aop.Pointcuts.orderAndService()")
//  public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
//    try {
//      // @Before
//      log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//      Object result = joinPoint.proceed();
//      // @AfterReturning
//      log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//      return result;
//    } catch (Exception e) {
//      // @AfterThrowing
//      log.info("[트랜잭션 롤밸] {}", joinPoint.getSignature());
//      throw e;
//    } finally {
//      // @After
//      log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
//    }
//  }

  @Before("jp.falsystack.aop.order.aop.Pointcuts.orderAndService()")
  public void doBefore(JoinPoint joinPoint) {
    log.info("[before] {}", joinPoint.getSignature());
    // join point 실행 직전까지의 로직만 작성해주면 join point는 알아서 실행해준다. ,joinPoint.proceed();
  }

  @AfterReturning(value = "jp.falsystack.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
  public void doReturn(JoinPoint joinPoint, Object result) {
    log.info("[return] {} return = {}", joinPoint.getSignature(), result);
    // 리턴할 수가 없다.
  }

  @AfterThrowing(value = "jp.falsystack.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
  public void doThrowing(JoinPoint joinPoint, Exception ex) {
    log.info("[ex] = {},  message = {}", joinPoint.getSignature(), ex.getMessage());
  }

  @After("jp.falsystack.aop.order.aop.Pointcuts.orderAndService()")
  public void doAfter(JoinPoint joinPoint) {
    log.info("[after] {}", joinPoint.getSignature());
  }
}
