package net.homenet;

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
}
