package net.homenet.configuration;

import net.homenet.WeatherService;
import net.homenet.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class HessianServerWeatherConfiguration {
    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }

    @Bean(name = "/weather")
    public HessianServiceExporter hessianServiceExporter(WeatherService weatherService) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(weatherService);
        exporter.setServiceInterface(WeatherService.class);
        return exporter;
    }
}
