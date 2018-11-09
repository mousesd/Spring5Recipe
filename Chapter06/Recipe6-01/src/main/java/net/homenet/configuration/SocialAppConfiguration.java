package net.homenet.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;

@Configuration
@EnableSocial
@PropertySource("classpath:/application.properties")
public class SocialAppConfiguration extends SocialConfigurerAdapter {
    @Override
    public StaticUserIdSource getUserIdSource() {
        return new StaticUserIdSource();
    }
}
