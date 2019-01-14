package net.homenet.configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = "net.homenet.repository")
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
}
