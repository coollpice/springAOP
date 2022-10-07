package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.order.aop.Pointcuts.allOrderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            //@Before
            log.info("[트랜잭션 시작]");
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜잭션 커밋]");
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백]");
            throw e;
        } finally {
            //@After
            log.info("[리소스 릴리즈]");
        }
    }

    // 조인포인트가 실행 
    @Before("hello.aop.order.aop.Pointcuts.allOrderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    // 조인포인트가 정상실행된 경우 실행
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning] {} return = {}", joinPoint.getSignature(), result);
    }

    // 메서드가 예외를 던지는 경우 실행
    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.allOrderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[AfterThrowing] {} return = {}", joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "hello.aop.order.aop.Pointcuts.allOrderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }
}
