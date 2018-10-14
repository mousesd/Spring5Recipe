package net.homenet.s2;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {
    @Pointcut("within(net.homenet.s2.ArithmeticCalculator+)")
    public void loggingOperation() { }
}
