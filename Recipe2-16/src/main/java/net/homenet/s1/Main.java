package net.homenet.s1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings({"Duplicates", "WeakerAccess"})
public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ArithmeticCalculator arithmeticCalculator = context.getBean(ArithmeticCalculator.class);
        arithmeticCalculator.add(1, 2);
        arithmeticCalculator.sub(4, 3);
        arithmeticCalculator.mul(2, 3);
        arithmeticCalculator.div(4, 0);

        //UnitCalculator unitCalculator = context.getBean(UnitCalculator.class);
        //unitCalculator.kilogramToPound(10);
        //unitCalculator.kilometerToMile(5);
    }
}
