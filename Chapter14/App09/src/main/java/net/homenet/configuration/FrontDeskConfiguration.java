package net.homenet.configuration;

import net.homenet.FrontDesk;
import net.homenet.FrontDeskImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Configuration
public class FrontDeskConfiguration {
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
        jmsTemplate.setDefaultDestination(destination);
        return jmsTemplate;
    }

    @Bean
    public FrontDesk frontDesk(JmsTemplate jmsTemplate) {
        FrontDeskImpl frontDesk = new FrontDeskImpl();
        frontDesk.setJmsTemplate(jmsTemplate);
        return frontDesk;
    }
}
