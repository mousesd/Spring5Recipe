package net.homenet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
public class SequenceConfiguration {
    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator generator = new SequenceGenerator();
        generator.setInitial(10000);
        generator.setSuffix("A");
        return generator;
    }
}
