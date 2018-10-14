package net.homenet.s2;

import org.springframework.stereotype.Component;

@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    @LoggingRequired
    public double add(double x, double y) {
        double result = x + y;
        System.out.println(x + "+" + y + "=" + result);
        return result;
    }

    @Override
    @LoggingRequired
    public double sub(double x, double y) {
        double result = x - y;
        System.out.println(x + "-" + y + "=" + result);
        return result;
    }

    @Override
    @LoggingRequired
    public double mul(double x, double y) {
        double result = x * y;
        System.out.println(x + "*" + y + "=" + result);
        return result;
    }

    @Override
    @LoggingRequired
    public double div(double x, double y) {
        if (y == 0) {
            throw new IllegalArgumentException("Division by zero");
        }

        double result = x / y;
        System.out.println(x + "/" + y + "=" + result);
        return result;
    }
}
