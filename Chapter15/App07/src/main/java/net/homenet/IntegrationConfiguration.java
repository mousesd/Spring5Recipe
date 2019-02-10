package net.homenet;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
@EnableIntegration
@ComponentScan
public class IntegrationConfiguration {
    @Bean
    public CachingConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.222.128:61616");
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    @Bean
    public InboundJmsMessageProcessor inboundJmsMessageProcessor() {
        return new InboundJmsMessageProcessor();
    }

    @Bean
    public InboundJmsMessageToCustomerTransformer customerTransformer() {
        return new InboundJmsMessageToCustomerTransformer();
    }

    @Bean
    public DefaultErrorHandlingServiceActivator defaultErrorHandlingServiceActivator() {
        return new DefaultErrorHandlingServiceActivator();
    }

    @Bean
    public IntegrationFlow errorFlow(DefaultErrorHandlingServiceActivator defaultErrorHandlingServiceActivator) {
        return IntegrationFlows.from("errorChannel")
            .handle(defaultErrorHandlingServiceActivator)
            .get();
    }

    @Bean
    public IntegrationFlow jmsInbound(ConnectionFactory connectionFactory
        , InboundJmsMessageProcessor messageProcessor
        , InboundJmsMessageToCustomerTransformer customerTransformer) {

        return IntegrationFlows
            .from(Jms.messageDrivenChannelAdapter(connectionFactory)
                .extractPayload(true)
                .destination("recipe15-2")
                .errorChannel("errorChannel"))
            .transform(customerTransformer)
            .handle(messageProcessor)
            .get();
    }
}
