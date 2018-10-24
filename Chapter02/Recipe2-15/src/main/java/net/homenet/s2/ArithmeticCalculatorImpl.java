package net.homenet.s2;

import org.springframework.stereotype.Component;

@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public double add(double x, double y) {
        double result = x + y;
        System.out.println(x + "+" + y + "=" + result);
        return result;
    }

    @Override
    public double sub(double x, double y) {
        double result = x - y;
        System.out.println(x + "-" + y + "=" + result);
        return result;
    }

    @Override
    public double mul(double x, double y) {
        double result = x * y;
        System.out.println(x + "*" + y + "=" + result);
        return result;
    }

    @Override
    public double div(double x, double y) {
        double result = x / y;
        System.out.println(x + "/" + y + "=" + result);
        return result;
    }
}
