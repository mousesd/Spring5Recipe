package net.homenet.configuration;

import net.homenet.service.WeatherService;
import net.homenet.service.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class WeatherConfiguration {
    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }

    @Bean
    public RmiServiceExporter rmiServiceExporter(WeatherService weatherService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("Weather Service");
        rmiServiceExporter.setServiceInterface(WeatherService.class);
        rmiServiceExporter.setService(weatherService);
        return rmiServiceExporter;
    }
}
