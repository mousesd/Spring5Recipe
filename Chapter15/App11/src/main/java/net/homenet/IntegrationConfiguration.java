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
    public CustomerFilesSplitter customerFilesSplitter() {
        return new CustomerFilesSplitter();
    }

    @Bean
    public LineToCustomerTransformer lineToCustomerTransformer() {
        return new LineToCustomerTransformer();
    }

    @Bean
    public CustomerCreditScoreRouter customerCreditScoreRouter() {
        return new CustomerCreditScoreRouter();
    }

    @Bean
    public IntegrationFlow deleteCustomer(CustomerFilesSplitter customerFilesSplitter
        , LineToCustomerTransformer lineToCustomerTransformer
        , CustomerCreditScoreRouter customerCreditScoreRouter
        , @Value("file:${user.home}/removeCustomers/new/") File inputDirectory) {

        return IntegrationFlows.from(
                Files.inboundAdapter(inputDirectory).patternFilter("customers-*.txt"), c -> c.poller(Pollers.fixedRate(1, TimeUnit.SECONDS)))
            .split(customerFilesSplitter)
            .transform(lineToCustomerTransformer)
            .route(customerCreditScoreRouter)
            .get();
    }

    @Bean
    public IntegrationFlow safeCustomerFlow() {
        return IntegrationFlows.from("safeCustomerChannel").<Customer>log(customer -> "Safe: " + customer).get();
    }

    @Bean
    public IntegrationFlow riskyCustomerFlow() {
        return IntegrationFlows.from("riskyCustomerChannel").<Customer>log(customer -> "Risky: " + customer).get();
    }
}
