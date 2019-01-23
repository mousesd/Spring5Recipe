package net.homenet.configuration;

import net.homenet.BackOffice;
import net.homenet.BackOfficeImpl;
import net.homenet.MailMessageConverter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@SuppressWarnings("Duplicates")
@Configuration
public class BackOfficeConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://192.168.222.128:61616");
    }

    @Bean
    public Destination destination() {
        return new ActiveMQQueue("mail.queue");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MailMessageConverter();
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, Destination destination, MessageConverter messageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setReceiveTimeout(10000);
        jmsTemplate.setDefaultDestination(destination);
        jmsTemplate.setMessageConverter(messageConverter);
        return jmsTemplate;
    }

    @Bean
    public BackOffice backOffice(JmsTemplate jmsTemplate) {
        BackOfficeImpl backOffice = new BackOfficeImpl();
        backOffice.setJmsTemplate(jmsTemplate);
        return backOffice;
    }
}
