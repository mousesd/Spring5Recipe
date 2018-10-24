package net.homenet;

public class MaxCalculatorImpl implements MaxCalculator {
    @Override
    public double max(double x, double y) {
        double result = x >= y ? x : y;
        System.out.println(String.format("max(%s, %s)=%s", x, y, result));
        return result;
    }
}
