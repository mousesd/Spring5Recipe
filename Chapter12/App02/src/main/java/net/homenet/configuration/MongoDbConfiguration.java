package net.homenet.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.jndi.MongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "net.homenet")
public class MongoDbConfiguration {
    private static final String DB_NAME = "vehicleDb";

    @Bean
    public MongoClient mongo() {
        return new MongoClient();
    }

    @Bean
    public MongoClientFactory mongoClientFactory() {
        return new MongoClientFactory();
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, DB_NAME);
    }

    @EventListener(classes = { ContextClosedEvent.class })
    public void handleContextClosedEvent() {
        mongo().dropDatabase(DB_NAME);
        mongo().close();
    }
}
