package net.homenet;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import net.homenet.domain.Vehicle;
import net.homenet.repository.CouchbaseVehicleRepositoryImpl;
import net.homenet.repository.VehicleRepository;

public class Main {
    public static void main(String[] args) {
        Cluster cluster = CouchbaseCluster.create();
        cluster.authenticate("Administrator", "sqladmin");
        Bucket bucket = cluster.openBucket("Vehicle");

        VehicleRepository repository = new CouchbaseVehicleRepositoryImpl(bucket);
        repository.save(new Vehicle("TEM0001", "GREEN", 3, 1));
        repository.save(new Vehicle("TEM0004", "RED", 4, 1));

        System.out.println(repository.findByVehicleNo("TEM0001"));
        System.out.println(repository.findByVehicleNo("TEM0004"));

        bucket.remove("TEM0001");
        bucket.remove("TEM0004");

        bucket.close();
        cluster.disconnect();
    }
}
