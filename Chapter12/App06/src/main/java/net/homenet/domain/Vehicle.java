package net.homenet.domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Vehicle implements Serializable {
    @Id
    private String vehicleNo;
    @Field
    private String color;
    @Field
    private int wheel;
    @Field
    private int seat;

    public Vehicle() { }

    public Vehicle(String vehicleNo, String color, int wheel, int seat) {
        this.vehicleNo = vehicleNo;
        this.color = color;
        this.wheel = wheel;
        this.seat = seat;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWheel() {
        return wheel;
    }

    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "vehicleNo='" + vehicleNo + '\'' +
            ", color='" + color + '\'' +
            ", wheel=" + wheel +
            ", seat=" + seat +
            '}';
    }
}
