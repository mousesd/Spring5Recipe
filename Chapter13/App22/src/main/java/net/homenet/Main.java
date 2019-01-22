package net.homenet;

import net.homenet.configuration.SpringWsWeatherClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringWsWeatherClientConfiguration.class);
        WeatherService weatherService = context.getBean(WeatherService.class);
        weatherService.getTemperators("Seoul", Collections.singletonList(new Date()))
            .forEach(System.out::print);
    }
}
