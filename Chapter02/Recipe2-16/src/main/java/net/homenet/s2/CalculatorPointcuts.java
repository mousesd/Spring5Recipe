package net.homenet.s2;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {
    @Pointcut("execution(* ArithmeticCalculator.*(..))")
    public void loggingOperation() { }
}
