package net.homenet;

import java.util.Collection;
import java.util.List;

public interface VehicleDao {
    void insert(Vehicle vehicle);
    void insert(Collection<Vehicle> vehicles);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    void deleteAll();
    Vehicle findByVehicleNo(String vehicleNo);
    List<Vehicle> findAll();
    String getColor(String vehicleNo);
    int countAll();
}
