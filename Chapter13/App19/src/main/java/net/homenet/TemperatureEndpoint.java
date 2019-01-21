package net.homenet;

import org.dom4j.*;
import org.dom4j.xpath.DefaultXPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Endpoint
public class TemperatureEndpoint {
    private static final String namespaceUri = "http://homenet.net/weather/schemas";

    private final WeatherService weatherService;
    private XPath cityPath;
    private XPath datePath;

    @Autowired
    public TemperatureEndpoint(WeatherService weatherService) {
        this.weatherService = weatherService;

        Map<String, String> namespaceUris = new HashMap<>();
        namespaceUris.put("weather", namespaceUri);

        cityPath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:city");
        cityPath.setNamespaceURIs(namespaceUris);
        datePath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:date");
        datePath.setNamespaceURIs(namespaceUris);
    }

    @PayloadRoot(localPart = "GetTemperaturesRequest", namespace = namespaceUri)
    @ResponsePayload
    public Element getTemperature(@RequestPayload Element request) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String city = cityPath.valueOf(request);
        List<Date> dates = new ArrayList<>();
        for (Node node : datePath.selectNodes(request)) {
            dates.add(df.parse(node.getText()));
        }

        List<TemperatureInfo> temperatures = weatherService.getTemperators(city, dates);
        Document response = DocumentHelper.createDocument();
        Element responseElement = response.addElement("GetTemperaturesResponse", namespaceUri);
        for (TemperatureInfo temperature : temperatures) {
            Element temperatureElement = responseElement.addElement("TemperatureInfo");
            temperatureElement.addAttribute("city", temperature.getCity());
            temperatureElement.addAttribute("date", df.format(temperature.getDate()));
            temperatureElement.addElement("min").setText(Double.toString(temperature.getMin()));
            temperatureElement.addElement("max").setText(Double.toString(temperature.getMax()));
            temperatureElement.addElement("average").setText(Double.toString(temperature.getAverage()));
        }
        return responseElement;
    }
}
