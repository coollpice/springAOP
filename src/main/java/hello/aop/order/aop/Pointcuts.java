package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP pointcut 들을 공용화해서 쓰기위해 외부로 추출
 */
public class Pointcuts {

    /**
     * hello.aop.order 패키지와 하위 패키지
     */
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){} // 포인트컷 시그니쳐

    // 클래스 이름 패턴이 *Service 인 경우
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void allOrderAndService(){}

}
