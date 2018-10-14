package net.homenet.s2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order
public class CalculatorValidationAspect {
    @Before("execution(* ArithmeticCalculator.*(..))")
    public void validateBefore(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            validate((Double) arg);
        }
    }

    private void validate(Double arg) {
        if (arg < 0) {
            throw new IllegalArgumentException("Positive numbers only");
        }
    }
}
