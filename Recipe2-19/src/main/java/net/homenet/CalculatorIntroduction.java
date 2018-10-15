package net.homenet;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {
    @DeclareParents(value = "net.homenet.ArithmeticCalculatorImpl"
        , defaultImpl = MaxCalculatorImpl.class)
    public MaxCalculator maxCalculator;

    @DeclareParents(value = "net.homenet.ArithmeticCalculatorImpl"
        , defaultImpl = MinCalculatorImpl.class)
    public MinCalculator minCalculator;

    @DeclareParents(value = "net.homenet.*CalculatorImpl"
        , defaultImpl = CounterImpl.class)
    public Counter counter;

    @After(value = "execution(* net.homenet.*Calculator.*(..)) && this(counter)"
        , argNames = "counter")
    public void increaseCount(Counter counter) {
        counter.increase();
    }
}
