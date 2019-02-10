package net.homenet.domain;

import java.io.Serializable;
import java.util.Date;

public class TemperatureInfo implements Serializable {
    private String city;
    private Date date;
    private Double min;
    private Double max;
    private Double average;

    public TemperatureInfo() {}

    public TemperatureInfo(String city, Date date, Double min, Double max, Double average) {
        this.city = city;
        this.date = date;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "TemperatureInfo{" +
            "city='" + city + '\'' +
            ", date=" + date +
            ", min=" + min +
            ", max=" + max +
            ", average=" + average +
            '}';
    }
}
