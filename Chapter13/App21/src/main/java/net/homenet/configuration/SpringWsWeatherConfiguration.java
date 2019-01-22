package net.homenet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@SuppressWarnings("Duplicates")
@Configuration
@EnableWs
@ComponentScan("net.homenet")
public class SpringWsWeatherConfiguration extends WsConfigurerAdapter {
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

    @Bean
    public Marshaller marshaller() {
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(new ClassPathResource("/mapping.xml"));
        return marshaller;
    }

    @Bean
    public MarshallingPayloadMethodProcessor marshallingPayloadMethodProcessor() {
        return new MarshallingPayloadMethodProcessor(marshaller());
    }

    @Override
    public void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(marshallingPayloadMethodProcessor());
    }

    @Override
    public void addReturnValueHandlers(List<MethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(marshallingPayloadMethodProcessor());
    }
}
