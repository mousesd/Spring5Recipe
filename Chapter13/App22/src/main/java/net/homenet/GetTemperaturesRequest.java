package net.homenet;

import java.util.Date;
import java.util.List;

@SuppressWarnings({ "WeakerAccess", "unused" })
public class GetTemperaturesRequest {
    private String city;
    private List<Date> dates;

    public GetTemperaturesRequest() { }

    public GetTemperaturesRequest(String city, List<Date> dates) {
        this.city = city;
        this.dates = dates;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
