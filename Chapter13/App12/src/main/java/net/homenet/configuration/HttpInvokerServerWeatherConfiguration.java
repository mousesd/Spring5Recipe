package net.homenet.configuration;

import net.homenet.WeatherService;
import net.homenet.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class HttpInvokerServerWeatherConfiguration {
    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }

    @Bean(name = "/weather")
    public HttpInvokerServiceExporter weatherServiceExporter(WeatherService weatherService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(weatherService);
        exporter.setServiceInterface(WeatherService.class);
        return exporter;
    }
}
