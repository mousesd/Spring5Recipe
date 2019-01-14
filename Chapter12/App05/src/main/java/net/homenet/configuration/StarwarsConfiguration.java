package net.homenet.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "net.homenet.repository")
@ComponentScan("net.homenet")
public class StarwarsConfiguration {
    //# Configuration 를 이용해 로컬파일에 접속이 안됨
    //@Bean
    //public org.neo4j.ogm.config.Configuration configuration() {
    //    String dbPath = System.getProperty("user.home") + "\\friends";
    //
    //    FileConfigurationSource source = new FileConfigurationSource(dbPath);
    //    return new org.neo4j.ogm.config.Configuration.Builder(source).build();
    //}

    //@Bean
    //public SessionFactory sessionFactory(org.neo4j.ogm.config.Configuration configuration) {
    //    return new SessionFactory(configuration, "net.homenet");
    //}

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory("net.homenet");
    }

    @Bean
    public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new Neo4jTransactionManager(sessionFactory);
    }
}
