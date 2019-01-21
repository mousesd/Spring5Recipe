package net.homenet.configuration;

import net.homenet.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class WeatherClientConfiguration {
    @Bean
    public JaxWsPortProxyFactoryBean weatherService() throws MalformedURLException {
        JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
        proxy.setServiceInterface(WeatherService.class);
        proxy.setWsdlDocumentUrl(new URL("http://localhost:8080/weather?WSDL"));
        proxy.setNamespaceUri("http://homenet.net/");
        proxy.setServiceName("weather");
        proxy.setPortName("WeatherServiceImplPort");
        return proxy;
    }
}
