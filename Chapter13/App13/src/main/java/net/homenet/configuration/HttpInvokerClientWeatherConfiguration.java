package net.homenet.configuration;

import net.homenet.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class HttpInvokerClientWeatherConfiguration {
    @Bean
    public HttpInvokerProxyFactoryBean weatherServiceProxy() {
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/weather");
        proxy.setServiceInterface(WeatherService.class);
        return proxy;
    }
}
