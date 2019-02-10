package net.homenet;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public interface WeatherService {
    List<TemperatureInfo> getTemperators(String city, List<Date> dates);
}
