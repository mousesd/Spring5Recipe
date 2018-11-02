package net.homenet.configuration;

import net.homenet.feed.AtomFeedView;
import net.homenet.feed.RssFeedView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("net.homenet.*")
public class FeedConfiguration {
    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

    @Bean
    public AtomFeedView atomFeedTemplate() {
        return new AtomFeedView();
    }

    @Bean
    public RssFeedView rssFeedTemplate() {
        return new RssFeedView();
    }
}
