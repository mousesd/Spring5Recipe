package net.homenet.configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.homenet.repository.CouchbaseVehicleRepositoryImpl;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseConfiguration {
    @Bean(destroyMethod = "disconnect")
    public Cluster cluster() {
        Cluster cluster = CouchbaseCluster.create();
        cluster.authenticate("Administrator", "sqladmin");
        return cluster;
    }

    @Bean
    public Bucket bucket(Cluster cluster) {
        return cluster.openBucket("Vehicle");
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public VehicleRepository vehicleRepository(Bucket bucket, ObjectMapper mapper) {
        return new CouchbaseVehicleRepositoryImpl(bucket, mapper);
    }
}
