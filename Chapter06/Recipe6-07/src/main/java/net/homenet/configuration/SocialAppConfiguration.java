package net.homenet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.security.PublicKey;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableSocial
@PropertySource("classpath:/application.properties")
public class SocialAppConfiguration extends SocialConfigurerAdapter {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private Environment env;

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
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

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource(), connectionFactoryLocator, Encryptors.noOpText());
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

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setDriverClassName(env.getRequiredProperty("datasource.driverClassName"));
        return dataSource;
    }

    @Bean
    public DataSourceInitializer databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(
            "org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql"));
        populator.addScript(new ClassPathResource("scripts/create_users.sql"));
        populator.addScript(new ClassPathResource("scripts/init_users.sql"));
        populator.setContinueOnError(true);

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDatabasePopulator(populator);
        initializer.setDataSource(dataSource());
        return initializer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator locator, UsersConnectionRepository repository) {
        return new ProviderSignInUtils(locator, repository);
    }
}
