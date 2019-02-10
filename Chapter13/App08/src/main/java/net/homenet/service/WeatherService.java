package net.homenet.service;

import net.homenet.domain.TemperatureInfo;

import java.util.Date;
import java.util.List;

public interface WeatherService {
    List<TemperatureInfo> getTemperators(String city, List<Date> dates);
}
