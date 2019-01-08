package net.homenet.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.sun.istack.internal.NotNull;
import net.homenet.domain.Vehicle;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MongoDBVehicleRepository implements VehicleRepository {
    private final MongoClient mongo;
    private final String collectionName;
    private final String databaseName;

    public MongoDBVehicleRepository(MongoClient mongo, String collectionName, String databaseName) {
        this.mongo = mongo;
        this.collectionName = collectionName;
        this.databaseName = databaseName;
    }

    @Override
    public long count() {
        return getCollection().countDocuments();
    }

    @Override
    public void save(Vehicle vehicle) {
        Document document = transform(vehicle);
        getCollection().insertOne(document);
    }

    @Override
    public void delete(Vehicle vehicle) {
        getCollection().deleteOne(Filters.eq("vehicleNo", vehicle.getVehicleNo()));
    }

    @Override
    public List<Vehicle> findAll() {
        return StreamSupport.stream(getCollection().find().spliterator(), false)
            .map(this::transform)
            .collect(Collectors.toList());
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        Document document = getCollection().find(Filters.eq("vehicleNo", vehicleNo)).first();
        assert document != null;
        return transform(document);
    }

    private MongoCollection<Document> getCollection() {
        return mongo.getDatabase(databaseName).getCollection(collectionName);
    }

    private Document transform(@NotNull Vehicle vehicle) {
        return new Document("vehicleNo", vehicle.getVehicleNo())
            .append("color", vehicle.getColor())
            .append("wheel", vehicle.getWheel())
            .append("seat", vehicle.getSeat());
    }

    private Vehicle transform(@NotNull Document document) {
        return new Vehicle(document.getString("vehicleNo")
            , document.getString("color")
            , document.getInteger("wheel")
            , document.getInteger("seat"));
    }
}
