package net.homenet;

import java.util.List;

@SuppressWarnings("unused")
public class GetTemperaturesResponse {
    private List<TemperatureInfo> temperatures;

    public GetTemperaturesResponse() { }

    public GetTemperaturesResponse(List<TemperatureInfo> temperatures) {
        this.temperatures = temperatures;
    }

    public List<TemperatureInfo> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<TemperatureInfo> temperatures) {
        this.temperatures = temperatures;
    }
}
