package net.homenet.repository;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.homenet.domain.Vehicle;

import java.io.IOException;

public class CouchbaseVehicleRepositoryImpl implements VehicleRepository {
    private final Bucket bucket;
    private final ObjectMapper mapper;

    public CouchbaseVehicleRepositoryImpl(Bucket bucket, ObjectMapper mapper) {
        this.bucket = bucket;
        this.mapper = mapper;
    }

    @Override
    public void save(Vehicle vehicle) {
        //# 1.SerializableDocument 이용
        //SerializableDocument document = SerializableDocument.create(vehicle.getVehicleNo(), vehicle);

        //# 2.JsonDocument 이용
        //JsonObject jsonObject = JsonObject.empty()
        //    .put("vehicleNo", vehicle.getVehicleNo())
        //    .put("color", vehicle.getColor())
        //    .put("wheel", vehicle.getWheel())
        //    .put("seat", vehicle.getSeat());
        //JsonDocument document = JsonDocument.create(vehicle.getVehicleNo(), jsonObject);

        //# 3.Jackson 의 ObjectMapper 이용
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(vehicle);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JsonObject jsonObject = JsonObject.fromJson(jsonString);
        JsonDocument document = JsonDocument.create(vehicle.getVehicleNo(), jsonObject);
        bucket.upsert(document);
    }

    @Override
    public void delete(Vehicle vehicle) {
        bucket.remove(vehicle.getVehicleNo());
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        //# 1.SerializableDocument 이용
        //SerializableDocument document = bucket.get(vehicleNo, SerializableDocument.class);
        //if (document != null) {
        //    return (Vehicle) document.content();
        //}

        //# 2.JsonDocument 이용
        //JsonDocument document = bucket.get(vehicleNo, JsonDocument.class);
        //if (document != null) {
        //    JsonObject jsonObject = document.content();
        //    return new Vehicle(jsonObject.getString("vehicleNo")
        //        , jsonObject.getString("color")
        //        , jsonObject.getInt("wheel")
        //        , jsonObject.getInt("seat"));
        //}

        //# 3.Jackson 의 ObjectMapper 이용
        JsonDocument document = bucket.get(vehicleNo, JsonDocument.class);
        if (document != null) {
            try {
                return mapper.readValue(document.content().toString(), Vehicle.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
