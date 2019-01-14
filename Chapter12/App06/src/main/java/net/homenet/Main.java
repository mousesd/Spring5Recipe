package net.homenet;

import net.homenet.configuration.CouchbaseConfiguration;
import net.homenet.domain.Vehicle;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CouchbaseConfiguration.class);
        VehicleRepository repository = context.getBean(VehicleRepository.class);

        repository.save(new Vehicle("TEM0001", "GREEN", 3, 1));
        repository.save(new Vehicle("TEM0004", "RED", 4, 1));

        System.out.println(repository.findByVehicleNo("TEM0001"));
        System.out.println(repository.findByVehicleNo("TEM0004"));

        repository.delete(repository.findByVehicleNo("TEM0001"));
        repository.delete(repository.findByVehicleNo("TEM0004"));
    }
}
