package net.homenet;

import jdk.internal.org.objectweb.asm.TypeReference;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHandlingException;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

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
    public ErrorMessageExceptionTypeRouter exceptionTypeRouter() {
        //# 1.아래 코드를 사용하면 Exception 발생
        //# - https://stackoverflow.com/questions/49995635/spring-integration-errormessageexceptiontyperouter-nullpointerexception-how
        //# - Spring Integration Bug 라고 알려진 상태, 당분간은 2번 형태로 사용
        //# Caused by: java.lang.NullPointerException
        //#     at org.springframework.integration.router.ErrorMessageExceptionTypeRouter.resolveClassFromName(ErrorMessageExceptionTypeRouter.java:70)
        //#     at org.springframework.integration.router.ErrorMessageExceptionTypeRouter.setChannelMapping(ErrorMessageExceptionTypeRouter.java:82)
        //#     at net.homenet.IntegrationConfiguration.exceptionTypeRouter(IntegrationConfiguration.java:56)
        //ErrorMessageExceptionTypeRouter typeRouter = new ErrorMessageExceptionTypeRouter();
        //typeRouter.setChannelMapping(InvalidCustomerException.class.getName(), "customExceptionChannel");
        //typeRouter.setChannelMapping(RuntimeException.class.getName(), "runtimeExceptionChannel");
        //typeRouter.setChannelMapping(MessageHandlingException.class.getName(), "messageHandlingExceptionChannel");
        //return typeRouter;

        //# 2.
        ErrorMessageExceptionTypeRouter typeRouter = new ErrorMessageExceptionTypeRouter();
        Map<String, String> channelMappings = new HashMap<>();
        channelMappings.put(InvalidCustomerException.class.getName(), "customExceptionChannel");
        channelMappings.put(RuntimeException.class.getName(), "runtimeExceptionChannel");
        channelMappings.put(MessageHandlingException.class.getName(), "messageHandlingExceptionChannel");
        typeRouter.setChannelMappings(channelMappings);
        return typeRouter;
    }

    @Bean
    public IntegrationFlow errorFlow(ErrorMessageExceptionTypeRouter exceptionTypeRouter) {
        return IntegrationFlows.from("errorChannel")
            .route(exceptionTypeRouter)
            .get();
    }

    @Bean
    public IntegrationFlow customErrorFlow(DefaultErrorHandlingServiceActivator defaultErrorHandlingServiceActivator) {
        return IntegrationFlows.from("customExceptionChannel")
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
