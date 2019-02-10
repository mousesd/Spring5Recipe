package net.homenet;

import net.homenet.configuration.HttpInvokerClientWeatherConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HttpInvokerClient {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HttpInvokerClientWeatherConfiguration.class);
        WeatherService weatherService = context.getBean(WeatherService.class);

        List<TemperatureInfo> temperatures = weatherService.getTemperators("Seoul", Collections.singletonList(new Date()));
        for (TemperatureInfo temperature : temperatures) {
            System.out.println(temperature);
        }
    }
}
