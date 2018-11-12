package net.homenet.configuration;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
@EnableSocial
@PropertySource("classpath:/application.properties")
public class SocialAppConfiguration extends SocialConfigurerAdapter {
    @Override
    public StaticUserIdSource getUserIdSource() {
        return new StaticUserIdSource();
    }

    //# NOTE: @Configuration 설정하면 아래 예외가 발생
    //# Caused by: java.lang.IllegalArgumentException: A ConnectionFactory for provider 'twitter' has already been registered
    //#     at org.springframework.social.connect.support.ConnectionFactoryRegistry.addConnectionFactory(ConnectionFactoryRegistry.java:45)
    //#     at org.springframework.social.config.annotation.DefaultConnectionFactoryConfigurer.addConnectionFactory(DefaultConnectionFactoryConfigurer.java:35)
    //#     at net.homenet.configuration.SocialAppConfiguration$TwitterConfiguration.addConnectionFactories(SocialAppConfiguration.java:29)
    //@Configuration
    //public static class TwitterConfiguration extends SocialConfigurerAdapter {
    //    @Override
    //    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer
    //        , Environment environment) {
    //
    //        connectionFactoryConfigurer.addConnectionFactory(
    //            new TwitterConnectionFactory(environment.getRequiredProperty("twitter.appId")
    //                , environment.getRequiredProperty("twitter.appSecret")));
    //    }
    //
    //    @Bean
    //    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    //    public Twitter twitterTemplate(ConnectionRepository repository) {
    //        Connection<Twitter> connection = repository.findPrimaryConnection(Twitter.class);
    //        return connection != null ? connection.getApi() : null;
    //    }
    //}

    //# NOTE: @Configuration 설정하면 아래 예외가 발생
    //# Caused by: java.lang.IllegalArgumentException: A ConnectionFactory for provider 'facebook' has already been registered
    //#     at org.springframework.social.connect.support.ConnectionFactoryRegistry.addConnectionFactory(ConnectionFactoryRegistry.java:45)
    //#     at org.springframework.social.config.annotation.DefaultConnectionFactoryConfigurer.addConnectionFactory(DefaultConnectionFactoryConfigurer.java:35)
    //#     at net.homenet.configuration.SocialAppConfiguration$FacebookConfiguration.addConnectionFactories(SocialAppConfiguration.java:52)
    //@Configuration
    //public static class FacebookConfiguration extends SocialConfigurerAdapter {
    //    @Override
    //    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
    //        configurer.addConnectionFactory(
    //            new FacebookConnectionFactory(environment.getRequiredProperty("facebook.appId"),
    //                environment.getRequiredProperty("facebook.appSecret")));
    //    }
    //
    //    @Bean
    //    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    //    public Facebook facebookTemplate(ConnectionRepository repository) {
    //        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
    //        return connection != null ? connection.getApi() : null;
    //    }
    //}

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(
            new TwitterConnectionFactory(environment.getRequiredProperty("twitter.appId")
                , environment.getRequiredProperty("twitter.appSecret")));
        configurer.addConnectionFactory(
            new FacebookConnectionFactory(environment.getRequiredProperty("facebook.appId"),
                environment.getRequiredProperty("facebook.appSecret")));
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Twitter twitterTemplate(ConnectionRepository repository) {
        Connection<Twitter> connection = repository.findPrimaryConnection(Twitter.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebookTemplate(ConnectionRepository repository) {
        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator locator, ConnectionRepository repository) {
        return new ConnectController(locator, repository);
    }
}
