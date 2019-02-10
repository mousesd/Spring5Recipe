package net.homenet.configuration;

import net.homenet.WeatherService;
import net.homenet.WeatherServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class WeatherConfiguration {
    @Bean
    public WeatherService weatherService() {
        return new WeatherServiceImpl();
    }

    @Bean(initMethod = "publish")
    public EndpointImpl endpoint(Bus bus, WeatherService weatherService) {
        EndpointImpl endpoint = new EndpointImpl(bus, weatherService);
        endpoint.setAddress("/weather");
        return endpoint;
    }
}
