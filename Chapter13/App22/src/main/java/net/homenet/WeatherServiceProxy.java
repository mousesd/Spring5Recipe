package net.homenet;

import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.Date;
import java.util.List;

public class WeatherServiceProxy implements WeatherService {
    private final WebServiceTemplate wsTemplate;

    public WeatherServiceProxy(WebServiceTemplate wsTemplate) {
        this.wsTemplate = wsTemplate;
    }

    @Override
    public List<TemperatureInfo> getTemperators(String city, List<Date> dates) {
        GetTemperaturesRequest request = new GetTemperaturesRequest(city, dates);
        GetTemperaturesResponse response = (GetTemperaturesResponse) wsTemplate.marshalSendAndReceive(request);
        return response.getTemperatures();
    }
}
