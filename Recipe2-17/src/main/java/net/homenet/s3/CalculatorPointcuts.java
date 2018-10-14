package net.homenet.s3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {
    @Pointcut("within(net.homenet.s3.ArithmeticCalculator+)")
    public void loggingOperation() { }

    @Pointcut(value = "execution(* ArithmeticCalculator.*(..)) && target(target) && args(a,b)", argNames = "target,a,b")
    public void parameterPointcut(Object target, double a, double b) {}
}
