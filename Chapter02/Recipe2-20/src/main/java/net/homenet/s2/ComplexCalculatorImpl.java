package net.homenet.s2;

import org.springframework.stereotype.Component;

@Component("complexCalculator")
public class ComplexCalculatorImpl implements ComplexCalculator {
    @Override
    public Complex add(Complex x, Complex y) {
        Complex result = new Complex(x.getReal() + y.getReal(), x.getImaginary() + y.getImaginary());
        System.out.println(String.format("%s+%s=%s", x, y, result));
        return result;
    }

    @Override
    public Complex sub(Complex x, Complex y) {
        Complex result = new Complex(x.getReal() - y.getReal(), x.getImaginary() - y.getImaginary());
        System.out.println(String.format("%s-%s=%s", x, y, result));
        return result;
    }
}
