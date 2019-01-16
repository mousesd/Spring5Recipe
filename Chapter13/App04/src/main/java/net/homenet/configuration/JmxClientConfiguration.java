package net.homenet.configuration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import javax.management.MBeanServerConnection;
import java.net.MalformedURLException;

@Configuration
public class JmxClientConfiguration {
    @Bean
    public FactoryBean<MBeanServerConnection> mBeanServerConnection() throws MalformedURLException {
        MBeanServerConnectionFactoryBean factoryBean = new MBeanServerConnectionFactoryBean();
        factoryBean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator");
        return factoryBean;
    }
}
