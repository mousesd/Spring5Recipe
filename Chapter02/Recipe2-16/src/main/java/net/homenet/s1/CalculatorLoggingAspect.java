package net.homenet.s1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class CalculatorLoggingAspect implements Ordered {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public int getOrder() {
        return 1;
    }

    @Pointcut("execution(* ArithmeticCalculator.*(..))")
    private void loggingOperation() {}

    //@Before("CalculatorLoggingAspect.loggingOperation()")
    //public void logBefore(JoinPoint joinPoint) {
    //    log.info("The method {}() begins with {}"
    //        , joinPoint.getSignature().getName()
    //        , Arrays.toString(joinPoint.getArgs()));
    //}
    //
    //@After("CalculatorLoggingAspect.loggingOperation()")
    //public void logAfter(JoinPoint joinPoint) {
    //    log.info("The method {}() ends", joinPoint.getSignature().getName());
    //}
    //
    //@AfterReturning(pointcut = "CalculatorLoggingAspect.loggingOperation()"
    //    , returning = "result")
    //public void logAfterReturning(JoinPoint joinPoint, Object result) {
    //    log.info("The method {}() ends with {}", joinPoint.getSignature().getName(), result);
    //}
    //
    //@AfterThrowing(pointcut = "CalculatorLoggingAspect.loggingOperation()"
    //    , throwing = "e")
    //public void logAfterThrowing(JoinPoint joinPoint, IllegalArgumentException e) {
    //    log.error("Illegal argument {} in {}()"
    //        , Arrays.toString(joinPoint.getArgs())
    //        , joinPoint.getSignature().getName());
    //}

    @Around("CalculatorLoggingAspect.loggingOperation()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("The method {}() begins with {}"
            , joinPoint.getSignature().getName()
            , Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            log.info("The method {}() ends with {}", joinPoint.getSignature().getName(), result);
            return result;
        } catch (Throwable throwable) {
            log.error("Illegal argument {} in {}()"
                , Arrays.toString(joinPoint.getArgs())
                , joinPoint.getSignature().getName());
            throw throwable;
        }
    }
}
