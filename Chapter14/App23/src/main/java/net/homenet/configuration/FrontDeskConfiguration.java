package net.homenet.configuration;

import net.homenet.FrontDesk;
import net.homenet.FrontDeskImpl;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrontDeskConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.222.128");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("password");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setRoutingKey("mail.queue");
        return rabbitTemplate;
    }

    @Bean
    public FrontDesk frontDesk(RabbitTemplate rabbitTemplate) {
        FrontDeskImpl frontDesk = new FrontDeskImpl();
        frontDesk.setRabbitOperations(rabbitTemplate);
        return frontDesk;
    }
}
