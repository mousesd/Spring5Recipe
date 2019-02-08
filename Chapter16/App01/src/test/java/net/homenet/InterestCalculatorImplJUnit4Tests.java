package net.homenet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InterestCalculatorImplJUnit4Tests {

    private InterestCalculator interestCalculator;

    @Before
    public void setUp() {
        interestCalculator = new InterestCalculatorImpl();
        interestCalculator.setRate(0.05);
    }

    @Test
    public void calculate() {
        double interest = interestCalculator.calculate(10000, 2);
        assertEquals(interest, 1000.0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCalculate() {
        interestCalculator.calculate(-10000, 2);
    }
}
