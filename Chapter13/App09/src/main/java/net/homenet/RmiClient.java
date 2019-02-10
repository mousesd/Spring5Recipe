package net.homenet;

import net.homenet.domain.TemperatureInfo;
import net.homenet.service.WeatherServiceClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RmiClient {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WeatherClientConfiguration.class);
        WeatherServiceClient client = context.getBean(WeatherServiceClient.class);

        TemperatureInfo temperature = client.getTodayTemperature("Seoul");
        System.out.println("Min temperature: " + temperature.getMin());
        System.out.println("Max temperature: " + temperature.getMax());
        System.out.println("Average temperature: " + temperature.getAverage());
    }
}
