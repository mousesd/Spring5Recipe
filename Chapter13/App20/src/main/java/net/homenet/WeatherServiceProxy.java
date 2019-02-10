package net.homenet;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherServiceProxy implements WeatherService {
    private static final String namespaceUri = "http://homenet.net/weather/schemas";
    private final WebServiceTemplate wsTemplate;

    public WeatherServiceProxy(WebServiceTemplate wsTemplate) {
        this.wsTemplate = wsTemplate;
    }

    @Override
    public List<TemperatureInfo> getTemperators(String city, List<Date> dates) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Document request = DocumentHelper.createDocument();
        Element requestElement = request.addElement("GetTemperaturesRequest", namespaceUri);
        requestElement.addElement("city").setText(city);
        for (Date date : dates) {
            requestElement.addElement("date").setText(df.format(date));
        }

        DocumentSource source = new DocumentSource(request);
        DocumentResult result = new DocumentResult();
        wsTemplate.sendSourceAndReceiveToResult(source, result);

        Document response = result.getDocument();
        Element responseElement = response.getRootElement();
        List<TemperatureInfo> temperatures = new ArrayList<>();
        for (Element element : responseElement.elements("TemperatureInfo")) {
            try {
                Date date = df.parse(element.attributeValue("date"));
                double min = Double.parseDouble(element.elementText("min"));
                double max = Double.parseDouble(element.elementText("max"));
                double average = Double.parseDouble(element.elementText("average"));
                temperatures.add(new TemperatureInfo(city, date, min, max, average));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temperatures;
    }
}
