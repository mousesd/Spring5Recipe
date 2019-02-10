package net.homenet.configuration;

import net.homenet.WeatherServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SpringWsWeatherClientConfiguration {
    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate wsTemplate = new WebServiceTemplate();
        wsTemplate.setDefaultUri("http://localhost:8080/services");
        return wsTemplate;
    }

    @Bean
    public WeatherServiceProxy weatherServiceProxy(WebServiceTemplate wsTemplate) {
        return new WeatherServiceProxy(wsTemplate);
    }
}
