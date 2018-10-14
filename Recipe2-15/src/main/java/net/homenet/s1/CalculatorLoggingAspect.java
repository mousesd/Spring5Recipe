package net.homenet.s1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class CalculatorLoggingAspect implements Ordered {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* ArithmeticCalculator.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("The method {}() begins with {}"
            , joinPoint.getSignature().getName()
            , Arrays.toString(joinPoint.getArgs()));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
