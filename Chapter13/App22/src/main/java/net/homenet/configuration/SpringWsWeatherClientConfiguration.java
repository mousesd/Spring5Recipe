package net.homenet.configuration;

import net.homenet.WeatherService;
import net.homenet.WeatherServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SpringWsWeatherClientConfiguration {
    @Bean
    public Marshaller marshaller() {
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(new ClassPathResource("/mapping.xml"));
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Marshaller marshaller) {
        WebServiceTemplate wsTemplate = new WebServiceTemplate(marshaller);
        wsTemplate.setDefaultUri("http://localhost:8080/services");
        return wsTemplate;
    }

    @Bean
    public WeatherService weatherService(WebServiceTemplate wsTemplate) {
        return new WeatherServiceProxy(wsTemplate);
    }
}
