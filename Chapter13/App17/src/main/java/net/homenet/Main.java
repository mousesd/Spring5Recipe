package net.homenet;

import net.homenet.configuration.WeatherClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WeatherClientConfiguration.class);
        WeatherService service = context.getBean(WeatherService.class);
        service.getTemperators("Seoul", Collections.singletonList(new Date()))
            .forEach(System.out::println);
    }
}
