package net.homenet.repository;

import net.homenet.domain.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Vehicle findByVehicleNo(String vehicle);
}
