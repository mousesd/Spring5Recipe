package net.homenet.repository;

import net.homenet.domain.Vehicle;

import java.util.List;

public interface VehicleRepository {
    long count();
    void save(Vehicle vehicle);
    void delete(Vehicle vehicle);
    List<Vehicle> findAll();
    Vehicle findByVehicleNo(String vehicle);
}
