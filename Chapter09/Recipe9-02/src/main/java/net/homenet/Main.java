package net.homenet;

import net.homenet.configuration.VehicleConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfiguration.class);

        VehicleDao vehicleDao = context.getBean(VehicleDao.class);
        vehicleDao.deleteAll();

        Vehicle vehicle = new Vehicle("TEM0001", "Red", 4, 4);
        vehicleDao.insert(vehicle);

        vehicle = vehicleDao.findByVehicleNo("TEM0001");
        System.out.println(vehicle);

        //# Batch insert
        Vehicle vehicle1 = new Vehicle("TEM0022", "Blue", 4, 4);
        Vehicle vehicle2 = new Vehicle("TEM0023", "Black", 4, 5);
        Vehicle vehicle3 = new Vehicle("TEM0024", "Green", 4, 6);
        vehicleDao.insert(Arrays.asList(vehicle1, vehicle2, vehicle3));
    }
}
