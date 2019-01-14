package net.homenet.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableReactiveCouchbaseRepositories(basePackages = "net.homenet.repository")
@ComponentScan("net.homenet")
public class CouchbaseConfiguration extends AbstractReactiveCouchbaseConfiguration {
    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList("localhost");
    }

    @Override
    protected String getBucketName() {
        return "Vehicle";
    }

    @Override
    protected String getBucketPassword() {
        return null;
    }
}
