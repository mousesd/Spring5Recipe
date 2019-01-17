package net.homenet.service;

import net.homenet.domain.TemperatureInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherServiceImpl implements WeatherService {
    @Override
    public List<TemperatureInfo> getTemperators(String city, List<Date> dates) {
        List<TemperatureInfo> temperatures = new ArrayList<>();
        for (Date date : dates) {
            temperatures.add(new TemperatureInfo(city, date, 5.0, 10.0, 8.0));
        }
        return temperatures;
    }
}
