package net.homenet;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableIntegration
public class ServerConfiguration {
    @Bean
    public CachingConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.222.128:61616");
        connectionFactory.setTrustAllPackages(true);
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public VacationService vacationService() {
        return new VacationServiceImpl();
    }

    @Bean
    public IntegrationFlow vacationInboundFlow(ConnectionFactory connectionFactory, VacationService vacationService) {
        return IntegrationFlows.from(Jms.inboundGateway(connectionFactory).destination("inboundHotelReservationSearchDestination"))
            .handle(vacationService)
            .get();
    }
}
