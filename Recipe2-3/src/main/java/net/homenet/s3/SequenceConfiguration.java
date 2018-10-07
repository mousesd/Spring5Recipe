package net.homenet.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
public class SequenceConfiguration {
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

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator generator = new SequenceGenerator();
        generator.setInitial(10000);
        generator.setSuffix("A");
        return generator;
    }
}
