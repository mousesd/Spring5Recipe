package net.homenet.configuration;

import net.homenet.MailListener;
import net.homenet.MailMessageConverter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@SuppressWarnings("Duplicates")
@Configuration
@EnableJms
public class BackOfficeConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.222.128:61616");
        PooledConnectionFactory connectionFactory = new PooledConnectionFactory();
        connectionFactory.setConnectionFactory(mqConnectionFactory);
        return connectionFactory;
    }

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

    @Bean
    public MailMessageConverter mailMessageConverter() {
        return new MailMessageConverter();
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory
        , MailMessageConverter mailMessageConverter) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(mailMessageConverter);
        factory.setSessionTransacted(true);
        return factory;
    }
}
