package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * 스프링 bean 으로 등록하여야 한다.
 * 외부 PointCut 참조
 */

@Slf4j
@Aspect
public class AspectV4Pointcut {

    @Around("hello.aop.order.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(" [log] {}" , joinPoint.getSignature());
        return joinPoint.proceed();
    }


    @Around("hello.aop.order.aop.Pointcuts.allOrderAndService()")
    private Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            log.info("[트랜잭션 시작]");
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋]");
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백]");
            throw e;
        } finally {
            log.info("[리소스 릴리즈]");
        }
    }
}
