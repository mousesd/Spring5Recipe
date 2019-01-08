package net.homenet.configuration;

import com.mongodb.MongoClient;
import net.homenet.repository.MongoDBVehicleRepository;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDbConfiguration {
    private static final String DB_NAME = "vehicleDb";

    @Bean
    public MongoClient mongo() {
        return new MongoClient();
    }

    @Bean
    public VehicleRepository vehicleRepository(MongoClient mongo) {
        return new MongoDBVehicleRepository(mongo, DB_NAME, "vehicles");
    }
}
