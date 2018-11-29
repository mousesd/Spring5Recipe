package net.homenet;

import java.util.List;

public interface VehicleDao {
    void insert(Vehicle vehicle);
    void insert(Iterable<Vehicle> vehicles);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    void deleteAll();
    Vehicle findByVehicleNo(String vehicleNo);
    List<Vehicle> findAll();
}
