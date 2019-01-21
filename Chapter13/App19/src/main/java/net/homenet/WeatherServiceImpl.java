package net.homenet;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WeatherServiceImpl implements WeatherService {
    @Override
    @WebMethod(operationName = "getTemperators")
    public List<TemperatureInfo> getTemperators(String city, List<Date> dates) {
        List<TemperatureInfo> temperatures = new ArrayList<>();
        for (Date date : dates) {
            temperatures.add(new TemperatureInfo(city, date, 5.0, 10.0, 8.0));
        }
        return temperatures;
    }
}
