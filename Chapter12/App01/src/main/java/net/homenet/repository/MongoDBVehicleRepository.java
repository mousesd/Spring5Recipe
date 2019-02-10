package net.homenet.repository;

import net.homenet.domain.Vehicle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.PreDestroy;
import java.util.List;

@Document(collection = "vehicles")
public class MongoDBVehicleRepository implements VehicleRepository {
    private final MongoTemplate mongo;

    public MongoDBVehicleRepository(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public long count() {
        return mongo.count(new Query(), Vehicle.class);
    }

    @Override
    public void save(Vehicle vehicle) {
        mongo.save(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        mongo.remove(vehicle);
    }

    @Override
    public List<Vehicle> findAll() {
        return mongo.findAll(Vehicle.class);
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return mongo.findOne(new Query(Criteria.where("vehicleNo").is(vehicleNo)), Vehicle.class);
    }

    @PreDestroy
    public void cleanup() {
        mongo.execute(db -> {
            db.drop();
            return null;
        });
    }
}
