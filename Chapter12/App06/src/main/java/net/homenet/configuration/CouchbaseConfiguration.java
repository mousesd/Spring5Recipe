package net.homenet.configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import net.homenet.repository.CouchbaseVehicleRepositoryImpl;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

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
    public CouchbaseTemplate couchbaseTemplate(Cluster cluster, Bucket bucket) {
        return new CouchbaseTemplate(cluster.clusterManager().info(), bucket);
    }

    @Bean
    public VehicleRepository vehicleRepository(CouchbaseTemplate couchbaseTemplate) {
        return new CouchbaseVehicleRepositoryImpl(couchbaseTemplate);
    }
}
