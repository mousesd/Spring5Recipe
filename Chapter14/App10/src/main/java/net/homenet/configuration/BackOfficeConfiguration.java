package net.homenet.configuration;

import net.homenet.BackOffice;
import net.homenet.BackOfficeImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

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
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, Destination destination) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setReceiveTimeout(10000);
        jmsTemplate.setDefaultDestination(destination);
        return jmsTemplate;
    }

    @Bean
    public BackOffice backOffice(JmsTemplate jmsTemplate) {
        BackOfficeImpl backOffice = new BackOfficeImpl();
        backOffice.setJmsTemplate(jmsTemplate);
        return backOffice;
    }
}
