package net.homenet.configuration;

import net.homenet.EmailErrorNotifierImpl;
import net.homenet.ErrorNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfiguration {
    @Bean
    public ErrorNotifier errorNotifier() {
        return new EmailErrorNotifierImpl();
    }
}
