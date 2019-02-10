package net.homenet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
public class IntegrationConfiguration {
    @Bean
    public CustomerDeleteServiceActivator customerDeleteServiceActivator() {
        return new CustomerDeleteServiceActivator();
    }

    @Bean
    public CustomerFilesSplitter customerFilesSplitter() {
        return new CustomerFilesSplitter();
    }

    @Bean
    public IntegrationFlow deleteCustomer(CustomerDeleteServiceActivator serviceActivator
        , CustomerFilesSplitter filesSplitter
        , @Value("file:${user.home}/removeCustomers/new/")File inputDirectory) {

        return IntegrationFlows.from(
                Files.inboundAdapter(inputDirectory).patternFilter("customerstoremove-*.txt")
                    , c -> c.poller(Pollers.fixedRate(1, TimeUnit.SECONDS)))
            .split(filesSplitter)
            .handle(serviceActivator)
            .get();
    }
}
