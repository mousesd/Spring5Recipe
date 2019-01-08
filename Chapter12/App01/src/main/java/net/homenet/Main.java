package net.homenet;

import com.mongodb.MongoClient;
import net.homenet.domain.Vehicle;
import net.homenet.repository.MongoDBVehicleRepository;
import net.homenet.repository.VehicleRepository;

import java.util.List;

public class Main {
    private static final String DB_NAME = "vehicleDb";

    public static void main(String[] args) {
        MongoClient mongo = new MongoClient();
        VehicleRepository repository = new MongoDBVehicleRepository(mongo, DB_NAME, "vehicles");

        System.out.println("Number of vehicles: " + repository.count());

        repository.save(new Vehicle("TEM0001", "RED", 4, 4));
        repository.save(new Vehicle("TEM0002", "RED", 4, 4));
        System.out.println("Number of vehicles: " + repository.count());

        Vehicle vehicle = repository.findByVehicleNo("TEM0001");
        System.out.println(vehicle);

        List<Vehicle> vehicles = repository.findAll();
        System.out.println("Number of vehicle: " + vehicles.size());

        vehicles.forEach(System.out::println);
        System.out.println("Number of vehicle: " + repository.count());

        mongo.dropDatabase(DB_NAME);
        mongo.close();
    }
}
