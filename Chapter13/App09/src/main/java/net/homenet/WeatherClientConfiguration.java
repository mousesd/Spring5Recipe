package net.homenet;

import net.homenet.service.WeatherService;
import net.homenet.service.WeatherServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class WeatherClientConfiguration {
    @Bean
    public RmiProxyFactoryBean weatherServiceProxy() {
        RmiProxyFactoryBean weatherServiceProxy = new RmiProxyFactoryBean();
        weatherServiceProxy.setServiceUrl("rmi://localhost:1099/WeatherService");
        weatherServiceProxy.setServiceInterface(WeatherService.class);
        return weatherServiceProxy;
    }

    @Bean
    public WeatherServiceClient weatherServiceClient(WeatherService weatherService) {
        return new WeatherServiceClient(weatherService);
    }
}
