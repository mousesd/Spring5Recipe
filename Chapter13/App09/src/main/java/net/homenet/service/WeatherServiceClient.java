package net.homenet.service;

import net.homenet.domain.TemperatureInfo;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class WeatherServiceClient {
    private final WeatherService weatherService;

    public WeatherServiceClient(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public TemperatureInfo getTodayTemperature(String city) {
        List<Date> dates = Collections.singletonList(new Date());
        List<TemperatureInfo> temperatures = weatherService.getTemperators(city, dates);
        return temperatures.get(0);
    }
}
