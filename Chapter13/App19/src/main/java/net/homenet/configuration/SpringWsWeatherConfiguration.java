package net.homenet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan("net.homenet")
public class SpringWsWeatherConfiguration {
    @Bean
    public XsdSchema getTemperatureSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/META-INF/xsd/temperature.xsd"));
    }

    @Bean
    public DefaultWsdl11Definition temperature(XsdSchema getTemperatureSchema) {
        DefaultWsdl11Definition temperatureWsdl = new DefaultWsdl11Definition();
        temperatureWsdl.setPortTypeName("Weather");
        temperatureWsdl.setLocationUri("/");
        temperatureWsdl.setSchema(getTemperatureSchema);
        return temperatureWsdl;
    }
}
