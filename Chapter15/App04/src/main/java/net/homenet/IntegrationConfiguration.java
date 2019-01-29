package net.homenet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableIntegration
@ComponentScan
public class IntegrationConfiguration {
    @Bean
    public InboundMessageProcessor inboundJmsMessageProcessor() {
        return new InboundMessageProcessor();
    }

    @Bean
    public IntegrationFlow fileInboundFlow(InboundMessageProcessor messageProcessor
        , @Value("${user.home}/inBoundFiles/news/")File directory) {

        return IntegrationFlows.from(Files.inboundAdapter(directory).patternFilter("*.csv")
                , s -> s.poller(Pollers.fixedDelay(10, TimeUnit.SECONDS)))
            .handle(messageProcessor)
            .get();
    }
}
