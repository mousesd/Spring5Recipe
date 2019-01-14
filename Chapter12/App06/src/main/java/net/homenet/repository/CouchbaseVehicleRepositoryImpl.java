package net.homenet.repository;

import net.homenet.domain.Vehicle;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

public class CouchbaseVehicleRepositoryImpl implements VehicleRepository {
    private final CouchbaseTemplate template;

    public CouchbaseVehicleRepositoryImpl(CouchbaseTemplate template) {
        this.template = template;
    }

    @Override
    public void save(Vehicle vehicle) {
        template.save(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        template.remove(vehicle);
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return template.findById(vehicleNo, Vehicle.class);
    }
}
