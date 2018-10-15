package net.homenet;

public class MinCalculatorImpl implements MinCalculator {
    @Override
    public double min(double x, double y) {
        double result = x <= y ? x : y;
        System.out.println(String.format("min(%s, %s)=%s", x, y, result));
        return result;
    }
}
