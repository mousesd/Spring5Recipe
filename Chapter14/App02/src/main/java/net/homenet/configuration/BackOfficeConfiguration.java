package net.homenet.configuration;

import net.homenet.BackOffice;
import net.homenet.BackOfficeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackOfficeConfiguration {
    @Bean
    public BackOffice backOffice() {
        return new BackOfficeImpl();
    }
}
