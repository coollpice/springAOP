package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 스프링 bean 으로 등록하여야 한다.
 * 외부 PointCut 참조
 * 순서를 보장 받기 위해서 @Order 사용 - Aspect 단위로 동작하기 떄문에 클래스 분리
 */

@Slf4j
public class AspectV5Order {

    @Order(1)
    @Aspect
    public static class LogAspect{
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info(" [log] {}" , joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Order(2)
    @Aspect
    public static class TxAspect {
        // &&, ||, ! 사용가능
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

}
