package net.homenet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class TemperatureMarshallingEndpoint {
    private static final String namespaceUri = "http://net.homenet/weather/schemas";

    private final WeatherService weatherService;

    @Autowired
    public TemperatureMarshallingEndpoint(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @SuppressWarnings("unused")
    @PayloadRoot(localPart = "GetTemperaturesRequest", namespace = namespaceUri)
    public @ResponsePayload GetTemperaturesResponse getTemperature(@RequestPayload GetTemperaturesRequest request) {
        List<TemperatureInfo> temperatures = weatherService.getTemperators(request.getCity(), request.getDates());
        return new GetTemperaturesResponse(temperatures);
    }
}
