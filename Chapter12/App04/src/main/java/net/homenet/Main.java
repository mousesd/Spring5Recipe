package net.homenet;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.homenet.domain.Vehicle;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) throws Exception {
        //# 1.Connect to Redis
        //Jedis jedis = new Jedis("localhost");

        //jedis.set("message", "Hello, from Jedis!");
        //System.out.println(jedis.get("message"));

        //jedis.rpush("authors", "Marten Deinum", "Josh Long", "Daniel Rubio", "Gark Mak");
        //System.out.println("Authors: " + jedis.lrange("authors", 0, -1));

        //jedis.hset("sr_3", "authors", "Gark Mak, Danial Rubio, Josh Long, Maten Deinum");
        //jedis.hset("sr_3", "published", "2014");
        //jedis.hset("sr_4", "authors", "Josh Long, Marten Deinum");
        //jedis.hset("sr_4", "published", "2017");

        //System.out.println("Spring Recipes 3rd: " + jedis.hgetAll("sr_3"));
        //System.out.println("Spring Recipes 4th: " + jedis.hgetAll("sr_4"));

        //# 2.Store object with Redis(byte[])
        //String vehicleNo = "TEM0001";
        //Vehicle vehicle = new Vehicle(vehicleNo, "RED", 4, 4);
        //
        //Jedis jedis = new Jedis("localhost");
        //jedis.set(vehicleNo.getBytes(), SerializationUtils.serialize(vehicle));
        //
        //byte[] vehicleByteArray = jedis.get(vehicleNo.getBytes());
        //System.out.println(SerializationUtils.deserialize(vehicleByteArray));

        //# 2.Store object with Redis(JSON)
        String vehicleNo = "TEM0001";
        Vehicle vehicle = new Vehicle(vehicleNo, "RED", 4, 4);

        Jedis jedis = new Jedis("localhost");
        ObjectMapper mapper = new ObjectMapper();
        jedis.set(vehicleNo, mapper.writeValueAsString(vehicle));

        String vehicleJsonString = jedis.get(vehicleNo);
        System.out.println(mapper.readValue(vehicleJsonString, Vehicle.class));
    }
}
