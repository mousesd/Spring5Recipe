package net.homenet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
public class PrefixConfiguration {
    @Bean
    public DatePrefixGenerator datePrefixGenerator() {
        DatePrefixGenerator generator = new DatePrefixGenerator();
        generator.setPattern("yyyyMMdd");
        return generator;
    }

    @Bean
    public NumberPrefixGenerator numberPrefixGenerator() {
        return new NumberPrefixGenerator();
    }
}
