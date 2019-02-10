package net.homenet.configuration;

import net.homenet.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class HessianClientWeatherConfiguration {
    @Bean
    public HessianProxyFactoryBean weatherServiceProxy() {
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/weather");
        proxy.setServiceInterface(WeatherService.class);
        return proxy;
    }
}
