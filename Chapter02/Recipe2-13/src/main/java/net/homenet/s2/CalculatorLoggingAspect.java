package net.homenet.s2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class CalculatorLoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* ArithmeticCalculator.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("The method {}() begins with {}"
            , joinPoint.getSignature().getName()
            , Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            log.info("The method {}() ends with {}"
                , joinPoint.getSignature().getName()
                , result);
            return result;
        } catch (Throwable e) {
            log.error("An exception {} has been thrown in {}()"
                , e
                , joinPoint.getSignature().getName());
            throw e;
        }
    }
}
