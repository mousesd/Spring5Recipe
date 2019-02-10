package net.homenet.configuration;

import net.homenet.WeatherService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class WeatherClientConfiguration {
    @Bean
    public WeatherService weatherService() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WeatherService.class);
        factory.setAddress("http://localhost:8080/weather");
        return (WeatherService) factory.create();
    }
}
