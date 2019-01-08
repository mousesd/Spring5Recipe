package net.homenet.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.jndi.MongoClientFactory;
import net.homenet.repository.MongoDBVehicleRepository;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
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

    @Bean
    public VehicleRepository vehicleRepository(MongoTemplate mongoTemplate) {
        return new MongoDBVehicleRepository(mongoTemplate);
    }
}
