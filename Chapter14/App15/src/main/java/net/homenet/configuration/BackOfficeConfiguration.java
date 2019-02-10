package net.homenet.configuration;

import net.homenet.MailListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageListener;

@SuppressWarnings("Duplicates")
@Configuration
public class BackOfficeConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://192.168.222.128:61616");
    }

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MailListener mailListener) {
        SimpleMessageListenerContainer smlc = new SimpleMessageListenerContainer();
        smlc.setConnectionFactory(connectionFactory);
        smlc.setDestinationName("mail.queue");
        smlc.setMessageListener(mailListener);
        return smlc;
    }
}
