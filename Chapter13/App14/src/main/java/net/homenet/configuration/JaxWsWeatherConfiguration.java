package net.homenet.configuration;

import net.homenet.WeatherService;
import net.homenet.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleHttpServerJaxWsServiceExporter;

@Configuration
public class JaxWsWeatherConfiguration {
    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }

    @Bean
    public SimpleHttpServerJaxWsServiceExporter weatherServiceExporter() {
        SimpleHttpServerJaxWsServiceExporter exporter = new SimpleHttpServerJaxWsServiceExporter();
        exporter.setPort(8080);
        exporter.setBasePath("/jaxws/");
        return exporter;
    }
}
