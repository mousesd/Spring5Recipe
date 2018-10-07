package net.homenet.s1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {
    @Bean
    public DatePrefixGenerator datePrefixGenerator() {
        DatePrefixGenerator generator = new DatePrefixGenerator();
        generator.setPattern("yyyyMMdd");
        return generator;
    }

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator generator = new SequenceGenerator();
        generator.setPrefixGenerator(datePrefixGenerator());
        generator.setInitial(10000);
        generator.setSuffix("A");
        return generator;
    }
}
