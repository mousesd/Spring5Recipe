package net.homenet.repository;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.SerializableDocument;
import net.homenet.domain.Vehicle;

public class CouchbaseVehicleRepositoryImpl implements VehicleRepository {
    private final Bucket bucket;

    public CouchbaseVehicleRepositoryImpl(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void save(Vehicle vehicle) {
        SerializableDocument document = SerializableDocument.create(vehicle.getVehicleNo(), vehicle);
        bucket.upsert(document);
    }

    @Override
    public void delete(Vehicle vehicle) {
        bucket.remove(vehicle.getVehicleNo());
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        SerializableDocument document = bucket.get(vehicleNo, SerializableDocument.class);
        if (document != null) {
            return (Vehicle) document.content();
        }
        return null;
    }
}
