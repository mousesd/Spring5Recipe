package net.homenet.s1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceGenaratorConfiguration {
    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator generator = new SequenceGenerator();
        generator.setPrefix("30");
        generator.setSuffix("A");
        generator.setInitial(100000);
        return generator;
    }
}
