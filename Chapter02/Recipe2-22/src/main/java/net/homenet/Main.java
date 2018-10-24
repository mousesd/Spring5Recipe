package net.homenet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(ComplexCalculatorConfiguration.class);

        ComplexCalculator calculator = context.getBean(ComplexCalculatorImpl.class);
        calculator.add(new Complex(1, 2), new Complex(2, 3));
        calculator.sub(new Complex(5, 8), new Complex(2, 3));
    }
}
