package net.homenet.repository;

import net.homenet.domain.Vehicle;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends ReactiveCrudRepository<Vehicle, String> { }
