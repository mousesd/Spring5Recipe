package net.homenet.s1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {
    @Pointcut("@annotation(net.homenet.s1.LoggingRequired)")
    public void loggingOperation() { }
}
