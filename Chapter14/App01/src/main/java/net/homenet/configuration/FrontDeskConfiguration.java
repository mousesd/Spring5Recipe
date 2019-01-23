package net.homenet.configuration;

import net.homenet.FrontDesk;
import net.homenet.FrontDeskImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrontDeskConfiguration {
    @Bean
    public FrontDesk frontDesk() {
        return new FrontDeskImpl();
    }
}
