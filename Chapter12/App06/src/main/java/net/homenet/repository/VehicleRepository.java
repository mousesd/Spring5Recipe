package net.homenet.repository;

import net.homenet.domain.Vehicle;

public interface VehicleRepository {
    void save(Vehicle vehicle);
    void delete(Vehicle vehicle);
    Vehicle findByVehicleNo(String vehicleNo);
}
