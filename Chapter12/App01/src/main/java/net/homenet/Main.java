package net.homenet;

import net.homenet.configuration.MongoDbConfiguration;
import net.homenet.domain.Vehicle;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MongoDbConfiguration.class);
        VehicleRepository repository = context.getBean(VehicleRepository.class);

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

        context.close();
    }
}
