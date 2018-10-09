package net.homenet.s1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


@Aspect
@Component
public class CalculatorLoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* ArithmeticCalculator.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("The method {}() begins with {}"
            , joinPoint.getSignature().getName()
            , Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* ArithmeticCalculator.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("The method {}() ends", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* ArithmeticCalculator.*(..))"
        , returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("The method {}() ends with {}"
            , joinPoint.getSignature().getName()
            , result);
    }

    @AfterThrowing(pointcut = "execution(* ArithmeticCalculator.*(..))"
        , throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("An exception {} has been thrown in {}()"
            , e
            , joinPoint.getSignature().getName());
    }
}
